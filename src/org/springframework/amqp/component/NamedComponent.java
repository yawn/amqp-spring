package org.springframework.amqp.component;

import org.springframework.amqp.message.MessageProducer;

public interface NamedComponent extends Component, MessageProducer {

    public void declare(boolean passive);

}
