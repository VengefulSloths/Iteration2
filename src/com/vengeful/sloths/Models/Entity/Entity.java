package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.*;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.InventoryTakeableItemFactory;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Occupation.DummyOccupation;
import com.vengeful.sloths.Models.Occupation.Occupation;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.CurrentHealthAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.WeaponClass;
import com.vengeful.sloths.View.Observers.EntityObserver;
import com.vengeful.sloths.View.Observers.ModelObserver;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class Entity implements ModelVisitable, ViewObservable {
    private Coord location;
    private Direction facingDirection;
    private Occupation occupation;
    private AbilityManager abilityManager;
    private BuffManager buffManager;
    private Inventory inventory;
    private Equipped equipped;
    private String name;
    private Stats stats;
    private SkillManager skillManager;

    private CanMoveVisitor movementValidator;


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private boolean isActive = false;

    private ArrayList<EntityObserver> observers = new ArrayList<>();

    //for avatar
    public Entity(){}

    public Entity(String name, BuffManager buffManager, Stats stats){
        this(name, stats);
        this.setBuffManager(buffManager);
        //no argument for Ability, Occupaiton, and SkillManager - they are avatar only (for now)

    }

    public void setMovementValidator(CanMoveVisitor canMoveVisitor) {
        this.movementValidator = canMoveVisitor;
    }

    public Entity(String name, Stats stats){
        this.name = name;
        this.stats = stats;
        this.inventory = new Inventory();
        this.equipped = new Equipped(stats);
        this.abilityManager = new AbilityManager();
        this.buffManager = new BuffManager(this);
        this.skillManager = new SkillManager();
        this.occupation = new DummyOccupation(stats, skillManager, abilityManager, this);
        this.movementValidator = new DefaultCanMoveVisitor();

        //this.location = new Coord(1,2);
        this.facingDirection = Direction.N;
    }

    public void doAbility(int index){
        this.abilityManager.doAbility(index);
    }

    public final int move(Direction dir){
        if(!isActive) {
            this.setFacingDirection(dir);

            EntityMovementCommand emc = EntityMapInteractionFactory.getInstance().createMovementCommand(
                    this.getLocation(),
                    dir,
                    getStats().getMovement(),
                    this,
                    movementValidator,
                    observers.iterator());

            return emc.execute();


        }
        return 0;

    }

    public final int attack(Direction dir){
        if(!isActive) {
            this.setFacingDirection(dir);



            EntityAttackCommand eac = EntityMapInteractionFactory.getInstance().createAttackCommand(
                    this.getLocation(),
                    dir,
                    30,//attackspeed
                    1,//attack dmg
                    this,
                    observers.iterator());

            return eac.execute();


        }
        return 0;
    }
    public void equip(EquippableItems item) {
        //Alex wrote this for testing delete whenever
        if (item == null) {
            Iterator<EntityObserver> iter = getObservers().iterator();
            while (iter.hasNext()) {
                EntityObserver eo = iter.next();
                //eo.alertEquipHat("tophat");
                eo.alertEquipWeapon("cleaver", WeaponClass.TWO_HAND);
            }
        } else {
            item.addToEquipped(this.getEquipped());
        }
    }

    public void unequip(EquippableItems item) {
        //Same as above
        if (item == null) {
            Iterator<EntityObserver> iter = getObservers().iterator();
            while (iter.hasNext()) {
                EntityObserver current = iter.next();
                current.alertUnequipHat();
                //current.alertUnequipWeapon();
            }
        } else {
            item.removeFromEquipped(this.getEquipped());
        }
    }

    public void registerObserver(ModelObserver observer) {
        this.observers.add((EntityObserver) observer);
    }


    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void changeDirection(Direction direction){
        if(!isActive) {
            EntityWaitCommand ewc = EntityMapInteractionFactory.getInstance().createEntityWaitCommand(this);
            ewc.execute();
            this.facingDirection = direction;

            Iterator<EntityObserver> entityObserverIterator = observers.iterator();
            while (entityObserverIterator.hasNext()) {
                entityObserverIterator.next().alertDirectionChange(direction);
            }
        }else{
            //suck a dick
        }
    }

    public void setFacingDirection(Direction facingDirection) {
            this.facingDirection = facingDirection;

            Iterator<EntityObserver> entityObserverIterator = observers.iterator();
            while (entityObserverIterator.hasNext()) {
                try {
                    entityObserverIterator.next().alertDirectionChange(facingDirection);
                }catch(Exception e){}
            }
    }

    public void takeDamage(int attackDamage){
        //do dmg calculations here (like lessening it for defense)
        getStats().subtract(new CurrentHealthAddable(attackDamage));
    }

    public void gainHealth(int health) {
        this.getStats().add(new HealthManaExperienceAddable(health, 0, 0, 0, 0));
    }

    public void pickup(TakeableItem item){
        EntityPickupCommand epc = EntityMapInteractionFactory.getInstance().createPickUpCommand(this, this.getInventory(), item);
        epc.execute();
    }

    public void drop(InventoryItem item){
        EntityDropCommand edc = EntityMapInteractionFactory.getInstance().createDropCommand(item, this.getLocation(), this);
        edc.execute();
    }


    /********** Getters and Setters *************/
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

    public SkillManager getSkillManager(){
        return this.skillManager;
    }

    public void setSkillManager(SkillManager skillManager){
        this.skillManager = skillManager;
    }

    protected ArrayList<EntityObserver> getObservers(){
        return this.observers;
    }


    @Deprecated
    public boolean getMoving(){
        return this.isActive;
    }

    @Deprecated
    public void setMoving(boolean isMoving){
        this.isActive = isMoving;
    }

    @Override
    public void deregisterObserver(ModelObserver modelObserver) {
        this.observers.remove((EntityObserver) modelObserver);
    }

    /**
     * Handles accepting a ModelVisitor
     */
    public void accept(ModelVisitor modelVisitor) {
        //TODO: delete this, it will cause bugs when people forget to give entities subclasses visit statements
    }

}
