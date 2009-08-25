package org.springframework.amqp.message;

import org.springframework.amqp.message.header.Header;
import org.springframework.amqp.message.header.HeaderImpl;

public abstract class AbstractMessage<T extends Header> {

    private T header;
    private byte[] body;

    public AbstractMessage() {
    }

    public AbstractMessage(byte[] body) {
        this.body = body;
    }

    public AbstractMessage(String body) {
        this(body.getBytes());
    }

    public AbstractMessage(byte[] body, String routingKey) {
        this(body);

        HeaderImpl header = new HeaderImpl();
        header.setRoutingKey(routingKey);

        this.header = (T) header;
    }

    public AbstractMessage(String body, String routingKey) {
        this(body.getBytes(), routingKey);
    }

    public AbstractMessage(byte[] body, T header) {
        this(body);
        this.header = header;
    }

    public AbstractMessage(String body, T header) {
        this(body.getBytes(), header);
    }

    public T getHeader() {
        return header;
    }

    public void setHeader(T header) {
        this.header = header;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
    
}