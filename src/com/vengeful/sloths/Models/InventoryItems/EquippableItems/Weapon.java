package com.vengeful.sloths.Models.InventoryItems.EquippableItems;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.WeaponClass;

/**
 * Created by qianwen on 1/30/16.
 */
public abstract class Weapon extends EquippableItems {

    private int baseDamage;


    public Weapon(String name, StatsAddable stats, int baseDamage){
        super(name, stats);
        this.baseDamage = baseDamage;
    }

    public abstract WeaponClass getWeaponClassification();

    public abstract int calculateOffsensiveRating(Stats stats, SkillManager skills);

    public abstract Ability getAttackAbility(Entity entity);

    public int getBaseDamage(){
        return this.baseDamage;
    }

    public void setBaseDamage(int baseDamage){
        this.baseDamage = baseDamage;
    }

    @Override
    public void addToEquipped(Equipped equipped) {
        equipped.addWeapon(this);
    }

    @Override
    public void removeFromEquipped(Equipped equipped) {
        equipped.removeWeapon(this);
    }

}
