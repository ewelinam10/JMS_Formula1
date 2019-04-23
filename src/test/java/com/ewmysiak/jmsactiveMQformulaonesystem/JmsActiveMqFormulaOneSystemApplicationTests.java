package com.ewmysiak.jmsactiveMQformulaonesystem;

import com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents.InfoCar;
import com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents.PitStop;
import com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents.FileLogger;
import com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents.CarMonitor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsActiveMqFormulaOneSystemApplicationTests {

    @Autowired
    private CarMonitor carMonitor;


    @Test
    public void testReceive() throws Exception {

       // carMonitor.send("To jest super nowa wiadomosc! :)) HAVE A NICE DAY",infoCar);
    }

}



