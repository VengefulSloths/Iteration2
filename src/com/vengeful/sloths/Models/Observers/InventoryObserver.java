package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;

/**
 * Created by zach on 2/1/16.
 */
public interface InventoryObserver extends ModelObserver {
    void alertItemAdded(InventoryItem item);
    void alertItemDropped(InventoryItem item);
}
