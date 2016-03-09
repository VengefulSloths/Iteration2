package com.vengeful.sloths.Models.Stats.StatAddables;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 2/21/2016.
 */
public class IntellectAddable extends StatsAddable implements ModelVisitable {

    public IntellectAddable(int intellect){
        this.setIntellect(intellect);
    }
    public void accept(ModelVisitor mv){
        super.accept(mv);
    }
}
