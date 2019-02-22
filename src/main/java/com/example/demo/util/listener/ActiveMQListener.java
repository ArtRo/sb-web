package com.example.demo.util.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created by dhf_ndm on 2019/2/19
 * the previous bug derived from the previous
 */
@Component
public class ActiveMQListener {

    @JmsListener(destination = "public.queue", containerFactory = "jmsListenerContainerQueue")
    @SendTo("out.queue")
    public String receiveMsgByQueue(String msg) {
        System.out.println("receive queue msg:" + msg);
        return "from receiver msg";
    }

    @JmsListener(destination = "public.topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveMsgByTopic(String msg) {
        System.out.println("receive topic msg:" + msg);
    }
}
