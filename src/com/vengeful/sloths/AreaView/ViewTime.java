package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.Utility.RealTuple;
import com.vengeful.sloths.Utility.Tuple;

import java.util.*;

/**
 * Created by Alex on 2/21/2016.
 */
public class ViewTime {
    private long currentTimeMilli;
    private static ViewTime instance = null;
    private Comparator<RealTuple<Long, vCommand>> comparator = new Comparator<RealTuple<Long,vCommand>>() {
        @Override
        public int compare(RealTuple<Long, vCommand> o1, RealTuple<Long, vCommand> o2) {
            if (o1 == o2) return 0;
            return o1.x < o2.x ? -1: 1;
        }
    };


    private ArrayList<RealTuple<Long, vCommand>> staging = new ArrayList<>();
    private PriorityQueue<RealTuple<Long, vCommand>> waitingList = new PriorityQueue<>(10, comparator);
    private ViewTime() {
        this.currentTimeMilli = System.currentTimeMillis();
    }

    public static ViewTime getInstance() {
        if (instance == null) {
            instance = new ViewTime();
        }
        return instance;
    }

    public void tick() {
        this.currentTimeMilli = System.currentTimeMillis();

        //Move staging to waiting list
        while (!staging.isEmpty())
            waitingList.add(staging.remove(0));

        while (!waitingList.isEmpty() && waitingList.peek().x < this.currentTimeMilli) {
            RealTuple<Long, vCommand> current = waitingList.poll();
            current.y.execute();
        }
    }
    public long getCurrentTimeMilli() {
        return this.currentTimeMilli;
    }

    public void registerAlert(long timeTillAlert, vCommand command) {
        staging.add(new RealTuple<>(timeTillAlert + this.currentTimeMilli, command));

    }

    public void reset(){
        //staging = new ArrayList<>();
        waitingList = new PriorityQueue<>(10, comparator);
    }
}