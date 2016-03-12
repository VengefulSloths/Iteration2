package com.vengeful.sloths.Models.InventoryItems.UsableItems;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/22/16.
 */

public abstract class UsableItems extends InventoryItem implements ModelVisitable{

    public UsableItems(String name){
        this.setItemName(name);
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.vistUsableItem(this);
    }
}
