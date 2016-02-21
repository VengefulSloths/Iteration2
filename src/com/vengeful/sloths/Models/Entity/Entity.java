package com.vengeful.sloths.Models.Entity;

import com.sun.jdi.connect.Connector;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Occupation.DummyOccupation;
import com.vengeful.sloths.Models.Occupation.Occupation;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class Entity {
    private Coord location;
    private Direction facingDirection;
    private String name;

    private Occupation occupation;
    private AbilityManager abilityManager;
    private BuffManager buffManager;
    private Inventory inventory;
    private Equipped equipped;
    //private Stats stats;

    //for avatar
    public Entity(){}

    //pass in stats as well
    public Entity(String name, BuffManager buffManager){
        this.name = name;

        this.inventory = new Inventory();
        this.equipped = new Equipped();
        this.abilityManager = new AbilityManager();
        this.buffManager = buffManager;
        this.occupation = new DummyOccupation();

        this.location = new Coord(0,0);
        this.facingDirection = Direction.N;
    }


    public void doAbility(){
        //do something
    }



    /********** Getter and Setters *************/
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
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

    protected void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public Equipped getEquipped(){
        return this.equipped;
    }

    protected void setEquipped(Equipped equipped){
        this.equipped = equipped;
    }

    public AbilityManager getAbilityManager(){
        return this.abilityManager;
    }

    protected void setAbilityManager(AbilityManager abilityManager){
        this.abilityManager = abilityManager;
    }

    public BuffManager getBuffManager(){
        return this.buffManager;
    }

    protected void setBuffManager(BuffManager buffManager){
        this.buffManager = buffManager;
    }

    public Occupation getOccupation(){
        return this.occupation;
    }

    protected void setOccupation(Occupation occupation){
        this.occupation = occupation;
    }
}