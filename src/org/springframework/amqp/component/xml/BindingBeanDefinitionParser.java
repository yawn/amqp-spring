package org.springframework.amqp.component.xml;

import org.w3c.dom.Element;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedSet;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import static org.springframework.util.StringUtils.hasText;
import org.springframework.amqp.component.BindingImpl;

import java.util.*;

public class BindingBeanDefinitionParser extends AbstractComponentBeanDefinitionReader {

    @Override
    protected Class getBeanClass(Element element) {
        return BindingImpl.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {

        // resolve exchanges
        ManagedSet exchangeRefs = handleReferences("exchange", element, parserContext);
        if (exchangeRefs.isEmpty())
            parserContext.getReaderContext().error("No exchange reference(s) found for binding", element);

        builder.addPropertyValue("exchanges", exchangeRefs);

        // resolve queues
        ManagedSet queueRefs = handleReferences("queue", element, parserContext);
        if (queueRefs.isEmpty())
            parserContext.getReaderContext().error("No queue reference(s) found for binding", element);

        builder.addPropertyValue("queues", queueRefs);

        // handle keys
        Set<String> allKeys = new HashSet<String>();

        String key = element.getAttribute("key");
        if (hasText(key))
            allKeys.add(key);

        String keys = element.getAttribute("keys");
        if (hasText(keys))
            allKeys.addAll(listTokensToSet(keys));

        builder.addPropertyValue("keys", allKeys);

    }

    protected ManagedSet handleReferences(String component, Element element, ParserContext parserContext) {
        return createReferences(parseReferences(component, element), element, parserContext);
    }

    @SuppressWarnings({"unchecked"})
    protected Set<String> parseReferences(String component, Element element) {

        Set<String> referenceSet = new HashSet<String>();

        String reference = element.getAttribute(String.format("%s-ref", component));
        if (hasText(reference))
            referenceSet.add(reference);

        String referenceList = element.getAttribute(String.format("%s-refs", component));
        if (hasText(referenceList))
            referenceSet.addAll(listTokensToSet(referenceList));

        return referenceSet;

    }

    @SuppressWarnings({"unchecked"})
    protected ManagedSet createReferences(Set<String> referenceSet, Element element, ParserContext parserContext) {

        ManagedSet managedSet = new ManagedSet(referenceSet.size());

        for (String reference : referenceSet) {

            RuntimeBeanReference beanReference = new RuntimeBeanReference(reference);
            beanReference.setSource(parserContext.extractSource(element));
            managedSet.add(beanReference);

        }

        return managedSet;

    }

}
