package com.vengeful.sloths.Controllers.ControllerManagers;

import com.vengeful.sloths.Controllers.ActionController.NonAggressiveNPCActionController;
import com.vengeful.sloths.Controllers.ActionController.PiggyActionController;
import com.vengeful.sloths.Controllers.SearchingController.NonAggresssiveNPCSearchingController;
import com.vengeful.sloths.Controllers.SearchingController.PiggySearchingController;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by John on 3/14/2016.
 */
public class NonAggressiveNPCControllerManager extends NPCControllerManager {
    private boolean dead = false;

    public NonAggressiveNPCControllerManager(MapArea mapArea, Entity entity, Direction dir, int numSteps){

        super(mapArea, entity);
        this.setSearchingController(new NonAggresssiveNPCSearchingController(mapArea, entity));
        this.setActionController(new NonAggressiveNPCActionController(entity, dir, numSteps));
        this.setMapArea(mapArea);
    }


    @Override
    public void tick() {
        if(!dead) {
            if (this.getTicks() % 30 == 0) {
                if (this.getMapArea().equals(Map.getInstance().getActiveMapArea())) {
                    //System.out.println("beginning of tick");
                    if (getSearchingController() != null) {
                        this.getSearchingController().search(4);//hardcoded to 2 right now
                    }
                    //System.out.println("highest priority target is :" + searchingController.getHighestPriorityTarget());
                    if (getActionController() != null) {
                        this.getActionController().action(this.getSearchingController().getHighestPriorityTarget());
                    }
                    //movementController
                } else {
                    //you could put reset the npc logic here
                }
            }
            this.setTicks(this.getTicks() + 1);
        }

    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;

    }
}
