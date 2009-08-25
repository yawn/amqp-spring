package org.springframework.amqp.component;

import org.springframework.amqp.message.MessageSource;

public interface NamedComponent extends Component, MessageSource {

    public String getName();

    public void declare(boolean passive) throws Exception;

}
