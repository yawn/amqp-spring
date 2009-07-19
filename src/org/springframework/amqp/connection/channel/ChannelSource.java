package org.springframework.amqp.connection.channel;

import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.InitializingBean;

public interface ChannelSource {

    public Channel getChannel();

}