package com.vengeful.sloths.Controllers.ControllerManagers;

import com.vengeful.sloths.Controllers.ActionController.ActionController;
import com.vengeful.sloths.Controllers.MovementController.MovementController;
import com.vengeful.sloths.Controllers.SearchingController.SearchingController;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.TimeModel.Tickable;

/**
 * Created by John on 2/28/2016.
 */
public abstract class NPCControllerManager implements Tickable{

    //make a constructor that makes new ones of these and sets them for the implementations
    private Entity entity;

    public NPCControllerManager(){};
    public NPCControllerManager(Entity entity){
        this.setEntity(entity);
    }

    public abstract void setSearchingController(SearchingController searchingController);
    public abstract void setMovementController(MovementController movementController);
    public abstract void setActionController(ActionController actionController);

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }


}
