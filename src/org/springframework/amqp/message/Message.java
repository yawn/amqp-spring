package org.springframework.amqp.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

import java.util.Arrays;

public class Message {

    public static int PAYLOAD_DISPLAY_SIZE = 128;

    private String routingKey; // the rk used for sending - a bit clumsy ...
    private AMQP.BasicProperties properties;
    private String consumerTag;
    private Envelope envelope;
    private byte[] payload;

    public Message() {
    }

    public Message(byte[] payload) {
        this(payload, null);
    }

    public Message(byte[] payload, String routingKey) {
        setPayload(payload);
        setRoutingKey(routingKey);
    }

    public Message(String payload) {
        this(payload, null);
    }

    public Message(String payload, String routingKey) {
        setPayload(payload);
        setRoutingKey(routingKey);
    }

    public Message(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] payload) {
        this(payload);
        setEnvelope(envelope);
        setProperties(properties);
        setConsumerTag(consumerTag);
    }

    public String getRoutingKey() {
        return routingKey == null ? envelope.getRoutingKey() : routingKey;
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
        setRoutingKey(envelope.getRoutingKey());
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        setPayload(payload.getBytes());
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public String getPayloadAsString() {
        return new String(getPayload());
    }

    protected String formatPayload(byte[] payload) {

        if (payload.length <= PAYLOAD_DISPLAY_SIZE)
            return new String(payload);
        else
            return formatPayload(Arrays.copyOfRange(payload, 0, PAYLOAD_DISPLAY_SIZE));

    }

    @Override
    public String toString() {
        return "Message{" +
                "routingKey='" + getRoutingKey() + '\'' +
                ", properties=" + properties +
                ", consumerTag='" + consumerTag + '\'' +
                ", envelope=" + envelope +
                ", payload=" + (payload == null ? null : formatPayload(payload)) +
                '}';
    }

}