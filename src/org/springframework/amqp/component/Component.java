package org.springframework.amqp.component;

import com.rabbitmq.client.Channel;

public interface Component {

    public void setChannel(Channel channel);

    public void declare() throws Exception;
    
}
