package com.vengeful.sloths.Models.Map.Terrains;

import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 1/30/2016.
 */
public class Water extends Terrain {
    public Water(){
        this.canBeMovedOn = false;
    }

    public void accept(ModelVisitor v) {
        v.visitWater(this);
    }
}
