package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.InventoryTakeableItemFactory;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Models.Map.Map;

/**
 * Created by luluding on 3/2/16.
 */
public class EntityDropCommand {
    //don't need to implement alertable cuz won't be on tick
    InventoryItem itemToDrop;
    Coord dropLoc;
    Entity entity;
    Map map;

    public EntityDropCommand(InventoryItem itemToDrop, Coord dropLoc, Entity entity, Map map){
        this.itemToDrop = itemToDrop;
        this.dropLoc = dropLoc;
        this.entity = entity;
        this.map = map;
    }

    public void execute(){
        TakeableItem takeableRep = InventoryTakeableItemFactory.getInstance().createTakeableRep(itemToDrop, dropLoc);
        this.map.getActiveMapArea().getTile(this.dropLoc).addTakeableItem(takeableRep);
        entity.getInventory().removeItem(itemToDrop);

        //TODO: debug line, remove
        System.out.println("Drop item " + itemToDrop.getItemName() + " at location: " + dropLoc.getR() + " " + dropLoc.getS());
        System.out.println("After drop: Inventory size: " + entity.getInventory().getCurrentSize());
        System.out.println("After drop: Items in entity's inventory:");
        for(int i = 0; i < entity.getInventory().getCurrentSize(); i++){
            System.out.println(entity.getInventory().getItem(i).getItemName());
        }

        //Don't need alertDrop because takeableVO is created in InventoryTakeableItemFactory
    }

}
