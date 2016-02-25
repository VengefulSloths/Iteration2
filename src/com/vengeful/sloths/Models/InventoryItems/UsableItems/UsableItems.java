package com.vengeful.sloths.Models.InventoryItems.UsableItems;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by luluding on 2/22/16.
 */

public class UsableItems extends InventoryItem implements ModelVisitable{

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.vistUsableItem(this);
    }
}
