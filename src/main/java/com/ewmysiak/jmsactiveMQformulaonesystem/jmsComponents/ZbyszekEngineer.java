package com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

public class ZbyszekEngineer {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ZbyszekEngineer.class);

    @JmsListener(containerFactory = "topicJmsListenerContainerFactory",destination = "engineer.topic")
    public void receive(String message) {
        LOGGER.info("ZbyszekEngineer : Mechanik Zbyszek odebrał następującą wiadomość:      ");
        LOGGER.info("MSG:'{}'  " , message);
    }
}
