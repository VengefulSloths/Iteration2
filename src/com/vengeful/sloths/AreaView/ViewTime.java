package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.Utility.Tuple;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Alex on 2/21/2016.
 */
public class ViewTime {
    private long currentTimeMilli;
    private static ViewTime instance = null;
    private Comparator<Tuple<Long, vAlertable>> comparator = new Comparator<Tuple<Long, vAlertable>>() {
        @Override
        public int compare(Tuple<Long, vAlertable> o1, Tuple<Long, vAlertable> o2) {
            return o1.x < o2.x ? -1: 1;
        }
    };
    private PriorityQueue<Tuple<Long, vAlertable>> waitingList = new PriorityQueue<>(10, comparator);
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
        while (!waitingList.isEmpty() && waitingList.peek().x < this.currentTimeMilli) {
            waitingList.poll().y.vAlert();
        }
    }
    public long getCurrentTimeMilli() {
        return this.currentTimeMilli;
    }

    public void registerAlert(vAlertable alertable, long timeTillAlert) {
        waitingList.add(new Tuple<>(timeTillAlert + this.currentTimeMilli, alertable));
    }
}