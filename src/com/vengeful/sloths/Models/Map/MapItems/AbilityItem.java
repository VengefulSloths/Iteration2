package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Ability.Ability;

/**
 * Created by zach on 3/12/16.
 */
public class AbilityItem extends TakeableItem {
    private Ability ability;

    public AbilityItem(Ability ability) {
        this.ability = ability;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}
