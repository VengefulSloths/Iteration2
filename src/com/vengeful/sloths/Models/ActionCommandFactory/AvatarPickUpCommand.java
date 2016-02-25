package com.vengeful.sloths.Models.ActionCommandFactory;

import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Utility.Coord;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by luluding on 2/1/16.
 */
public class AvatarPickUpCommand extends PickUpCommand {

    Tile tile;


    public AvatarPickUpCommand(Map map, Coord dropLoc, Entity entity, TakeableItem item){
        super(map, dropLoc, entity, item);
        tile = map.getTile(dropLoc);
    }

    @Override
    public void execute() {
        InventoryItem toBePickedUp = item.getInventorpRep();
        this.entity.getInventory().addItem(toBePickedUp);
        this.tile.removeMapItem(this.item);
    }
}
