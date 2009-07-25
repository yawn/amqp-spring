package org.springframework.amqp.component.xml;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import static org.springframework.util.StringUtils.*;
import org.springframework.amqp.component.Exchange;
import org.springframework.amqp.component.xml.AbstractNamedComponentBeanDefinitionParser;
import org.w3c.dom.Element;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class ExchangeBeanDefinitionParser extends AbstractNamedComponentBeanDefinitionParser {

    protected Class getBeanClass(Element element) {
        return Exchange.class;
    }

    protected void doComponentParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {

        String type = element.getAttribute("type");
        builder.addPropertyValue("type", type);

        String property = element.getAttribute("property");
        if (hasText(property))
            builder.addPropertyValue("property", property);

    }

}
