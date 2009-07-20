package org.springframework.amqp.component.common;

import org.springframework.beans.factory.InitializingBean;

import java.util.UUID;

public class ReplyQueue extends TransientQueue implements InitializingBean {

    private String prefix;

    public void afterPropertiesSet() throws Exception {

        if (prefix != null)
            setName(String.format("%s.%s", prefix, generateUniqueName()));
        else
            setName(generateUniqueName());

    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    protected String generateUniqueName() {
        return UUID.randomUUID().toString();
    }

}
