package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;

/**
 * Created by zach on 3/11/16.
 */
public interface AbilityManagerObserver extends ModelObserver {
    void alertAbilityAdded(Ability ability);
    void alertAbilityEquipped(Ability ability);
    void alertAbilityUnset(Ability ability);
}
