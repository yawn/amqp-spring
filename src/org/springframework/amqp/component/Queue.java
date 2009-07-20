package org.springframework.amqp.component;

import org.springframework.amqp.AMQException;
import org.springframework.amqp.component.common.DefaultExchange;
import org.springframework.amqp.message.Message;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.util.EnumSet;
import java.util.Arrays;
import java.util.Collections;
import java.io.IOException;

import com.rabbitmq.client.AMQP;

public class Queue extends AbstractNamedComponent {

    private static final Log log = LogFactory.getLog(Queue.class);

    private static final EnumSet INVALID_COMBINATION = EnumSet.of(Property.AUTO_DELETE, Property.DURABLE);

    public enum Property {

        DURABLE,
        AUTO_DELETE,
        EXCLUSIVE

    }

    public Queue() {
        properties = EnumSet.noneOf(Property.class);
    }

    private EnumSet properties;
    private Exchange defaultExchange;

    public EnumSet getProperties() {
        return properties;
    }

    public void setProperties(Property... properties) {
        this.properties = EnumSet.copyOf(Arrays.asList(properties));
    }
    
    public void declare(final boolean passive) {

        if (getProperties().containsAll(INVALID_COMBINATION))
            throw new AMQException("Queue can't be both - auto_delete and durable");

        if (log.isInfoEnabled())
            log.info(String.format("Declaring queue '%s'", this));

        try {
            getChannel().queueDeclare(getName(),
                    passive,
                    getProperties().contains(Property.DURABLE),
                    getProperties().contains(Property.EXCLUSIVE),
                    getProperties().contains(Property.AUTO_DELETE),
                    Collections.EMPTY_MAP);

        } catch (IOException e) {
            throw new AMQException(String.format("Could not declare queue '%s'", this), e);
        }

    }

    public void send(Message message) {

        /*
        if (defaultExchange == null)
            defaultExchange = DefaultExchange.declare(getChannel());

        message.setRoutingKey(getName());
        defaultExchange.send(message);
         */

    }

    @Override
    public String toString() {
        return "Queue{" +
                "name=" + getName() +
                ",properties=" + properties +
                '}';
    }

}