package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.*;

import com.vengeful.sloths.Models.Occupation.Smasher;
import com.vengeful.sloths.Models.Occupation.Sneak;
import com.vengeful.sloths.Models.Occupation.Summoner;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by luluding on 2/21/16.
 */
public class Avatar extends Entity{

    private static Avatar avatar = null;

    private Avatar(){}

    public static Avatar getInstance(){
        if(avatar == null)
            avatar = new Avatar();

        return avatar;
    }

    //pass in stats
    public void avatarInit(String occupationString, AbilityManager abilityManager, BuffManager buffManager){

        switch (occupationString) {
            case "Smasher":
                this.setOccupation(new Smasher());
                break;
            case "Sneak":
                this.setOccupation(new Sneak());
                break;
            case "Summoner":
                this.setOccupation(new Summoner());
                break;
            default:
                this.setOccupation(new Summoner());
        }

        this.setInventory(new Inventory());
        this.setEquipped(new Equipped());
        this.setAbilityManager(abilityManager);
        this.setBuffManager(buffManager);
    }



    /*
    private ActionCommandFactory commandFactory;


    public void setCommandFactory(ActionCommandFactory acf) {
        this.commandFactory = acf;
    }*/


    public void talk(){
        //create talk command
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
