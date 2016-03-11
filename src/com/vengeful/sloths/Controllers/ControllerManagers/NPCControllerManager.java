package com.vengeful.sloths.Controllers.ControllerManagers;

import com.vengeful.sloths.Controllers.ActionController.ActionController;
import com.vengeful.sloths.Controllers.ActionController.PiggyActionController;
import com.vengeful.sloths.Controllers.MovementController.LandMovementController;
import com.vengeful.sloths.Controllers.MovementController.MovementController;
import com.vengeful.sloths.Controllers.SearchingController.PiggySearchingController;
import com.vengeful.sloths.Controllers.SearchingController.SearchingController;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Entity.NPC;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.TimeModel.Tickable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

/**
 * Created by John on 2/28/2016.
 */
public abstract class NPCControllerManager implements Tickable{

    private SearchingController searchingController;
    private MovementController movementController;
    private ActionController actionController;
    private MapArea mapArea;
    private int ticks = 0;
    //make a constructor that makes new ones of these and sets them for the implementations
    private Entity entity;

    public NPCControllerManager(){};
    public NPCControllerManager(MapArea mapArea, Entity entity){
        this.setEntity(entity);
        this.setMovementController(new LandMovementController());
        this.setMapArea(mapArea);
        TimeModel.getInstance().registerTickable(this);
        ((NPC) this.getEntity()).setControllerManager(this);
    }
    public NPCControllerManager(Entity entity){
        this.setEntity(entity);
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public MapArea getMapArea() {
        return mapArea;
    }

    public void setMapArea(MapArea mapArea) {
        this.mapArea = mapArea;
        if(searchingController != null) {
            this.searchingController.setMapArea(mapArea);
        }
    }


    public void setSearchingController(SearchingController searchingController) {
        this.searchingController = searchingController;
    }


    public void setMovementController(MovementController movementController) {
        this.movementController = movementController;
    }

    public void setActionController(ActionController actionController) {
        this.actionController = actionController;
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public ActionController getActionController() {
        return actionController;
    }

    public MovementController getMovementController() {
        return movementController;
    }

    public SearchingController getSearchingController() {
        return searchingController;
    }
}
