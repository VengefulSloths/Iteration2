package com.vengeful.sloths.Models.ActionCommandFactory;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by qianwen on 2/1/16.
 */
public abstract class PickUpCommand implements Alertable{

    Map map;
    Coord dropLocation;
    Entity entity;


    public PickUpCommand(Map map, Coord dropLoc, Entity entity){
        this.map = map;
        this.dropLocation = dropLoc;
        this.entity = entity;

        TimeModel.getInstance().registerAlertable(this, 0);
    }

    @Override
    public void execute() {}
}
