package com.vengeful.sloths.View.Observers;

import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;

/**
 * Created by luluding on 2/22/16.
 */
public interface EquipmentObserver extends ModelObserver{
    void alertItemEquipped(EquippableItems item);
    void alertItemUnequipped(EquippableItems item);
}
