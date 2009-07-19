package org.springframework.amqp.connection;

import com.rabbitmq.client.ConnectionFactory;

import javax.net.ssl.TrustManager;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class SSLConnectionFactoryBean extends ConnectionFactoryBean {

    private static final Log log = LogFactory.getLog(SSLConnectionFactoryBean.class);

    private String protocol = ConnectionFactory.DEFAULT_SSL_PROTOCOL;
    private TrustManager trustManager;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public TrustManager getTrustManager() {
        return trustManager;
    }

    public void setTrustManager(TrustManager trustManager) {
        this.trustManager = trustManager;
    }

    @Override
    protected ConnectionFactory newConnectionFactory() throws Exception {

        if (log.isInfoEnabled())
            log.info(String.format("Enabling SSL for broker connection using protocol '%s'", protocol));

        ConnectionFactory connectionFactory = super.newConnectionFactory();

        if (trustManager != null)
            connectionFactory.useSslProtocol(protocol, trustManager);
        else
            connectionFactory.useSslProtocol(protocol);

        return connectionFactory;

    }

}