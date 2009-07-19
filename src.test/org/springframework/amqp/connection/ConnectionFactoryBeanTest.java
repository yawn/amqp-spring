package org.springframework.amqp.connection;

import junit.framework.TestCase;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Address;

import java.net.InetAddress;
import java.util.UUID;
import java.io.IOException;

public class ConnectionFactoryBeanTest extends AbstractConnectionTest {

    public void testConnectDefaultLocal() throws Exception {
        Connection connection = connect();
    }

    public void testMultiple() throws Exception {

        connectionFactoryBean.setAddresses(new Address("localhost", ConnectionFactoryBean.DEFAULT_PORT),
                new Address(InetAddress.getLocalHost().getHostName(), ConnectionFactoryBean.DEFAULT_PORT));

        Connection connection = connect();
    }

    public void testInvalidCredentials() throws Exception {

        connectionFactoryBean.setUsername(UUID.randomUUID().toString());
        connectionFactoryBean.setPassword(UUID.randomUUID().toString());

        try {
            Connection connection = connect();
            fail();
        } catch(IOException e) {
        }

        connectionFactoryBean.setUsername(ConnectionFactoryBean.DEFAULT_USER);
        connectionFactoryBean.setPassword(ConnectionFactoryBean.DEFAULT_PASS);

        Connection connection = connect();
        
    }

}
