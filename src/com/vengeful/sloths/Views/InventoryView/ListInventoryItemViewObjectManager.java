package com.vengeful.sloths.Views.InventoryView;

import com.vengeful.sloths.Models.Inventory.Inventory;

/**
 * Created by lenovo on 3/7/2016.
 */
public class ListInventoryItemViewObjectManager extends InventoryItemViewObjectManager {

    public ListInventoryItemViewObjectManager() {
        super();
    }

    public void initWithInventory(Inventory inventory) {
        for (int i = 0; i < inventory.getCurrentSize(); i++) {
            //InventoryItem item = inventory.getItem(i);
            //this.addInventoryItemViewObject(new ListItemViewObject(item);
            this.addInventoryItemViewObject(new ListItemViewObject(inventory.getItem(i)));
        }
    }

    /* Adds an InventoryItemViewObject to the itemList */
    public void addListInventoryItemViewObject(ListItemViewObject item) { //rename populate(InventoryItemViewObject item)?
        //We can sort on iterator because it will be called less
        this.addItem(item);
    }


}