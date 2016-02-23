package com.vengeful.sloths.Models.InventoryItems.EquippableItems;


import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;

/**
 * Created by qianwen on 1/30/16.
 */
public class Hat extends EquippableItems {

    public Hat(String name, StatsAddable stats){
        super(name, stats);
    }

    @Override
    public void addToEquipped(Equipped equipped) {
        equipped.addHat(this);
    }

    @Override
    public void removeFromEquipped(Equipped equipped) {
        equipped.removeHat(this);
    }
}
