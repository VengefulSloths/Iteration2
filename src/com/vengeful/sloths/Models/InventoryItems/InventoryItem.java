package com.vengeful.sloths.Models.InventoryItems;


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



}
