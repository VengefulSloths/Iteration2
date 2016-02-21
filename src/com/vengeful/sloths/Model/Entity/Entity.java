package com.vengeful.sloths.Model.Entity;

import com.vengeful.sloths.Model.Ability.AbilityManager;
import com.vengeful.sloths.Model.Buff.BuffManager;
import com.vengeful.sloths.Model.Inventory.Equipped;
import com.vengeful.sloths.Model.Inventory.Inventory;
import com.vengeful.sloths.Model.Occupation.Occupation;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class Entity {
    private Coord location;
    private Direction facingDirection;
    private Occupation occupation;
    //private Stats stats;
    private AbilityManager abilityManager;
    private BuffManager buffManager;
    private Inventory inventory;
    private Equipped equipped;

    //pass in stats as well
    /*
    public Entity(Occupation occupation, Inventory inventory, Equipped equipped, AbilityManager abilityManager, BuffManager buffManager){
        this.occupation = occupation;
        this.inventory = inventory;
        this.equipped = equipped;

        this.abilityManager = abilityManager;
        this.buffManager = buffManager;
    }*/


    public void doAbility(){
        //do something
    }





    public Coord getLocation(){
        return this.location;
    }

    public void setLocation(Coord loc){
        this.location = loc;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public Equipped getEquipped(){
        return this.equipped;
    }



}
