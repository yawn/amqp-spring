package org.springframework.amqp.message;

public interface MessageSink {

    public void receive(ReceivedMessage message);
    
}
