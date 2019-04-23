package com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

public class CarMonitor {
    //aplikacja zainstalowana w boildzie

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CarMonitor.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public InfoCar infoCar;

   @PostConstruct
    public void doSomethingAfterStartup() {
        infoCar = new InfoCar();
    }

    @Scheduled(fixedDelay=5000)
    public void send() {
        infoCar.setNewInfoCarParams();
        LOGGER.info("-----------------------------------------------------------");
        LOGGER.info("            ");
        LOGGER.info("CarMonitor : wysłanie wiadomości do PitStopu i FileLoggera");
        jmsTemplate.convertAndSend("pitstop.q",infoCar);
        jmsTemplate.convertAndSend("filelogger.q", infoCar);
    }
}