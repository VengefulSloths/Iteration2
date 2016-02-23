package com.vengeful.sloths.Models.Map.Terrains;

import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 1/30/2016.
 */
public class Mountain extends Terrain {

    public Mountain(){
        this.canBeMovedOn = false;
    }

    public void accept(ModelVisitor v) {
        v.visitMountain(this);
    }
}
