package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Occupation.Occupation;
import com.vengeful.sloths.Models.InventoryItems.*;

import com.vengeful.sloths.Utility.Direction;

/**
 * Created by luluding on 2/21/16.
 */
public class Avatar extends Entity{

    private static Avatar avatar;

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
