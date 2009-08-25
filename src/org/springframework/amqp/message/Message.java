package org.springframework.amqp.message;

import org.springframework.amqp.message.header.Header;

public interface Message {

    public Header getHeader();

    public byte[] getBody();

}