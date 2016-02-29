package com.vengeful.sloths.Controllers.ControllerManagers;

import com.vengeful.sloths.Controllers.ActionController.ActionController;
import com.vengeful.sloths.Controllers.ActionController.AggressiveNPCActionController;
import com.vengeful.sloths.Controllers.MovementController.LandMovementController;
import com.vengeful.sloths.Controllers.MovementController.MovementController;
import com.vengeful.sloths.Controllers.SearchingController.AggressiveNPCSearchingController;
import com.vengeful.sloths.Controllers.SearchingController.SearchingController;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;

/**
 * Created by John on 2/28/2016.
 */
public class AggressiveNPCControllerManager extends NPCControllerManager{

    private SearchingController searchingController;
    private MovementController movementController;
    private ActionController actionController;

    public AggressiveNPCControllerManager(Map map, Entity entity){
        this.setEntity(entity);
        this.setSearchingController(new AggressiveNPCSearchingController(map, entity));
        this.setActionController(new AggressiveNPCActionController());
        this.setMovementController(new LandMovementController());


    };

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
}
