package com.vengeful.sloths.Models.InventoryItems.ConsumableItems;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/22/16.
 */
public abstract class ConsumableItems extends InventoryItem{
    private StatsAddable itemStats;

    public ConsumableItems(String name, StatsAddable statsAddable){
        this.setItemName(name);
        this.itemStats = statsAddable;
    }

    public  ConsumableItems(){}

    public abstract void use(Stats stats);


    public StatsAddable getItemStats() {
        return this.itemStats;
    }

    public void setItemStats(StatsAddable stats){
        this.itemStats = stats;
    }
}
