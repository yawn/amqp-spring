package org.springframework.amqp.message;

import org.springframework.amqp.message.header.EmptyHeader;
import org.springframework.amqp.message.header.Header;

public class MessageImpl extends AbstractMessage<Header> implements SentMessage {

    public MessageImpl(byte[] body) {
        super(body, EmptyHeader.INSTANCE);
    }

    public MessageImpl(String body) {
        super(body, EmptyHeader.INSTANCE);
    }

    public MessageImpl(byte[] body, String routingKey) {
        super(body, routingKey);
    }

    public MessageImpl(String body, String routingKey) {
        super(body, routingKey);
    }

    public MessageImpl(byte[] body, Header header) {
        super(body, header);
    }

    public MessageImpl(String body, Header header) {
        super(body, header);
    }

}
