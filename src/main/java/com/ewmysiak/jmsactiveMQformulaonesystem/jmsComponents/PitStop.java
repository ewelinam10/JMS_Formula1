package com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import javax.sound.sampled.Line;
import java.util.concurrent.TimeUnit;

public class PitStop {
    //aplikacja monitorujaca i rozsylajaca informacje do kierowcy i mechanikow
    public enum ValidCarProps{
        CORRECT,
        BAD,
        DANGEROUS
    }

    @Autowired
    private CarMonitor carMonitor;
    @Autowired
    private JmsTemplate jmsTemplate;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(PitStop.class);

    private float enigneHeat;
    private double tirePress;
    private float oilPres;

    @JmsListener(destination = "pitstop.q")
    public void receive(InfoCar infoCar) {

        enigneHeat = infoCar.getEnigneHeat();
        tirePress = infoCar.getTirePress();
        oilPres = infoCar.getOilPres();

        //testAsyncMsg();

        LOGGER.info("PitStop : Odebrano w aplikacji monitorujacej następującą informacje o pracy silnika (czas : '{}') : \n" +
                "    ------->       Temperatura silnika: '{}'\n" +
                "    ------->       Ciśnienie opon: '{}' \n" +
                "    ------->       Ciśnienie oleju : '{}'" +
                "",new Object[]{infoCar.getCurrenDateTime(),enigneHeat,tirePress,oilPres});

        ValidCarProps validCarProps = checkCarProps();
        sendToDriver("Zjezdzaj na pobocze!");
        if(validCarProps.equals(ValidCarProps.BAD)){
          //  sendToDriver("Zjezdzaj na pobocze!");
        }else if(validCarProps.equals(ValidCarProps.DANGEROUS)){
            //sendToDriver("Zjezdzaj na pobocze!");
            sendToEngineers("Bądźcie przygotowani na zajazd auta do pitStopu - auto wymaga naprawy!");
        }
    }
    private ValidCarProps checkCarProps(){
        if(enigneHeat > CarProperties.ENGINE_HEAT_UPPER_LIMIT || enigneHeat < CarProperties.ENGINE_HEAT_LOWER_LIMIT){
            if(enigneHeat > CarProperties.ENGINE_HEAT_DANGEROUS_UPPER_LIMIT || enigneHeat < CarProperties.ENGINE_HEAT_DANGEROUS_LOWER_LIMIT){
                return ValidCarProps.DANGEROUS;
            }else{
                return ValidCarProps.BAD;
            }
        }else if(tirePress > CarProperties.TIRE_PRESS_UPPER_LIMIT || tirePress < CarProperties.TIRE_PRESS_LOWER_LIMIT){
            if(tirePress > CarProperties.TIRE_PRESS_DANGEROUS_UPPER_LIMIT || tirePress < CarProperties.TIRE_PRESS_DANGEROUS_LOWER_LIMIT){
                return ValidCarProps.DANGEROUS;
            }else{
                return ValidCarProps.BAD;
            }
        }else if(oilPres > CarProperties.OIL_PRES_UPPER_LIMIT || oilPres < CarProperties.OIL_PRES_LOWER__LIMIT){
            if(oilPres > CarProperties.OIL_PRES_UPPER_DANGEROUS_LIMIT || oilPres < CarProperties.OIL_PRES_LOWER_DANGEROUS_LIMIT){
                return ValidCarProps.DANGEROUS;
            }else{
                return ValidCarProps.BAD;
            }
        }else{
            return ValidCarProps.CORRECT;
        }
    }

    private void sendToDriver(String message) {
        LOGGER.info("PitStop : Wysyłanie wiadomości do kierowcy - ostrzeżenie!");
        jmsTemplate.convertAndSend("driver.q", message);
    }
    private void sendToEngineers(String message) {
        LOGGER.info("PitStop : Wysyłanie wiadomości do mechaników = sytuacja krytyczna !");
        jmsTemplate.convertAndSend("engineer.topic", message);
    }

    private void testAsyncMsg(){
        //ustawienie timera pozwala na sprawdzenie czy wysylane wiadomosci sa rzeczywiscie asynchronicznie wysylane
        try {
            LOGGER.info("Poczatek timera");
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Koniec timera");
    }
}