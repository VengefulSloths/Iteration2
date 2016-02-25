package com.vengeful.sloths.Models.Occupation;


import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.Stats;
/**
 * Created by zach on 1/30/16.
 */
public abstract class Occupation implements ModelVisitable{
    public abstract void levelUp(Stats stats);
    public abstract void accept(ModelVisitor mv);
}
