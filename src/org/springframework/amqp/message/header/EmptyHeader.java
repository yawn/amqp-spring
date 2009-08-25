package org.springframework.amqp.message.header;

public class EmptyHeader extends HeaderImpl {

    public static final Header INSTANCE = new EmptyHeader();

    private EmptyHeader() {
    }

}
