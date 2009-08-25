package org.springframework.amqp.message.subscription;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.springframework.amqp.AMQException;
import org.springframework.amqp.component.AbstractComponent;
import org.springframework.amqp.component.Component;
import org.springframework.amqp.component.Queue;
import org.springframework.amqp.message.header.HeaderImpl;
import org.springframework.amqp.message.MessageSink;
import org.springframework.amqp.message.ReceivedMessageImpl;

import java.io.IOException;

public class SimpleSubscription extends AbstractComponent implements Component, Subscription {

    private Queue queue;
    private MessageSink messageSink;
    private boolean noAck = true;

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public MessageSink getMessageSink() {
        return messageSink;
    }

    public void setMessageSink(MessageSink messageSink) {
        this.messageSink = messageSink;
    }

    public boolean isNoAck() {
        return noAck;
    }

    public void setNoAck(boolean noAck) {
        this.noAck = noAck;
    }

    protected Consumer newConsumerInstance() {

        return new DefaultConsumer(getChannel()) {

            @Override
            public void handleDelivery(String consumerTag, final Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                HeaderImpl header;

                if (noAck)
                    header = new HeaderImpl();
                else
                    header = new HeaderImpl() {
           
                    @Override
                    public void acknowledge() {

                        final long deliveryTag = envelope.getDeliveryTag();

                        try {
                            getChannel().basicAck(deliveryTag, false);
                        } catch (IOException e) {
                            throw new AMQException(String.format("Unable to acknowledge message with delivery tag '%d'", deliveryTag), e);
                        }
                        
                    }

                };

                header.setConsumerTag(consumerTag);
                header.setEnvelope(envelope);
                header.setProperties(properties);
                header.setRoutingKey(envelope.getRoutingKey());

                ReceivedMessageImpl message = new ReceivedMessageImpl();
                message.setHeader(header);
                message.setBody(body);

                messageSink.receive(message);

            }

        };

    }

    public void declare() throws Exception {
        getChannel().basicConsume(queue.getName(), noAck, newConsumerInstance());
    }

}