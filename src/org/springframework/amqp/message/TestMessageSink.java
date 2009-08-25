package org.springframework.amqp.message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestMessageSink implements MessageSink {

    private static final Log log = LogFactory.getLog(TestMessageSink.class);

    public void receive(ReceivedMessage message) {

        if (log.isInfoEnabled())
            log.info(String.format("I received a message '%s'", new String(message.getBody())));

        message.getHeader().acknowledge();

    }

}