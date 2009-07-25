package org.springframework.amqp.message;

import org.springframework.amqp.component.Exchange;
import org.springframework.amqp.message.Message;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class TestProducer extends TimerTask {

    private static final Log log = LogFactory.getLog(TestProducer.class);

    private Exchange exchange;
    private String routingKey;

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public void run() {

        if (log.isDebugEnabled())
            log.debug("Testproducer sending message ...");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm:ss");

        exchange.send(new Message(String.format("The local time is %s", format.format(calendar.getTime())), routingKey));

    }

}
