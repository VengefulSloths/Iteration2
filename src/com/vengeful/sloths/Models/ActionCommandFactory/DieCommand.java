package com.vengeful.sloths.Models.ActionCommandFactory;

import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by John on 2/7/2016.
 */
public abstract class DieCommand implements Alertable {

    Map map;
    Coord coord;
    Entity entity;

    public DieCommand(Map map, Coord dropLoc, Entity entity){
        this.map = map;
        this.coord = dropLoc;
        this.entity = entity;

        TimeModel.getInstance().registerAlertable(this, 0);
    }


    @Override
    public abstract void execute();
}
