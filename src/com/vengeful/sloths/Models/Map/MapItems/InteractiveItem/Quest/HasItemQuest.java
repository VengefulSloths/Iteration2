package com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;

/**
 * Created by alexs on 3/10/2016.
 */
public class HasItemQuest extends Quest {

    private Quest nextStep;
    private String itemName;

    public HasItemQuest(Quest nextStep, String itemName) {
        this.nextStep = nextStep;
        this.itemName = itemName;

    }

    @Override
    public boolean attempt(){
        InventoryItem[] items = Avatar.getInstance().getInventory().getArrayofItems();
        for (int i=0; i<items.length; i++) {
            if (items[i].getItemName() == itemName) {
                return nextStep.attempt();
            }
        }
        return false;
    }
}
