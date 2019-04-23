package com.ewmysiak.jmsactiveMQformulaonesystem.jmsComponents;

import org.springframework.scheduling.annotation.Scheduled;

import java.io.Serializable;
import java.util.Random;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class InfoCar implements Serializable {

    private float enigneHeat;
    private double tirePress;
    private float oilPres;
    private long currenDateTime;
    private Random random;

    public InfoCar() {
        random = new Random();
        setNewInfoCarParams();
    }

    public void setNewInfoCarParams(){
        //method generate random props but rarely bad props
        int onlyGodprops = random.nextInt(5);
        if(onlyGodprops!=4){
            setProperProps();
        }else{
            setRandomProps();
        }
        this.currenDateTime = System.currentTimeMillis();

    }
    private void setProperProps(){
        this.enigneHeat = getRandomRange(CarProperties.ENGINE_HEAT_LOWER_LIMIT,CarProperties.ENGINE_HEAT_UPPER_LIMIT);
        this.tirePress = (random.nextInt(6)) /(double)10 + getRandomRange(CarProperties.TIRE_PRESS_LOWER_LIMIT,CarProperties.TIRE_PRESS_UPPER_LIMIT);
        this.oilPres = getRandomRange(CarProperties.OIL_PRES_LOWER__LIMIT,CarProperties.OIL_PRES_UPPER_LIMIT);
    }
    private void setRandomProps(){
        this.enigneHeat = random.nextInt(150) + 60;
        this.tirePress =random.nextFloat() + random.nextInt(4);
        this.oilPres = random.nextInt(10);
        this.currenDateTime = System.currentTimeMillis();
    }

    private int getRandomRange(int start, int end){
        return random.nextInt(end - start) + start;
    }
    public double getTirePress() {
        return tirePress;
    }

    public void setTirePress(double tirePress) {
        this.tirePress = tirePress;
    }

    public float getOilPres() {
        return oilPres;
    }

    public void setOilPres(float oilPres) {
        this.oilPres = oilPres;
    }

    public long getCurrenDateTime() {
        return currenDateTime;
    }

    public void setCurrenDateTime(long currenDateTime) {
        this.currenDateTime = currenDateTime;
    }

    public float getEnigneHeat() {
        return enigneHeat;
    }

    public void setEnigneHeat(float enigneHeat) {
        this.enigneHeat = enigneHeat;
    }

}
