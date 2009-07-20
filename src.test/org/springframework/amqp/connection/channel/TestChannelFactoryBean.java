package org.springframework.amqp.connection.channel;

import org.springframework.amqp.connection.AbstractConnectionTest;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.lang.ref.Reference;

public class TestChannelFactoryBean extends AbstractConnectionTest {

    private Connection connection;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.connection = connect();
    }

    public void testChannelAcquiring() throws Exception {

        ChannelFactoryBean channelFactoryBean = new ChannelFactoryBean();
        channelFactoryBean.setConnection(connection);
        channelFactoryBean.setCloseMessage("So Long, and Thanks for All the Fish");

        {
            Channel c1 = channelFactoryBean.getObject();
            assertTrue(c1.isOpen());

            Channel c2 = channelFactoryBean.getObject();
            assertNotSame(c1, c2);
        }

        System.gc();

        Thread.sleep(1000);

        for (Reference<Channel> reference : channelFactoryBean.getChannelReferenceSet())
            assertNull(reference.get());

        channelFactoryBean.destroy();

    }

}
