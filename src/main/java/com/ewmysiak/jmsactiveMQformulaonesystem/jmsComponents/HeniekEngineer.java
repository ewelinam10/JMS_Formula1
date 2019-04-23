package com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

public class HeniekEngineer {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(HeniekEngineer.class);

    @JmsListener(containerFactory = "topicJmsListenerContainerFactory",destination = "engineer.topic")
    public void receive(String message) {
        LOGGER.info("HeniekEngineer :  Mechanik Heniek odberał następującą wiadomość:    ");
        LOGGER.info("MSG:'{}'  " , message);
    }
}
