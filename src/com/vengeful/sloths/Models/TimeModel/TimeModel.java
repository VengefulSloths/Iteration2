package com.vengeful.sloths.Models.TimeModel;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by John on 1/31/2016.
 */
public class TimeModel {

    private List<TimedObject> alertables;

    private static final TimeModel instance = new TimeModel();

    private TimeModel(){
        alertables = Collections.synchronizedList(new CopyOnWriteArrayList<TimedObject>()); //copy on write for concurrent access of 2 threads
    }
    //decremenets timedobjects and calls alterables execute when ticks == 0
    public void tick(){
        for (Iterator<TimedObject> iterator = alertables.iterator(); iterator.hasNext();){
            TimedObject object = iterator.next();
            ////System.out.Println(object.toString());
            if(object.decrement()){
                object.execute();
                ////System.out.Println("executing");
                alertables.remove(object);
            }
        }
    }

    public void registerAlertable(Alertable alertable, int ticks){
        alertables.add(new TimedObject(alertable, ticks));
    }
    private static TimeModel ourInstance = new TimeModel();

    public static TimeModel getInstance() {
        return instance;
    }


}
