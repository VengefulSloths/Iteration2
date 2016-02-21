package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Stats.*;
import com.vengeful.sloths.Models.Occupation.DummyOccupation;
import com.vengeful.sloths.Models.Occupation.Occupation;
import com.vengeful.sloths.Models.SaveLoad.SaveVisitor;
import com.vengeful.sloths.Models.Stats.Stats;
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
    private AbilityManager abilityManager;
    private BuffManager buffManager;
    private Inventory inventory;
    private Equipped equipped;
    private String name;
    private Stats stats;

    protected boolean isMoving = false;

    //for avatar
    public Entity(){}

    //pass in stats as well
    public Entity(String name, BuffManager buffManager, Stats stats){
        this.name = name;

        this.stats = stats;
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

    /**
     *This visit call is only for the save visitor
     */
    public void visit(SaveVisitor sv, Element e, Coord c){
        sv.visitEntity(this, e, c);
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

    public Stats getStats(){
        return this.stats;
    }

    public void setStats(Stats stats){
        this.stats = stats;
    }
}
