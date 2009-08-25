package org.springframework.amqp.message;

public interface MessageSource {
    
    public void send(SentMessage message);

    public void send(SentMessage message, String routingKey);
    
}
