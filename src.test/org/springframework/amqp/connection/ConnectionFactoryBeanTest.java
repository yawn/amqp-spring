package org.springframework.amqp.connection;

import junit.framework.TestCase;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Address;

import java.net.InetAddress;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.IOException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEvent;

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

    public void testEvent() throws Exception {

        final List<ApplicationEvent> applicationEvents = new ArrayList<ApplicationEvent>();

        connectionFactoryBean.setApplicationEventPublisher(new ApplicationEventPublisher() {

            public void publishEvent(ApplicationEvent applicationEvent) {
                System.out.println("ConnectionFactoryBeanTest.publishEvent");
                applicationEvents.add(applicationEvent);
            }

        });

        Thread thread = new Thread() {

            @Override
            public void run() {

                while (applicationEvents.size() == 0)
                    Thread.yield();

                System.out.println("ConnectionFactoryBeanTest.run");

                assertTrue(applicationEvents.iterator().next() instanceof Shutdown);

            }

        };

        thread.start();

        connect();
       
    }

}
