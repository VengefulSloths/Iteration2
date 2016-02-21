package com.vengeful.sloths.AreaView;

/**
 * Created by Alex on 2/21/2016.
 */
public class ViewTime {
    private long currentTimeMilli;
    private static ViewTime instance = null;

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
    }
    public long getCurrentTimeMilli() {
        return this.currentTimeMilli;
    }
}