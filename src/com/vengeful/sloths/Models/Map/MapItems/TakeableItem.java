package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.ModelVisitor;


/**
 * Created by John on 1/30/2016.
 */
public class TakeableItem extends MapItem {

    InventoryItem inventorpRep;

    public TakeableItem(String itemName, InventoryItem item){
        this.setItemName(itemName);
        this.inventorpRep = item;
    }

    //Does nothing
    public void interact(Entity entity){
        //maybe alert user he cannot move here
        this.destroy = false;
        entity.pickup(this);
    }


    public InventoryItem getInventorpRep(){
        return this.inventorpRep;
    }


    public void accept(ModelVisitor visitor) {
        visitor.visitTakeableItem(this);
    }

}
