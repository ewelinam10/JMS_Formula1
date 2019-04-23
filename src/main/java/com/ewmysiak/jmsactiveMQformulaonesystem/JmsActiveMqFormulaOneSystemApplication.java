package com.ewmysiak.jmsactiveMQformulaonesystem;

import com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents.CarMonitor;
import com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents.CarProperties;
import com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@SpringBootApplication
@EnableScheduling
public class JmsActiveMqFormulaOneSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsActiveMqFormulaOneSystemApplication.class, args);

		LOGGER.info("Main application running");
	}

}
