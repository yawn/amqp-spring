package org.springframework.amqp.component;

import java.util.Set;

public interface Binding extends Component {

    public Set<Exchange> getExchanges();

    public Set<Queue> getQueues();

    public Set<String> getKeys();

}
