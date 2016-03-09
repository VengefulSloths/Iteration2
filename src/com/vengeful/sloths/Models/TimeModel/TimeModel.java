package com.vengeful.sloths.Models.TimeModel;

import com.vengeful.sloths.AreaView.vAlertable;
import com.vengeful.sloths.AreaView.vCommand;
import com.vengeful.sloths.Utility.RealTuple;
import com.vengeful.sloths.Utility.Tuple;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by John on 1/31/2016.
 */
public class TimeModel {

    private List<TimedObject> alertables;
    private List<Tickable> tickables;


    //Alex's custom lamda alertables

    private int currentTick = 0;
    private Comparator<RealTuple<Integer, mCommand>> comparator = new Comparator<RealTuple<Integer, mCommand>>() {
        @Override
        public int compare(RealTuple<Integer, mCommand> o1, RealTuple<Integer, mCommand> o2) {
            return o1.x < o2.x ? -1: 1;
        }
    };


    private PriorityQueue<RealTuple<Integer, mCommand>> waitingList = new PriorityQueue<>(10, comparator);



    private static final TimeModel instance = new TimeModel();

    private TimeModel(){
        alertables = Collections.synchronizedList(new CopyOnWriteArrayList<TimedObject>()); //copy on write for concurrent access of 2 threads
        tickables = Collections.synchronizedList(new CopyOnWriteArrayList<Tickable>());
    }
    //decremenets timedobjects and calls alterables execute when ticks == 0
    public void tick(){
        for (Iterator<TimedObject> iterator = alertables.iterator(); iterator.hasNext();){
            TimedObject object = iterator.next();
            if(object.decrement()){// if its time to execute the object
                object.execute();
                alertables.remove(object);
            }
        }
        for (Iterator<Tickable> iterator = tickables.iterator(); iterator.hasNext();) {
            Tickable object = iterator.next();
            object.tick();
        }

        //This is alexs lambda alerts
        ++currentTick;
        while (!waitingList.isEmpty() && waitingList.peek().x < currentTick) {
            waitingList.poll().y.execute();
        }
    }

    public void registerAlertable(mCommand m, int ticks) {
        waitingList.add(new RealTuple<>(ticks + currentTick, m));
    }
    public void registerAlertable(Alertable alertable, int ticks){
        alertables.add(new TimedObject(alertable, ticks));
    }
    public void registerTickable(Tickable tickable){tickables.add(tickable);}
    public void removeTickable(Tickable tickable){tickables.remove(tickable);}
    private static TimeModel ourInstance = new TimeModel();

    public static TimeModel getInstance() {
        return instance;
    }


}
