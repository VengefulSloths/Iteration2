package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.ViewObservable;

/**
 * Created by zach on 3/11/16.
 */
public class ProxyAbilityManagerObserver extends ProxyObserver
            implements AbilityManagerObserver {

    private AbilityManagerObserver target;

    public ProxyAbilityManagerObserver(AbilityManagerObserver abilityManagerObserver, ViewObservable subject) {
        this.subject = subject;
        System.out.println("Subject: " + subject);
        this.subject.registerObserver(this);
        this.target = abilityManagerObserver;
    }

    @Override
    public ModelObserver getModelObserver() {
        return  target;
    }

    @Override
    public void alertAbilityAdded(Ability ability) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertAbilityAdded(ability));
        }
    }

    @Override
    public void alertAbilityEquipped(Ability ability, int index) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertAbilityEquipped(ability, index));
        }
    }

    @Override
    public void alertAbilityUnequipped(Ability ability, int index) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertAbilityUnequipped(ability, index));
        }
    }

}
