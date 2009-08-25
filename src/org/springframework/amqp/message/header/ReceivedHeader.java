package org.springframework.amqp.message.header;

import com.rabbitmq.client.Envelope;

public interface ReceivedHeader extends Header {

    public String getConsumerTag();

    public Envelope getEnvelope();

    public void acknowledge();

    public void reject();

}