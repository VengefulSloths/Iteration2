package com.vengeful.sloths.Models.InventoryItems;


import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by qianwen on 1/30/16.
 */
public abstract class InventoryItem {
    private String itemName;


    public String getItemName(){
        return this.itemName;
    }

    public void setItemName(String name){
        this.itemName = name;
    }

    public abstract void accept(ModelVisitor mv);


}
