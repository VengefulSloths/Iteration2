package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.InventoryTakeableItemFactory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by zach on 3/11/16.
 */
public class PiggyDropTotemCommand {
    TakeableItem itemToDrop;
    Coord dropLoc;
    Entity entity;
    Map map;

    public PiggyDropTotemCommand(TakeableItem itemToDrop, Coord dropLoc, Entity entity, Map map){
        this.itemToDrop = itemToDrop;
        this.dropLoc = dropLoc;
        this.entity = entity;
        this.map = map;
    }

    public void execute(){
        this.map.getActiveMapArea().getTile(this.dropLoc).addTakeableItem(this.itemToDrop);
        this.itemToDrop.setLocation(this.dropLoc);
        itemToDrop.accept(TemporaryVOCreationVisitor.getInstance());
    }
}
