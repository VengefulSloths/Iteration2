package com.vengeful.sloths.Models.Map.Terrains;

import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 1/30/2016.
 */
public class Water extends Terrain {
    private boolean sky = false;
    public Water(){
        this.canBeMovedOn = false;
        sky = false;
    }


    public void accept(ModelVisitor v) {
        v.visitWater(this);
    }

    public boolean isSky() {
        return sky;
    }

    public void setSky(boolean sky) {
        this.sky = sky;
    }
}
