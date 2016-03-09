package com.vengeful.sloths.Models.Stats.StatAddables;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 2/21/2016.
 */
public class BaseStatsAddable extends StatsAddable implements ModelVisitable {

    public BaseStatsAddable(int strength, int agility, int intellect, int hardiness, int movement){
        this.setStrength(strength);
        this.setAgility(agility);
        this.setIntellect(intellect);
        this.setHardiness(hardiness);
        this.setMovement(movement);
    }
    public void accept(ModelVisitor mv){
        super.accept(mv);
    }
}
