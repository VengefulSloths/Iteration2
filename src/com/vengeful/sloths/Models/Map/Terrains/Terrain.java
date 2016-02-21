package com.vengeful.sloths.Models.Map.Terrains;

/**
 * Created by John on 1/30/2016.
 */
public abstract class Terrain {
    protected boolean canBeMovedOn;

    public Terrain(){
        canBeMovedOn = false;
    }

    public boolean canMove(){
        return canBeMovedOn;
    }
}
