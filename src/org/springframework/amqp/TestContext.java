package org.springframework.amqp;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class TestContext  {

    static {

        short i = 0;
        assert(++i == 1);

        if (i == 0)
            throw new IllegalStateException("Enable assertions");

    }

    public static void main(String[] args) {

        assert (new ClassPathResource("META-INF/spring.schemas").exists());
        assert (new ClassPathResource("META-INF/spring.handlers").exists());
        assert (new ClassPathResource("org/springframework/amqp/component/xml/components.xsd").exists());
                        
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        context.registerShutdownHook();
        
    }

}