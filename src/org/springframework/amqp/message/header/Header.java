package org.springframework.amqp.message.header;

import com.rabbitmq.client.AMQP;

public interface Header {

    public String getRoutingKey();

    public AMQP.BasicProperties getProperties();

}
