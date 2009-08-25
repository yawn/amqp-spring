package org.springframework.amqp.message;

import org.springframework.amqp.message.header.Header;

public interface SentMessage extends Message {

    public Header getHeader();

}
