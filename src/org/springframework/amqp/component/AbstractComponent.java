package org.springframework.amqp.component;

import com.rabbitmq.client.Channel;

public abstract class AbstractComponent implements Component {

    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}
