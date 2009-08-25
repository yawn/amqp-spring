package org.springframework.amqp.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.AMQException;
import org.springframework.amqp.message.Message;

import java.io.IOException;
import java.util.Collections;

public class ExchangeImpl extends AbstractNamedComponent implements Exchange {

    public static final CharSequence DEFAULT_TYPE = Type.DIRECT;

    private static final Log log = LogFactory.getLog(ExchangeImpl.class);

    private Property property;
    private CharSequence type = DEFAULT_TYPE;

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public CharSequence getType() {
        return type;
    }

    public void setType(CharSequence type) {
        this.type = type;
    }

    public void declare(boolean passive) throws IOException {

        if (log.isInfoEnabled())
            log.info(String.format("Declaring exchange %s", this));

          getChannel().exchangeDeclare(getName(),
                    type.toString().intern(),
                    passive,
                    property == Property.DURABLE,
                    property == Property.AUTO_DELETE,
                    Collections.EMPTY_MAP);

    }

    public void send(Message message) {
        send(message, message.getHeader().getRoutingKey());
    }

    public void send(Message message, String routingKey) {

        try {
            getChannel().basicPublish(getName(),
                    routingKey,
                    message.getHeader().getProperties(),
                    message.getBody());
        } catch (IOException e) {
            throw new AMQException(String.format("Could not send message '%s'", message), e);
        }

    }

    @Override
    public String toString() {
        return "Exchange{" +
                "name=" + getName() +
                ",property=" + property +
                ", type=" + type +
                '}';
    }

}