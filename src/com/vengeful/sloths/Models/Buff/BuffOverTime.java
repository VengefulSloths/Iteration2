package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/21/16.
 */
public class BuffOverTime extends Buff implements ModelVisitable{


    @Override
    public void apply(Stats stats) {

    }

    @Override
    public void remove(Stats stats) {

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
