package com.vengeful.sloths.Models.InventoryItems.EquippableItems;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;

/**
 * Created by luluding on 2/22/16.
 */
public class TwoHandedWeapon extends Weapon implements ModelVisitable{

    public TwoHandedWeapon(String name, StatsAddable stats, int baseDamage){
        super(name, stats, baseDamage);
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitTwoHandedWeapon(this);
    }
}

