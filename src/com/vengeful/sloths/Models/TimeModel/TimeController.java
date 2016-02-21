package com.vengeful.sloths.Models.TimeModel;

/**
 * Created by John on 1/30/2016.
 */
public class TimeController {

    private TimeModel timeModel = TimeModel.getInstance();

    public void tick(){
        Long startTime = System.currentTimeMillis();
        //do shit

        timeModel.tick();

        //delay to get 30 ticks per seconds
        Long deltaTime = System.currentTimeMillis() - startTime;
        deltaTime = 33L - deltaTime;
        if(deltaTime < 0L)deltaTime = 0L;

        try {
            Thread.sleep(deltaTime);
        }catch(InterruptedException e){
            //suck 1000 dicks
        }
    }
}
