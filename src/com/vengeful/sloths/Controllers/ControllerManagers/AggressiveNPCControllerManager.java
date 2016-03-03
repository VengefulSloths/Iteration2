package com.vengeful.sloths.Controllers.ControllerManagers;

import com.vengeful.sloths.Controllers.ActionController.ActionController;
import com.vengeful.sloths.Controllers.ActionController.AggressiveNPCActionController;
import com.vengeful.sloths.Controllers.MovementController.LandMovementController;
import com.vengeful.sloths.Controllers.MovementController.MovementController;
import com.vengeful.sloths.Controllers.SearchingController.AggressiveNPCSearchingController;
import com.vengeful.sloths.Controllers.SearchingController.SearchingController;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

/**
 * Created by John on 2/28/2016.
 */
public class AggressiveNPCControllerManager extends NPCControllerManager{

    private SearchingController searchingController;
    private MovementController movementController;
    private ActionController actionController;
    private int ticks = 0;

    public AggressiveNPCControllerManager(Map map, Entity entity){
        this.setEntity(entity);
        this.setSearchingController(new AggressiveNPCSearchingController(map, entity));
        this.setActionController(new AggressiveNPCActionController(entity));
        this.setMovementController(new LandMovementController());
        TimeModel.getInstance().registerTickable(this);

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
        if(ticks % 30 == 0) {
            System.out.println("beginning of tick");
            searchingController.search(2);//hardcoded to 2 right now
            System.out.println("highest priority target is :" + searchingController.getHighestPriorityTarget());
            actionController.action(searchingController.getHighestPriorityTarget());
            //movementController
        }
        ++ticks;

    }
}
