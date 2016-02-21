package com.vengeful.sloths.Models.ActionCommandFactory;

import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.View.Observers.EntityObserver;

import java.util.Iterator;

/**
 * Created by luluding on 2/1/16.
 */
public class AvatarDropCommand extends DropCommand{

    public AvatarDropCommand(Map map, InventoryItem itemToDrop, Coord dropLoc, Entity entity){
        super(map, itemToDrop, dropLoc, entity);
    }

    @Override
    public void execute() {

        //System.out.Println(" I am the execute() of AvatarDropCommand");
        //System.out.Println("I am dropping item: " + itemToDrop.getItemName());
        Tile tile = map.getTile(dropLocation);
        tile.addMapItem(itemToDrop.getMapItemRep());
        entity.getInventory().removeItem(itemToDrop);


        Iterator<EntityObserver> iter = this.entity.entityObserverIterator();
        while (iter.hasNext()) {
            EntityObserver eo = iter.next();
            eo.alertDrop(dropLocation.getX(), dropLocation.getY(), itemToDrop.getMapItemRep());
        }


    }
}
