package org.springframework.amqp;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.amqp.message.Message;
import org.springframework.amqp.message.AckHandler;
import org.springframework.amqp.message.MessageConsumer;

public class TestContext  {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    }

}