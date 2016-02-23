package com.vengeful.sloths.Models.Map.Terrains;

import com.vengeful.sloths.Models.ModelVisitable;

/**
 * Created by John on 1/30/2016.
 */
public abstract class Terrain implements ModelVisitable {
    protected boolean canBeMovedOn;

    public Terrain(){
        canBeMovedOn = false;
    }

    public boolean canMove(){
        return canBeMovedOn;
    }
}
