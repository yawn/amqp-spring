package org.springframework.amqp.connection;

import junit.framework.TestCase;
import com.rabbitmq.client.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: joernbarthel
 * Date: Jul 19, 2009
 * Time: 2:53:18 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractConnectionTest extends TestCase {

    protected ConnectionFactoryBean connectionFactoryBean;

    @Override
    protected void setUp() throws Exception {
        connectionFactoryBean = new ConnectionFactoryBean();
    }

    @Override
    protected void tearDown() throws Exception {
        connectionFactoryBean.destroy();
    }

    protected Connection connect() throws Exception {
        connectionFactoryBean.afterPropertiesSet();
        return (Connection) connectionFactoryBean.getObject();
    }

}