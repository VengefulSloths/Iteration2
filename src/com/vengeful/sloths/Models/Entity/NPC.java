package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Controllers.ControllerManagers.NPCControllerManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.Location;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class NPC extends Entity{

    protected int timeToRespawn;
    private NPCControllerManager controllerManager;
    //pass in stats
    public NPC(String name, Stats stats){
        super(name, stats);
    }

    public void talk(){
        //create talk command
    }
    public NPC(){}

    public int getTimeToRespawn() {
        return timeToRespawn;
    }

    public void setTimeToRespawn(int timeToRespawn) {
        this.timeToRespawn = timeToRespawn;
    }

    public NPCControllerManager getControllerManager() {
        return controllerManager;
    }

    public void setControllerManager(NPCControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }
}
