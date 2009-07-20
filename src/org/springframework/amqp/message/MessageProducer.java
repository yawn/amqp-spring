package org.springframework.amqp.message;

public interface MessageProducer {
    
    public void send(Message message);
    
}
