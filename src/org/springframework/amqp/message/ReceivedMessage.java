package org.springframework.amqp.message;

import org.springframework.amqp.message.header.ReceivedHeader;

public interface ReceivedMessage extends Message {

    public ReceivedHeader getHeader();

}