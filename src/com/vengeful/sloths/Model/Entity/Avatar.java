package com.vengeful.sloths.Model.Entity;

import com.vengeful.sloths.Model.Ability.AbilityManager;
import com.vengeful.sloths.Model.Buff.BuffManager;
import com.vengeful.sloths.Model.Inventory.Equipped;
import com.vengeful.sloths.Model.Inventory.Inventory;
import com.vengeful.sloths.Model.Occupation.Occupation;
import com.vengeful.sloths.Model.InventoryItems.*;

import com.vengeful.sloths.Utility.Direction;

/**
 * Created by luluding on 2/21/16.
 */
public class Avatar extends Entity{

    public static Avatar avtar;

    private Avatar(Occupation occupation, Inventory inventory, Equipped equipped, AbilityManager abilityManager, BuffManager buffManager){
        //super(occupation, inventory, equipped, abilityManager, buffManager);
    }

    /*
    private ActionCommandFactory commandFactory;


    public void setCommandFactory(ActionCommandFactory acf) {
        this.commandFactory = acf;
    }*/

    public Equipped getEquipped() {
        return this.getEquipped();
    }

    public void move(Direction dir) {

    }

    public boolean equip(InventoryItem item) {

        return true;
    }

    public boolean unequip(InventoryItem item) {

        return true;
    }

    public boolean drop(InventoryItem item) {

        return true;
    }

    public boolean pickup(){
        return false;
    }



    public void levelUp() {
    }

    public void gainXP(int xp) {
    }

    public void gainHealth(int health) {
    }

    public void takeDamage(int damage) {
    }

    public void die() {
    }




}
