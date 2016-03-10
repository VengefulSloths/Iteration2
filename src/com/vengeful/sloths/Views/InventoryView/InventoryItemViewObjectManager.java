package com.vengeful.sloths.Views.InventoryView;

import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Hat;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Views.ViewManager.ViewObjectManager;

import java.util.ArrayList;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class InventoryItemViewObjectManager extends ViewObjectManager {


    /* Constructors */
    public InventoryItemViewObjectManager() {
        super();
    }
    public InventoryItemViewObjectManager(ArrayList<ItemViewObject> itemList) {
        super(itemList);
    }

    /* Adds an InventoryItemViewObject to the itemList */
    public void addInventoryItemViewObject(ItemViewObject item) { //rename populate(InventoryItemViewObject item)?
        //We can sort on iterator because it will be called less
        this.addItem(item);
    }

    /*Removes InventoryItemViewObject from the itemList */
    public ItemViewObject removeInventoryItemViewObject(InventoryItem item) { //rename populate(InventoryItemViewObject item)?
        //We can sort on iterator because it will be called less
        int index = 0;
        ItemViewObject ivo = null;

        for (int i = 0; i < this.getItemListSize(); i++) {
            if (this.getItemList().get(i).getInventoryItem().equals(item)) {
                ivo = (ItemViewObject) this.getItemList().get(i);
                ivo.setIsDisplayed(false); //edit?
                this.getItemList().remove(i);
            }
        }
        return ivo;
    }

    /* Gets an ItemViewObject from the itemList */
    public ItemViewObject getFromItemList(int index) {
        try {
            return this.getItemList().get(index);
        }catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    /* Initializes the itemList by generating ItemViewObjects from inventoryItems. Maybe make a factory? */
    public void initWithInventory(Inventory inventory) {
        for (int i = 0; i < inventory.getCurrentSize(); i++) {
            //InventoryItem item = inventory.getItem(i);
            this.addInventoryItemViewObject(new ItemViewObject(inventory.getItem(i)));
        }
    }
}
