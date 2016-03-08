package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;

/**
 * Created by luluding on 2/22/16.
 */
public interface EquipmentObserver extends ModelObserver{
    void alertItemEquipped(EquippableItems item);
    void alertItemUnequipped(EquippableItems item);
}
