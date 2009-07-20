package org.springframework.amqp.connection;

import org.springframework.context.ApplicationEvent;
import com.rabbitmq.client.ShutdownSignalException;

public class Shutdown extends ApplicationEvent {

    public Shutdown(ShutdownSignalException cause) {
        super(cause);
    }

    @Override
    public ShutdownSignalException getSource() {
        return (ShutdownSignalException) super.getSource();
    }

}
