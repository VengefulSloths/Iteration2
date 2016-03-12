package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by Alex on 3/12/2016.
 */
public abstract class TimedBuff extends Buff{
    private int duration;

    public TimedBuff(int duration) {
        this.duration = duration;
    }

    public boolean applyOnTick(Stats stats){
        this.duration -= 1;
        if(duration <= 0){
            this.remove(stats);
            return true;
        }
        return false;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
