package org.springframework.amqp;

import java.io.IOException;

public class AMQException extends RuntimeException {

    public AMQException() {
    }

    public AMQException(String s) {
        super(s);
    }

    public AMQException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AMQException(Throwable throwable) {
        super(throwable);
    }

}