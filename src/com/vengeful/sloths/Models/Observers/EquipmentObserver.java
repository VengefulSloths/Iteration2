package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Hat;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Mount;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Weapon;

/**
 * Created by luluding on 2/22/16.
 */
public interface EquipmentObserver extends ModelObserver{
    //void alertItemEquipped(EquippableItems item);
    //void alertItemUnequipped(EquippableItems item);

    void alertWeaponEquipped(Weapon weapon);
    void alertWeaponUnequipped(Weapon weapon);

    void alertHatEquipped(Hat hat);
    void alertHatUnequipped(Hat hat);

    void alertMountEquipped(Mount mount);
    void alertMountUnequipped(Mount mount);
}
