package org.springframework.amqp.component.xml;

import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import static org.springframework.util.StringUtils.hasText;
import org.w3c.dom.Element;

public abstract class AbstractNamedComponentBeanDefinitionParser extends AbstractComponentBeanDefinitionReader {

    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {

        String channelRef = element.getAttribute("channel-ref");
        builder.addPropertyReference("channel", channelRef);

        String name = element.getAttribute("name");
        builder.addPropertyValue("name", name);

        doComponentParse(element, parserContext, builder);

    }

    protected abstract void doComponentParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder);

    // TODO: this does not escape potentially invalid ids
    @Override
    protected String resolveId(Element element, AbstractBeanDefinition definition, ParserContext parserContext) throws BeanDefinitionStoreException {
        return element.getAttribute("name");
    }

}