package com.vengeful.sloths.Models.InventoryItems.EquippableItems;

import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;

/**
 * Created by qianwen on 1/30/16.
 */
public abstract class Weapon extends EquippableItems {

    private int baseDamage;


    public Weapon(String name, StatsAddable stats, int baseDamage){
        super(name, stats);
        this.baseDamage = baseDamage;
    }

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
