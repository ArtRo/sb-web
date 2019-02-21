package com.example.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.assertj.core.error.ShouldBeAfterYear;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    Queue qUeue;

    @Autowired
    Topic topic;

    @Autowired
    JmsMessagingTemplate jms;

    @Autowired
    ActiveMQConnectionFactory acf;

    @Test
    public void contextLoads() {
        for(int i = 0;i<10;i++){
            jms.convertAndSend(qUeue,"queue"+i);
            jms.convertAndSend(topic,"topic"+i);
        }
        System.out.println("send success");
    }

    @JmsListener(destination = "out.queue")
    public void receiveMsg(String msg){
        System.out.println("receive msg:"+msg);
    }
}

