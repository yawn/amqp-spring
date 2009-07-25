package org.springframework.amqp.component.xml;

import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public abstract class AbstractComponentBeanDefinitionReader extends AbstractSingleBeanDefinitionParser {

    protected static String LIST_TOKEN = " ";

    @Override
    protected boolean shouldGenerateIdAsFallback() {
        return true;
    }
    
    @SuppressWarnings({"unchecked"})
    protected Set<String> listTokensToSet(String list) {
        return new HashSet(Arrays.asList(list.split(LIST_TOKEN)));
    }

}
