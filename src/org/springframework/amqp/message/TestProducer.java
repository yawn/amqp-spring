package org.springframework.amqp.message;

import org.springframework.amqp.component.Exchange;
import org.springframework.amqp.message.Message;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.util.Timer;
import java.util.TimerTask;

public class TestProducer extends TimerTask {

    private static final Log log = LogFactory.getLog(TestProducer.class);

    private Exchange exchange;

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public void run() {

        if (log.isDebugEnabled())
            log.debug("Testproducer sending message ...");

        exchange.send(new Message("Hello world", "de.bb.berlin.west.kreuzberg"));
    }

}
