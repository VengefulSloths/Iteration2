package com.vengeful.sloths.Models.InventoryItems.EquippableItems;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Stats.StatAddables.BaseStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Inventory.Equipped;

/**
 * Created by qianwen on 1/30/16.
 */

public abstract class EquippableItems extends InventoryItem {
    private StatsAddable itemStats;

    /*
    public EquippableItems(String itemName) {
        this.setItemName(itemName);
    }*/

    public EquippableItems(String itemName, StatsAddable itemStats){
        this.setItemName(itemName);
        this.itemStats = itemStats;
    }

    public EquippableItems(){}

    public abstract void addToEquipped(Equipped equipped);
    public abstract void removeFromEquipped(Equipped equipped);


    public StatsAddable getItemStats() {
        return this.itemStats;
    }

    public void setItemStats(StatsAddable stats){
        this.itemStats = stats;
    }

    @Override
    public void interact() {
        this.addToEquipped(Avatar.getInstance().getEquipped());
    }

}