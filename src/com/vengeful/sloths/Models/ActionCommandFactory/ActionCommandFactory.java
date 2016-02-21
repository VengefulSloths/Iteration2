package com.vengeful.sloths.Models.ActionCommandFactory;

import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by zach on 1/30/16.
 */
public abstract class ActionCommandFactory {

    Map map;

    // movement command
    // drop command

    public ActionCommandFactory(Map map) {
        this.map = map;
    }

    public abstract MovementCommand createMovementCommand(Coord src, Coord dst, Direction dir, Entity entity, int movementSpeed);
    public abstract DropCommand createDropCommand(InventoryItem itemToDrop, Coord dropLoc, Entity entity);
    public abstract PickUpCommand createPickUpCommand(Coord dropLoc, Entity entity);
    public abstract DieCommand createDieCommand(Coord location, Entity entity);
}
