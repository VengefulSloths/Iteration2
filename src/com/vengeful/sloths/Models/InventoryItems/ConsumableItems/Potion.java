package com.vengeful.sloths.Models.InventoryItems.ConsumableItems;

import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/22/16.
 */
public class Potion extends ConsumableItems{

    public Potion(String name, StatsAddable statsAddable){
        super(name, statsAddable);

    }

    @Override
    public void use(Stats stats) {
        stats.add(this.getItemStats());
    }
}
