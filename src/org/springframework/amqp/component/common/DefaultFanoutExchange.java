package org.springframework.amqp.component.common;

import org.springframework.amqp.component.ExchangeImpl;

public class DefaultFanoutExchange extends ExchangeImpl {

    public DefaultFanoutExchange() {
        setName("amq.fanout");
        setProperty(ExchangeImpl.Property.DURABLE);
        setType(ExchangeImpl.Type.FANOUT);
    }

}