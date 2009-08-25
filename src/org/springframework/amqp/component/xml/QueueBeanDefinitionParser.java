package org.springframework.amqp.component.xml;

import org.w3c.dom.Element;
import org.springframework.amqp.component.QueueImpl;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import static org.springframework.util.StringUtils.hasText;

public class QueueBeanDefinitionParser extends AbstractNamedComponentBeanDefinitionParser {

    protected Class getBeanClass(Element element) {
        return QueueImpl.class;
    }

    protected void doComponentParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {

        String properties = element.getAttribute("properties");
        if (hasText(properties))
            builder.addPropertyValue("properties", listTokensToSet(properties));

    }

}