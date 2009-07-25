package org.springframework.amqp.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.AMQException;
import org.springframework.amqp.component.util.CollectionUtil;
import org.springframework.amqp.message.Message;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public class Queue extends AbstractNamedComponent {

    private static final Log log = LogFactory.getLog(Queue.class);

    private static final EnumSet INVALID_COMBINATION = EnumSet.of(Property.AUTO_DELETE, Property.DURABLE);

    public enum Property implements CharSequence {

        DURABLE,
        AUTO_DELETE,
        EXCLUSIVE;

        public int length() {
            return toString().length();
        }

        public char charAt(int i) {
            return toString().charAt(i);
        }

        public CharSequence subSequence(int i, int i1) {
            return toString().subSequence(i, i1);
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase().intern();
        }

    }

    private Set<CharSequence> properties = CollectionUtil.EMPTY_SET();

    public Set<CharSequence> getProperties() {
        return properties;
    }

    public void addProperties(Property... properties) {
        this.properties.addAll(Arrays.asList(properties));
    }

    public void setProperties(Set<CharSequence> properties) {
        this.properties = properties;
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
    }

    @Override
    public String toString() {
        return "Queue{" +
                "name=" + getName() +
                ",properties=" + properties +
                '}';
    }

}