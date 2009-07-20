package org.springframework.amqp.component;

public class Builder {

    private Exchange exchange;

    public Builder(Exchange exchange) {
        this.exchange = exchange;
    }

    public void bind(String key, Queue... queues) {

        for (Queue queue : queues) {

            Binding binding = new Binding();
            binding.setKey(key);
            binding.setExchange(exchange);
            binding.setQueue(queue);

            if (queue.getChannel() == null)
                queue.setChannel(exchange.getChannel());

            binding.setChannel(exchange.getChannel());

            binding.declare();

        }

    }

    public void bind(Queue... queues) {
        bind(Binding.NO_KEY, queues);
    }

}
