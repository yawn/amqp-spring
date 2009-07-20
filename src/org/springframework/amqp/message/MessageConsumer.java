package org.springframework.amqp.message;

public interface MessageConsumer {

    public void receive(Message message, AckHandler handler);

}
