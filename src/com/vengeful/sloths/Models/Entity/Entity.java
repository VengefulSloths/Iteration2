package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Occupation.Occupation;
import com.vengeful.sloths.Models.SaveLoad.SaveVisitor;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import org.w3c.dom.Element;

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
    private String name;

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

    /**
     *This visit call is only for the save visitor
     */
    public void visit(SaveVisitor sv, Element e, Coord c){
        sv.visitEntity(this, e, c);
    }

    /**
     *Getter/Setters
     */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
