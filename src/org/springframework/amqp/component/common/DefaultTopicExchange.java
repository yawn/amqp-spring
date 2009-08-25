package org.springframework.amqp.component.common;

import org.springframework.amqp.component.ExchangeImpl;

public class DefaultTopicExchange extends ExchangeImpl {

    public DefaultTopicExchange() {
        setName("amq.topic");
        setProperty(ExchangeImpl.Property.DURABLE);
        setType(ExchangeImpl.Type.TOPIC);
    }

}
