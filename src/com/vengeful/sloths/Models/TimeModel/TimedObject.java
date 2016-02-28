package com.vengeful.sloths.Models.TimeModel;

/**
 * Created by John on 1/30/2016.
 */
public class TimedObject {

    private Alertable alertable;
    private int ticks;

    public TimedObject(Alertable alertable, int ticks){
        this.ticks = ticks;
        this.alertable = alertable;
    }

    //boolean true if ticks = 0 and its time to execute/remove from list
    //this is called from time model
    public boolean decrement(){
        --ticks;
        return (ticks <= 0);
    }

    public void execute(){
        alertable.mAlert();
    }

}
