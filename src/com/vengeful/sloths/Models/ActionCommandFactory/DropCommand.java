package com.vengeful.sloths.Models.ActionCommandFactory;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.*;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by zach on 1/30/16.
 */
public abstract class DropCommand implements Alertable {

    Map map;
    Coord dropLocation;
    InventoryItem itemToDrop;
    Entity entity;

    public DropCommand(Map map, InventoryItem itemToDrop, Coord dropLoc, Entity entity){
        this.map = map;
        this.dropLocation = dropLoc;
        this.itemToDrop = itemToDrop;
        this.entity = entity;

        TimeModel.getInstance().registerAlertable(this, 0);
    }


    @Override
    public abstract void execute();
}
