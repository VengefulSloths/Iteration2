package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;

/**
 * Created by luluding on 3/2/16.
 */
public class EntityPickupCommand {
    Entity entity;
    TakeableItem item;
    Inventory inv;

    public EntityPickupCommand(Entity entity, Inventory inv, TakeableItem itemToPickup){
        System.out.println("Created pick up command");
        this.entity = entity;
        this.item = itemToPickup;
        this.inv = inv;
    }

    public void execute(){

        System.out.println("IPCKUP!");
        if(this.inv.addItem(item.getInventorpRep())){
            Map.getInstance().getActiveMapArea().getTile(this.item.getLocation()).removeTakeableItem(this.item);
            this.item.alertObserverOnDestroy();

            //TODO: use InventoryTakeableItemFactory to create inventory graphic rep
        }

        //TODO: debug line, remove
        System.out.println("Inventory size: " + entity.getInventory().getCurrentSize());
        System.out.println("Items in entity's inventory:");
        for(int i = 0; i < entity.getInventory().getCurrentSize(); i++){
            System.out.println(entity.getInventory().getItem(i).getItemName());
        }
    }
}
