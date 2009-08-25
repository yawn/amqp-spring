package org.springframework.amqp.util;

import com.rabbitmq.client.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.AMQException;
import org.springframework.amqp.component.Component;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class AutoDeclaringBeanPostProcessor implements BeanPostProcessor {

    private static final Log log = LogFactory.getLog(AutoDeclaringBeanPostProcessor.class);

    private FactoryBean channelFactoryBean;

    @Required
    public void setChannelFactoryBean(FactoryBean channelFactoryBean) {
        this.channelFactoryBean = channelFactoryBean;
    }

    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return(o);
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {

        if (o instanceof Component) {

            Component component = (Component)o;

            try {

                component.setChannel((Channel) channelFactoryBean.getObject());

                if (log.isInfoEnabled())
                    log.info(String.format("Auto declaring AMQ '%s' component bean '%s'", o.getClass().getSimpleName(), s));

                component.declare();

            } catch (Exception e) {
                throw new AMQException("Error while auto declaring component bean", e);
            }

        }

        return o;

    }

}
