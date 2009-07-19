package org.springframework.amqp.connection;

import com.rabbitmq.client.Connection;

public class SSLConnectionFactoryBeanTest extends AbstractConnectionTest {

    @Override
    protected void setUp() throws Exception {
        connectionFactoryBean = new SSLConnectionFactoryBean();
    }

}