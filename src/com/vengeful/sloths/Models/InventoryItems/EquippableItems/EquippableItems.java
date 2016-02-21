package com.vengeful.sloths.Models.InventoryItems.EquippableItems;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;

/**
 * Created by qianwen on 1/30/16.
 */

public abstract class EquippableItems extends InventoryItem {
    private StatsAddable itemStats;
    private String itemName;
    //private BaseStats baseStats;


    public StatsAddable getItemStats() {
        return this.itemStats;
    }

    public EquippableItems() {

    }

    //public EquippableItems(String itemName, BaseStats b){
    //super(); //not needed
    //    this.itemName = itemName;
    //    this.itemStats = b;
    //}

    public EquippableItems(String itemName) {
        //super(); //not needed
        this.itemName = itemName;

    }

//    public void saveMe(SaveManager sv, int ws){
//        super.saveMe(sv,ws);
//        itemStats.saveMe(sv, ws+1);
//    }
//
//    public void saveMeFromTakeable(SaveManager sm, int ws)
//    {
//        itemStats.saveMe(sm, ws+1);
//        sm.writeVariableLine(ws, "itemName", itemName, true);
//    }

    public void setBaseStats(StatsAddable itemStats) {
        this.itemStats = itemStats;
    }
}