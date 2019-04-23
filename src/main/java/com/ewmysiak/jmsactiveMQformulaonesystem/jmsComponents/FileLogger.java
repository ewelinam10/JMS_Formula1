package com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import java.util.concurrent.CountDownLatch;

public class FileLogger {
    //zapisuje do pliku

    private static final Logger LOGGER =
            LoggerFactory.getLogger(FileLogger.class);

    @JmsListener(destination = "filelogger.q")
    public void receive(InfoCar infoCar) {
        LOGGER.info("FileLogger : Zapisano do pliku !");
        //LOGGER.info("received message2='{}'", message);
    }
}
