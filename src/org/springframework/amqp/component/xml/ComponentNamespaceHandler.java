package org.springframework.amqp.component.xml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.BeanDefinitionParser;

public class ComponentNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerAliasedBeanDefinitionParser(new ExchangeBeanDefinitionParser(), "exchange", "e");
        registerAliasedBeanDefinitionParser(new QueueBeanDefinitionParser(), "queue", "q");
        registerAliasedBeanDefinitionParser(new BindingBeanDefinitionParser(), "binding", "b");
    }

    protected void registerAliasedBeanDefinitionParser(BeanDefinitionParser parser, String... elementNames) {

        for (String elementName : elementNames)
            registerBeanDefinitionParser(elementName, parser);

    }

}
