package org.springframework.amqp.message.subscription;

import org.springframework.amqp.message.MessageSink;

public interface Subscription {

    public void setMessageSink(MessageSink messageSink);

}
