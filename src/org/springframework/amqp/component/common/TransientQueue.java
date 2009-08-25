package org.springframework.amqp.component.common;

import org.springframework.amqp.component.QueueImpl;

public class TransientQueue extends QueueImpl {

    public TransientQueue() {
        addProperties(Property.AUTO_DELETE, Property.EXCLUSIVE);
    }

}