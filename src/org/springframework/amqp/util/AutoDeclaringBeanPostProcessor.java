package org.springframework.amqp.util;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.amqp.component.Component;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class AutoDeclaringBeanPostProcessor implements BeanPostProcessor {

    private static final Log log = LogFactory.getLog(AutoDeclaringBeanPostProcessor.class);

    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return(o);
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {

        if (o instanceof Component) {

            if (log.isInfoEnabled())
                log.info(String.format("Auto declaring AMQ component bean '%s'", s));

            ((Component)o).declare();

        }

        return o;

    }

}
