package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.SaveLoad.SaveManager;

/**
 * Created by John on 1/30/2016.
 */
public class Obstacle extends MapItem{

    @Override
    public boolean canMove(){
        return false;
    }

    public void interact(Entity entity){
        //maybe alert user he cannot move here
    }

//    public void saveMe(SaveManager sm, int ws) {
//        sm.writeClassLine(ws, "Obstacle");
//        super.saveMe(sm, ws);
//    }
}
