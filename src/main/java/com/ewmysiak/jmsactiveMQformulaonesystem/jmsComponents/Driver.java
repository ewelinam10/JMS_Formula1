package com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.support.destination.DestinationResolver;

import javax.jms.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class Driver {
    //kierowca
    @Autowired
    private JmsTemplate jmsTemplate;

    MessageConsumer consumer = null;
    MessageProducer producer = null;
    private static final int TIMEOUT = 5000;
    private static final String queueBoss = "engineerBoss.q";
    private static final Logger LOGGER =
            LoggerFactory.getLogger(Driver.class);

    @JmsListener(destination = "driver.q")
    public void receive(String message) {
        LOGGER.info("Driver :  Kierowca odebrał następującą wiadomość:       ");
        LOGGER.info("MSG:'{}'  " , message);

        final String correlationId = UUID.randomUUID().toString();


        final DestinationResolver destinationResolver = jmsTemplate.getDestinationResolver();

        jmsTemplate.send(queueBoss, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                String msg = "Potrzebuję zajechać do pitStopu";
                 Destination replyQueue =
                        destinationResolver.resolveDestinationName( session, queueBoss+".response", false );
                TextMessage textMessage = session.createTextMessage( msg );
                textMessage.setJMSReplyTo(replyQueue);
               // textMessage.setJMSCorrelationID( correlationId );
                return textMessage;
            }
        });
    }

    @JmsListener(destination = queueBoss + ".response")
    public void receive2(String message) {
        LOGGER.info("Kierowca " + message.toString() + "zajechać do pitstopu");
    }

    private static final class CorrelationIdPostProcessor implements MessagePostProcessor {

        private final String correlationId;

        public CorrelationIdPostProcessor( final String correlationId ) {
            this.correlationId = correlationId;
        }

        @Override
        public Message postProcessMessage( final Message msg ) throws JMSException {
            msg.setJMSCorrelationID( correlationId );
            return msg;
        }
    }
}


