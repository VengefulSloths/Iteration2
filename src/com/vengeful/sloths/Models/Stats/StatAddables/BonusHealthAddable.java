package com.vengeful.sloths.Models.Stats.StatAddables;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 2/21/2016.
 */
public class BonusHealthAddable extends StatsAddable implements ModelVisitable {


    public BonusHealthAddable(int health){
        this.setBonusHealth(health);
    }
    public void accept(ModelVisitor mv){
        super.accept(mv);
    }
}
