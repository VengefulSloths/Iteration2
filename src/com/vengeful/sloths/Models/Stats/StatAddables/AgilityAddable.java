package com.vengeful.sloths.Models.Stats.StatAddables;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 2/21/2016.
 */
public class AgilityAddable extends StatsAddable implements ModelVisitable {

    public AgilityAddable(int agility){
        this.setAgility(agility);
    }

    public void accept(ModelVisitor mv){
        super.accept(mv);
    }
}
