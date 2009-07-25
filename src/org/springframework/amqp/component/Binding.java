package org.springframework.amqp.component;

import org.springframework.amqp.AMQException;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.util.Collections;
import java.io.IOException;

import com.rabbitmq.client.Channel;

public class Binding extends AbstractComponent {

    private static final Log log = LogFactory.getLog(Binding.class);

    public static String NO_KEY = "";

    private Exchange exchange;
    private Queue queue;
    private String key = NO_KEY;

    @Override
    public Channel getChannel() {

        Channel channel = super.getChannel();

        if (channel != null)
            return channel;

        channel = getExchange().getChannel();

        if (channel != null)
            return channel;

        channel = getQueue().getChannel();

        if (channel != null)
            return channel;

        throw new AMQException(String.format("No channel available for binding '%s'", this));

    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void declare() {

        if (log.isInfoEnabled())
            log.info(String.format("Declaring binding %s", this));

        try {
            getChannel().queueBind(queue.getName(), exchange.getName(), key);
        } catch (IOException e) {
            throw new AMQException(String.format("Could not declare binding '%s'", this), e);
        }

    }

    @Override
    public String toString() {
        return "Binding{" +
                "exchange=" + exchange +
                ", queue=" + queue +
                ", key='" + key + '\'' +
                '}';
    }

}