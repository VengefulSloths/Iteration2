package com.vengeful.sloths.Models.InventoryItems;


import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Views.ViewFactory.ViewItem;

/**
 * Created by qianwen on 1/30/16.
 */
public abstract class InventoryItem extends ViewItem {
    private String itemName;
    private int value; //for trade

    public InventoryItem() {
        super();
    }

    public String getItemName(){
        return this.itemName;
    }

    public void setItemName(String name){
        this.itemName = name;
    }

    public abstract void accept(ModelVisitor mv);
    public int getValue(){
        return this.value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public abstract void interact();

}
