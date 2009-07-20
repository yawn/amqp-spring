package org.springframework.amqp.message;

public interface AckHandler {

    public void acknowledge();

}
