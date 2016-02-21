package com.vengeful.sloths.Models.Effects;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by John on 1/30/2016.
 */
public abstract class EffectCommand implements Alertable {
    protected Entity entity;

    //Used to check if entity is still on the same tile without the need for AE to know the tile
    protected Coord currentLoc;

    public abstract void execute();

//    public void saveMe(SaveManager sm, int ws){
//        sm.writeVariableLine(ws,"entity", "null", false);
//        sm.writeVariableLine(ws,"Coord","(" +  currentLoc.getX() + "," + currentLoc.getY()+ ")", true);
//    }


}
