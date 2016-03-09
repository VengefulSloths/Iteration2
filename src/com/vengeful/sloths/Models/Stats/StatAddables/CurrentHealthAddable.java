package com.vengeful.sloths.Models.Stats.StatAddables;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 2/29/2016.
 */
public class CurrentHealthAddable extends StatsAddable implements ModelVisitable {

    public CurrentHealthAddable(int health){
        this.setCurrentHealth(health);
    }
    public void accept(ModelVisitor mv){
        super.accept(mv);
    }
}
