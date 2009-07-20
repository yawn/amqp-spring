package org.springframework.amqp.component.common;

import org.springframework.amqp.component.Queue;

import java.util.EnumSet;

public class TransientQueue extends Queue {

    public TransientQueue() {
        setProperties(Property.AUTO_DELETE, Property.EXCLUSIVE);
    }

}