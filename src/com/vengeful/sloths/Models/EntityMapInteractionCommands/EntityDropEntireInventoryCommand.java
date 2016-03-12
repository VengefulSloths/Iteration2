package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.InventoryTakeableItemFactory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by zach on 3/9/16.
 */
public class EntityDropEntireInventoryCommand {
    Entity entity;

    public EntityDropEntireInventoryCommand(Entity entity){
        this.entity = entity;
    }

    public void execute() {
        TakeableItem takeableItem;
        int inventorySize = this.entity.getInventory().getCurrentSize();

        System.out.println("About to print item names: ");
        // Grab each item, put it on map
        for (InventoryItem inventoryItem : entity.getInventory().getArrayofItems()) {
            System.out.println(inventoryItem.getItemName());
            takeableItem = InventoryTakeableItemFactory.getInstance().createTakeableRep(inventoryItem, this.entity.getLocation());
            Map.getInstance().getActiveMapArea().getTile(this.entity.getLocation()).addTakeableItem(takeableItem);
        }
        new DropAllGoldCommand(this.entity, this.entity.getLocation()).execute();
        // Physically empty out the entity's inventory
        this.entity.getInventory().clearInventory();
    }
}
