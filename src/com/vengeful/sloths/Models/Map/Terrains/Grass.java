package com.vengeful.sloths.Models.Map.Terrains;

import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 1/30/2016.
 */
public class Grass extends Terrain {

    public Grass(){
        canBeMovedOn = true;
    }

    public void accept(ModelVisitor v) {
        v.visitGrass(this);
    }
}
