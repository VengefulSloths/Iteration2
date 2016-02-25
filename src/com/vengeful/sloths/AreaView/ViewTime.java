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
    private Comparator<Tuple<Long, vAlertable, vCommand>> comparator = new Comparator<Tuple<Long, vAlertable, vCommand>>() {
        @Override
        public int compare(Tuple<Long, vAlertable, vCommand> o1, Tuple<Long, vAlertable, vCommand> o2) {
            return o1.x < o2.x ? -1: 1;
        }
    };
    private PriorityQueue<Tuple<Long, vAlertable, vCommand>> waitingList = new PriorityQueue<>(10, comparator);
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
            Tuple<Long, vAlertable, vCommand> current = waitingList.poll();
            current.z.execute();
            current.y.vAlert();
        }
    }
    public long getCurrentTimeMilli() {
        return this.currentTimeMilli;
    }

    public void registerAlert(long timeTillAlert, vCommand command) {
        waitingList.add(new Tuple<>(timeTillAlert + this.currentTimeMilli,
                new vAlertable() {
                    @Override
                    public void vAlert() {

                    }
                },
                command));

    }

    public void registerAlert(vAlertable alertable, long timeTillAlert, vCommand command) {
        waitingList.add(new Tuple<>(timeTillAlert + this.currentTimeMilli, alertable, command));
    }
    public void registerAlert(vAlertable alertable, long timeTillAlert) {
        waitingList.add(new Tuple<>(timeTillAlert + this.currentTimeMilli,
                alertable,
                new vCommand() {
                    @Override
                    public void execute() {
                        //do nothing
                    }
                }));
    }
}