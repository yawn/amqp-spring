package org.springframework.amqp.component.common;

import org.springframework.amqp.component.Queue;

public class TransientQueue extends Queue {

    public TransientQueue() {
        addProperties(Property.AUTO_DELETE, Property.EXCLUSIVE);
    }

}