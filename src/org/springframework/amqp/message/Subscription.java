package org.springframework.amqp.message;

import org.springframework.amqp.component.Queue;
import org.springframework.amqp.component.AbstractComponent;
import org.springframework.amqp.AMQException;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Subscription extends AbstractComponent {

    private static final Log log = LogFactory.getLog(Subscription.class);

    public static final boolean DEFAULT_NO_ACK = false;

    private Queue queue;
    private MessageConsumer messageConsumer;
    private boolean noAck = DEFAULT_NO_ACK;

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public MessageConsumer getReceiver() {
        return messageConsumer;
    }

    public void setReceiver(MessageConsumer messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    public boolean isNoAck() {
        return noAck;
    }

    public void setNoAck(boolean noAck) {
        this.noAck = noAck;
    }

    public void declare() {

        if (log.isInfoEnabled())
            log.info(String.format("Declaring subscription '%s'", this));

        Consumer consumer = new DefaultConsumer(getChannel()) {

            @Override
            public void handleDelivery(String consumerTag, final Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                final Message message = new Message(consumerTag, envelope, properties, body);

                messageConsumer.receive(message, new AckHandler() {

                    public void acknowledge() {

                        try {
                            getChannel().basicAck(envelope.getDeliveryTag(), false);
                        } catch (IOException e) {
                            if (log.isErrorEnabled())
                                log.error(String.format("Unable to acknowledge message '%s'", message), e);
                        }

                    }

                });

            }

        };

        try {
            getChannel().basicConsume(queue.getName(), noAck, consumer);
        } catch (IOException e) {
            throw new AMQException(String.format("Could not declared subscription '%s'", this), e);
        }

        try {

        GetResponse response = getChannel().basicGet(queue.getName(), noAck);

        while(response != null)
            consumer.handleDelivery(null, response.getEnvelope(), response.getProps(), response.getBody());

        } catch (IOException e) {
            if (log.isErrorEnabled())
                log.error(String.format("Error while emptying queue '%s'", queue), e);
        }

    }

    @Override
    public String toString() {
        return "Subscription{" +
                "queue=" + queue +
                ", messageConsumer=" + messageConsumer +
                ", noAck=" + noAck +
                '}';
    }

}
