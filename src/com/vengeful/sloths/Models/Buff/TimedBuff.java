package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

import java.util.ArrayList;

/**
 * Created by Alex on 3/12/2016.
 */
public class TimedBuff extends Buff{
    private int duration;
    private int initialDuration;
    private StatsAddable statsAddable;
    private BuffManager owner;

    public TimedBuff(ArrayList<EntityObserver> entityObservers, BuffManager owner, StatsAddable buff, String name, int duration) {
        super(entityObservers, name);
        this.initialDuration = duration;
        this.duration = duration;
        this.statsAddable = buff;
        this.owner = owner;
    }

    @Override
    public void doApply(Stats stats) {
        this.duration = this.initialDuration;
        stats.add(statsAddable);
    }

    @Override
    public void doRemove(Stats stats) {
        stats.subtract(statsAddable);
    }

    @Override
    public StatsAddable getBuff() {
        return statsAddable;
    }

    public boolean applyOnTick(Stats stats){
        System.out.println(duration);
        this.duration -= 1;
        if(duration <= 0){
            this.owner.removeBuff(this);
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
