package org.springframework.amqp.component.common;

import org.springframework.amqp.component.Exchange;

public class DefaultTopicExchange extends Exchange {

    public DefaultTopicExchange() {
        setName("amq.topic");
        setProperty(Exchange.Property.DURABLE);
        setType(Exchange.Type.TOPIC);
    }

}
