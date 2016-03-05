package com.vengeful.sloths.Controllers.ControllerManagers;

import com.vengeful.sloths.Controllers.ActionController.ActionController;
import com.vengeful.sloths.Controllers.ActionController.PiggyActionController;
import com.vengeful.sloths.Controllers.MovementController.LandMovementController;
import com.vengeful.sloths.Controllers.MovementController.MovementController;
import com.vengeful.sloths.Controllers.SearchingController.PiggySearchingController;
import com.vengeful.sloths.Controllers.SearchingController.SearchingController;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

/**
 * Created by zach on 3/4/16.
 */
public class PiggyControllerManager extends NPCControllerManager {

    private SearchingController searchingController;
    private MovementController movementController;
    private ActionController actionController;
    private MapArea mapArea;
    private int ticks = 0;

    public PiggyControllerManager(MapArea mapArea, Entity entity){
        this.setMapArea(mapArea);
        this.setEntity(entity);
        this.setSearchingController(new PiggySearchingController(mapArea, entity));
        this.setActionController(new PiggyActionController(entity));
        this.setMovementController(new LandMovementController());
        TimeModel.getInstance().registerTickable(this);

    }

    public MapArea getMapArea() {
        return mapArea;
    }

    public void setMapArea(MapArea mapArea) {
        this.mapArea = mapArea;
    }

    @Override
    public void setSearchingController(SearchingController searchingController) {
        this.searchingController = searchingController;
    }

    @Override
    public void setMovementController(MovementController movementController) {
        this.movementController = movementController;
    }

    @Override
    public void setActionController(ActionController actionController) {
        this.actionController = actionController;
    }

    @Override
    public void tick() {
        if(ticks % 10 == 0) {
            if(mapArea.equals(Map.getInstance().getActiveMapArea())) {
                //System.out.println("beginning of tick");
                searchingController.search(5);//hardcoded to 5 right now
                System.out.println("highest priority target is :" + searchingController.getHighestPriorityTarget());
                actionController.action(searchingController.getHighestPriorityTarget());
                //movementController
            }else{
                //you could put reset the npc logic here
            }
        }
        ++ticks;

    }
}
