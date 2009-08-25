package org.springframework.amqp.message;

public interface MessageSource {
    
    public void send(Message message);

    public void send(Message message, String routingKey);
    
}
