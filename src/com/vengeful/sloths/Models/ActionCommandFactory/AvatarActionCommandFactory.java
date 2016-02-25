package com.vengeful.sloths.Models.ActionCommandFactory;


import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by zach on 1/30/16.
 */
public class AvatarActionCommandFactory extends ActionCommandFactory {

    public AvatarActionCommandFactory(Map map) {
        super(map);
    }


    @Override
    public MovementCommand createMovementCommand(Coord src, Coord dst, Direction dir, Entity avatar, int movementSpeed) {
        MovementCommand mc = new AvatarMovementCommand(map, src, dst, dir, avatar, movementSpeed);

        return mc;
    }

    @Override
    public DropCommand createDropCommand(InventoryItem itemToDrop, Coord dropLoc, Entity entity) { //Called in Avatar
        DropCommand dc = new AvatarDropCommand(map, itemToDrop, dropLoc, entity);
        return dc;
    }

    @Override
    public PickUpCommand createPickUpCommand(Coord dropLoc, Entity entity, TakeableItem item) {
        PickUpCommand pc = new AvatarPickUpCommand(map, dropLoc, entity, item);
        return pc;
    }

    @Override
    public DieCommand createDieCommand(Coord location, Entity entity){
        DieCommand die = new AvatarDieCommand(map, location, entity);
        return die;
    }

}
