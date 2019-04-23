package com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class EngineerBoss {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(EngineerBoss.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "engineerBoss.q")
    public void receive(TextMessage message) {
        LOGGER.info("Kierownik zespołu dostał zgłoszenie");
        String msg = "nie może ";
        try {
            Destination replyTo = message.getJMSReplyTo();
            jmsTemplate.convertAndSend(replyTo, msg);
        } catch (JMSException e) {
            e.printStackTrace();

        }

    }

}
