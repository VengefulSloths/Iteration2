package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Hat;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Mount;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Weapon;
import com.vengeful.sloths.Models.ViewObservable;

/**
 * Created by echristiansen on 3/11/2016.
 */
public class ProxyEquipmentObserver extends ProxyObserver implements EquipmentObserver {

    private EquipmentObserver target;

    public ProxyEquipmentObserver(EquipmentObserver equipmentObserver, ViewObservable subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
        this.target = equipmentObserver;
    }

    @Override
    public void alertWeaponEquipped(Weapon weapon) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertWeaponEquipped(weapon));
        }
    }

    @Override
    public void alertWeaponUnequipped(Weapon weapon) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertWeaponUnequipped(weapon));
        }
    }

    @Override
    public void alertHatEquipped(Hat hat) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertHatEquipped(hat));
        }
    }

    @Override
    public void alertHatUnequipped(Hat hat) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertHatUnequipped(hat));
        }
    }

    @Override
    public void alertMountEquipped(Mount mount) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertMountEquipped(mount));
        }
    }

    @Override
    public void alertMountUnequipped(Mount mount) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertMountUnequipped(mount));
        }
    }

    @Override
    public ModelObserver getModelObserver() {
        return target;
    }


}
