package org.springframework.amqp.component.common;

import org.springframework.amqp.component.Exchange;

public class DefaultFanoutExchange extends Exchange {

    public DefaultFanoutExchange() {
        setName("amq.fanout");
        setProperty(Exchange.Property.DURABLE);
        setType(Exchange.Type.FANOUT);
    }

}