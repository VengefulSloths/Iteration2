package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

import java.util.ArrayList;

/**
 * Created by Alex on 3/10/2016.
 */
public class PermanantBuff extends Buff {
    private StatsAddable statsAddable;

    public PermanantBuff(ArrayList<EntityObserver> entityObservers, String name, StatsAddable statsAddable) {
        super(entityObservers, name);
        this.statsAddable = statsAddable;
    }


    @Override
    public void doApply(Stats stats) {
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

    @Override
    public boolean applyOnTick(Stats stats) {
        //Do nothing because this is ok
        return false;
    }
}
