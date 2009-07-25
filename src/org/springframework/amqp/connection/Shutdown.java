package org.springframework.amqp.connection;

import com.rabbitmq.client.ShutdownSignalException;
import org.springframework.context.ApplicationEvent;

public class Shutdown extends ApplicationEvent {

    private ConnectionFactoryBean connectionFactoryBean;

    public Shutdown(ConnectionFactoryBean connectionFactoryBean, ShutdownSignalException cause) {
        super(cause);
        this.connectionFactoryBean = connectionFactoryBean;
    }

    @Override
    public ShutdownSignalException getSource() {
        return (ShutdownSignalException) super.getSource();
    }

    public ConnectionFactoryBean getConnectionFactoryBean() {
        return connectionFactoryBean;
    }

}
