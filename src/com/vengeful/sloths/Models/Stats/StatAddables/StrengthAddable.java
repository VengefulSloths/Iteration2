package com.vengeful.sloths.Models.Stats.StatAddables;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 2/21/2016.
 */

//Here is an example addable, all will implement every base stat, but
public class StrengthAddable extends StatsAddable implements ModelVisitable {

    public StrengthAddable(int strength){
        this.setStrength(strength);
    }
    public void accept(ModelVisitor mv){
        super.accept(mv);
    }

}
