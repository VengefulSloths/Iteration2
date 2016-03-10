package com.vengeful.sloths.Controllers.ControllerManagers;

import com.vengeful.sloths.Controllers.ActionController.ActionController;
import com.vengeful.sloths.Controllers.ActionController.PiggyActionController;
import com.vengeful.sloths.Controllers.MovementController.LandMovementController;
import com.vengeful.sloths.Controllers.MovementController.MovementController;
import com.vengeful.sloths.Controllers.SearchingController.PiggySearchingController;
import com.vengeful.sloths.Controllers.SearchingController.SearchingController;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Entity.NPC;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

/**
 * Created by zach on 3/4/16.
 */
public class PiggyControllerManager extends NPCControllerManager {

    public PiggyControllerManager(MapArea mapArea, Entity entity){
        super(mapArea, entity);
    }


    @Override
    public void tick() {
        if(this.getTicks() % 15 == 0) {
            if(this.getMapArea().equals(Map.getInstance().getActiveMapArea())) {
                //System.out.println("beginning of tick");
                this.getSearchingController().search(4);//hardcoded to 2 right now
                //System.out.println("highest priority target is :" + searchingController.getHighestPriorityTarget());
                this.getActionController().action(this.getSearchingController().getHighestPriorityTarget());
                //movementController
            }else{
                //you could put reset the npc logic here
            }
        }
        this.setTicks(this.getTicks()+ 1);

    }
}
