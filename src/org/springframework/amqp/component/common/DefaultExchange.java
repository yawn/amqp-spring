package org.springframework.amqp.component.common;

import org.springframework.amqp.component.Exchange;
import com.rabbitmq.client.Channel;

public class DefaultExchange extends Exchange {

    public static Exchange declare(Channel channel) {

        Exchange exchange = new DefaultExchange();
        exchange.setChannel(channel);
        exchange.declare();

        return exchange;

    }

    public DefaultExchange() {
        setName("");
        setProperty(Property.DURABLE);
        setType(Exchange.Type.DIRECT);
    }

}