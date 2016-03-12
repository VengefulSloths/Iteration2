package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.StatAddables.GenericStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StrengthAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class Buff implements ModelVisitable{

    public abstract void apply(Stats stats);

    public abstract void remove(Stats stats);

    public void modifyDamage(StatsAddable statsAddable) {
        //default do nothing
    }

    public abstract StatsAddable getBuff();

    public abstract boolean applyOnTick(Stats stats);

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitBuff(this);
    }

}
