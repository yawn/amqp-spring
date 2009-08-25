package org.springframework.amqp.component.common;

import org.springframework.amqp.component.ExchangeImpl;

public class DefaultExchange extends ExchangeImpl {

    public DefaultExchange() {
        setName("");
        setProperty(Property.DURABLE);
        setType(ExchangeImpl.Type.DIRECT);
    }

}