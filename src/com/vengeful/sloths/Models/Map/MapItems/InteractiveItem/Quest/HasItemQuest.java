package com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

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

    public void accept(ModelVisitor mv){
        mv.visitHasItemQuest(this);
    }

    public Quest getNextStep() {
        return nextStep;
    }

    public void setNextStep(Quest nextStep) {
        this.nextStep = nextStep;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
