package org.springframework.amqp.component;

import com.rabbitmq.client.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.AMQException;
import org.springframework.amqp.component.util.CollectionUtil;

import java.io.IOException;
import java.util.Set;

public class Binding implements Component {

    private static final Log log = LogFactory.getLog(Binding.class);

    public static String NO_KEY = "";

    private Set<Exchange> exchanges;
    private Set<Queue> queues;
    private Set<String> keys;

    @SuppressWarnings({"unchecked"})
    public Binding() {
        exchanges = CollectionUtil.EMPTY_SET();
        queues = CollectionUtil.EMPTY_SET();
        keys = CollectionUtil.EMPTY_SET(NO_KEY);
    }

    public Set<Exchange> getExchanges() {
        return exchanges;
    }

    public void setExchanges(Set<Exchange> exchanges) {
        this.exchanges = exchanges;
    }

    public Set<Queue> getQueues() {
        return queues;
    }

    public void setQueues(Set<Queue> queues) {
        this.queues = queues;
    }

    public Set<String> getKeys() {
        return keys;
    }

    public void setKeys(Set<String> keys) {
        this.keys = keys;
    }

    protected Channel getChannel() {
        return exchanges.iterator().next().getChannel();
    }

    public void declare() {

        if (log.isInfoEnabled())
            log.info(String.format("Declaring binding %s", this));

        for (Exchange exchange : exchanges) {
            for (Queue queue : queues) {
                for (String key : keys) {
                    try {
                        getChannel().queueBind(queue.getName(), exchange.getName(), key);
                    } catch (IOException e) {
                        throw new AMQException(String.format("Could not declare binding '%s'", this), e);
                    }
                }
            }
        }

    }

    @Override
    public String toString() {
        return "Binding{" +
                "exchanges=" + exchanges +
                ", queues=" + queues +
                ", keys=" + keys +
                '}';
    }
    
}