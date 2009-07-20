package org.springframework.amqp.connection;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.BeansException;
import org.springframework.util.StringUtils;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationEventPublisher;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.net.SocketFactory;
import java.util.Arrays;

public class ConnectionFactoryBean extends ConnectionParameters implements ApplicationEventPublisherAware, FactoryBean, InitializingBean, DisposableBean {

    private static final Log log = LogFactory.getLog(ConnectionFactoryBean.class);

    public static String DEFAULT_HOST_NAME = "localhost";
    public static int DEFAULT_PORT = AMQP.PROTOCOL.PORT;
    public static int DEFAULT_MAX_REDIRECTS = 0;
    public static int DEFAULT_CONNECTION_CLOSE_TIMEOUT = -1; // infinity

    private ApplicationEventPublisher applicationEventPublisher;

    private SocketFactory socketFactory;
    private Address[] addresses = { new Address(DEFAULT_HOST_NAME, DEFAULT_PORT) };
    private int maxRedirects = DEFAULT_MAX_REDIRECTS;
    private int connectionCloseTimeout = DEFAULT_CONNECTION_CLOSE_TIMEOUT;

    private Connection connection;

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public SocketFactory getSocketFactory() {
        return socketFactory;
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public Address[] getAddresses() {
        return addresses;
    }

    public void setAddresses(Address... addresses) {
        this.addresses = addresses;
    }

    public int getMaxRedirects() {
        return maxRedirects;
    }

    public void setMaxRedirects(int maxRedirects) {
        this.maxRedirects = maxRedirects;
    }

    public int getConnectionCloseTimeout() {
        return connectionCloseTimeout;
    }

    public void setConnectionCloseTimeout(int connectionCloseTimeout) {
        this.connectionCloseTimeout = connectionCloseTimeout;
    }

    protected ConnectionFactory newConnectionFactory() throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory(this);

        if (socketFactory != null)
            connectionFactory.setSocketFactory(socketFactory);

        return connectionFactory;

    }

    protected String formatPassword(String password) {

        char[] formatted = new char[password.length()];

        for (int i=0; i<formatted.length; i++)
            formatted[i] = '*';

        return new String(formatted);

    }

    protected String formatBrokerAddresses(Address[] addresses) {

        return String.format("AMQP broker%s: %s",
                addresses.length > 1 ? "s" : "",
                StringUtils.arrayToCommaDelimitedString(addresses));

    }

    public Object getObject() throws Exception {
        return connection;
    }

    public Class getObjectType() {
        return Connection.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {

        if (log.isInfoEnabled())
            log.info(String.format("Connecting to %s using credentials ('%s' / '%s') at vhost '%s'",
                    formatBrokerAddresses(addresses),
                    getUserName(),
                    formatPassword(getPassword()),
                    getVirtualHost()));

        this.connection = newConnectionFactory().newConnection(addresses, maxRedirects);

        connection.addShutdownListener(new ShutdownListener() {

            public void shutdownCompleted(ShutdownSignalException cause) {
                applicationEventPublisher.publishEvent(new Shutdown(cause));
            }

        });

    }

    public void destroy() throws Exception {

        if (connection != null) {

            if (log.isInfoEnabled())
                log.info(String.format("Destroying connection to %s", formatBrokerAddresses(addresses)));

            this.connection.close(connectionCloseTimeout);

        }

    }

}