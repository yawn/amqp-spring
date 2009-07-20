package org.springframework.amqp.message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.message.MessageConsumer;
import org.springframework.amqp.message.Message;
import org.springframework.amqp.message.AckHandler;

public class TestConsumer implements MessageConsumer {

    private static final Log log = LogFactory.getLog(TestConsumer.class);

    public void receive(Message message, AckHandler handler) {

        if (log.isInfoEnabled())
            log.info(String.format("I received a message %s", message));

        handler.acknowledge();

    }

}