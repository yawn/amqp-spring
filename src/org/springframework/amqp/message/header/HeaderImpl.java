package org.springframework.amqp.message.header;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

public class HeaderImpl implements Header, ReceivedHeader {

    private String routingKey;
    private AMQP.BasicProperties properties;
    private String consumerTag;
    private Envelope envelope;

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public AMQP.BasicProperties getProperties() {
        return properties;
    }

    public void setProperties(AMQP.BasicProperties properties) {
        this.properties = properties;
    }

    public String getConsumerTag() {
        return consumerTag;
    }

    public void setConsumerTag(String consumerTag) {
        this.consumerTag = consumerTag;
    }

    public Envelope getEnvelope() {
        return envelope;
    }

    public void setEnvelope(Envelope envelope) {
        this.envelope = envelope;
    }

    public void acknowledge() {
        throw new IllegalStateException("Acknowledge is not supported for this header");
    }

    public void reject() {
        throw new IllegalStateException("Reject is not supported for this header");
    }

}
