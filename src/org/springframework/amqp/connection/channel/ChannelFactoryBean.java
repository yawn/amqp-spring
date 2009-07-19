package org.springframework.amqp.connection.channel;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.amqp.AMQException;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.AMQP;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.HashSet;
import java.util.WeakHashMap;
import java.util.Map;

public class ChannelFactoryBean implements ChannelSource, FactoryBean, DisposableBean {

    private static final Log log = LogFactory.getLog(ChannelFactoryBean.class);

    public static final int DEFAULT_CLOSE_CODE = AMQP.REPLY_SUCCESS;
    public static final String DEFAULT_CLOSE_MESSAGE = "Goodbye";

    private Connection connection;
    private int closeCode = DEFAULT_CLOSE_CODE;
    private String closeMessage = DEFAULT_CLOSE_MESSAGE;

    private Set<Reference<Channel>> channelReferenceSet = new HashSet<Reference<Channel>>();

    public Channel getChannel() {

        try {
            return (Channel) getObject();
        } catch (Exception e) {
            throw new AMQException("Unable to create channel", e);
        }
        
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public int getCloseCode() {
        return closeCode;
    }

    public void setCloseCode(int closeCode) {
        this.closeCode = closeCode;
    }

    public String getCloseMessage() {
        return closeMessage;
    }

    public void setCloseMessage(String closeMessage) {
        this.closeMessage = closeMessage;
    }

    Set<Reference<Channel>> getChannelReferenceSet() {
        return channelReferenceSet;
    }

    public Object getObject() throws Exception {

        final Channel channel = connection.createChannel();
        channelReferenceSet.add(new WeakReference<Channel>(channel));

        return channel;

    }

    public Class getObjectType() {
        return Channel.class;
    }

    public boolean isSingleton() {
        return false;
    }

    public void destroy() throws Exception {

        if (log.isDebugEnabled())
            log.debug(String.format("Closing '%d' channels", channelReferenceSet.size()));

        for (Reference<Channel> channelReference : channelReferenceSet) {

            try {
                channelReference.get().close(closeCode, closeMessage);
            } catch (NullPointerException e) {
            }

        }

    }

}
