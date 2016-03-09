package com.vengeful.sloths.Models.Stats.StatAddables;


import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 2/21/2016.
 */
public class HardinessAddable extends StatsAddable implements ModelVisitable {

    public HardinessAddable(int hardiness){
        this.setHardiness(hardiness);
    }
    public void accept(ModelVisitor mv){
        super.accept(mv);
    }
}
