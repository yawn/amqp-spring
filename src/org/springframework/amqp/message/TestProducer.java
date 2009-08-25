package org.springframework.amqp.message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.component.Exchange;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

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

        exchange.send(new MessageImpl(String.format("The local time is %s", format.format(calendar.getTime())), routingKey));

    }

}
