package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

import java.util.ArrayList;

/**
 * Created by luluding on 2/21/16.
 */
public class BuffOverTime extends Buff implements ModelVisitable{

    public BuffOverTime(ArrayList<EntityObserver> entityObservers, String name) {
        super(entityObservers, name);
    }

    @Override
    public void doApply(Stats stats) {

    }

    @Override
    public void doRemove(Stats stats) {

    }

    @Override
    public StatsAddable getBuff() {
        return null;
    }

    @Override
    public boolean applyOnTick(Stats stats) {
        return false;
    }
}
