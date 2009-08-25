package org.springframework.amqp.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.component.util.CollectionUtil;

import java.io.IOException;
import java.util.Set;

public class BindingImpl extends AbstractComponent implements Binding {

    private static final Log log = LogFactory.getLog(BindingImpl.class);

    public static String NO_KEY = "";

    private Set<Exchange> exchanges;
    private Set<Queue> queues;
    private Set<String> keys;

    @SuppressWarnings({"unchecked"})
    public BindingImpl() {
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

    public void declare() throws IOException {

        if (log.isInfoEnabled())
            log.info(String.format("Declaring binding %s", this));

        for (Exchange exchange : exchanges) {
            for (Queue queue : queues) {
                for (String key : keys)
                    getChannel().queueBind(queue.getName(), exchange.getName(), key);
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