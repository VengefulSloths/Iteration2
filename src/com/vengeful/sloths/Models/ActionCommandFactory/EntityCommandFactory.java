package com.vengeful.sloths.Models.ActionCommandFactory;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by zach on 1/30/16.
 */
public abstract class EntityCommandFactory extends ActionCommandFactory {

    public EntityCommandFactory(Map map) {
        super(map);
    }

    
    public MovementCommand createMovementCommand(Coord dst) {
        // @TODO: Not implemented in this iteration
        return null;
    }

    @Override
    public DropCommand createDropCommand(InventoryItem itemToDrop, Coord dropLoc, Entity entity) {
        return null;
    }

    @Override
    public PickUpCommand createPickUpCommand(Coord dropLoc, Entity entity, TakeableItem item) {
        return null;
    }
}
