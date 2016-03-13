package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
import com.vengeful.sloths.Controllers.ControllerManagers.AggressiveNPCControllerManager;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.*;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.Gold;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.Observers.StatsObserver;
import com.vengeful.sloths.Models.Occupation.DummyOccupation;
import com.vengeful.sloths.Models.Occupation.Occupation;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.CurrentHealthAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.GenericStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;
import com.vengeful.sloths.Utility.Location;

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
    private boolean dead = false;

    private CanMoveVisitor movementValidator;

    public Entity(){
        this.movementValidator = new DefaultCanMoveVisitor();
    }
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        if(!dead) {
            isActive = active;
        }else{isActive = true;}
    }

    private boolean isActive = false;

    private ArrayList<EntityObserver> observers = new ArrayList<>();

    //for avatar
//    public Entity(){
//    }

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
        stats.setEntity(this);
        this.skillManager = new SkillManager();
        this.abilityManager = new AbilityManager(this);
        this.inventory = new Inventory();
        this.equipped = new Equipped(this);
        this.buffManager = new BuffManager(this);
        this.movementValidator = new DefaultCanMoveVisitor();

        //this.location = new Coord(1,2);
        this.facingDirection = Direction.N;

        this.occupation = new DummyOccupation(stats, skillManager, abilityManager, this);
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

            movementHook();

            return emc.execute();


        }
        return 0;

    }

    protected void movementHook(){
        //for subclass to do extra logic
    }


    public final int die(){
        if(!this.dead) {
            this.setDead(true);
            System.out.println("dying bruh");

            EntityDieCommand edc = EntityMapInteractionFactory.getInstance().createDeathCommand(this, observers.iterator());

            dropAllItems();
            customDeath();
            doRespawn();
            dropGold();
            removeAllBuffs();

            return edc.execute();

        }

        return 0;
    }

    protected void customDeath() {
        // anything extra
        System.out.println("custom death entity");
    }

    protected void dropAllItems() {
        //create drop command
        EntityMapInteractionFactory.getInstance().createDropEntireInventoryCommand(this).execute();
        System.out.println("Dropping all items!");
    }

    protected void removeAllBuffs(){
        buffManager.removeAllBuffs();
    }

    protected void doRespawn() {
        // Nothing for general entity
    }

    protected void dropGold(){
        EntityMapInteractionFactory.getInstance().createDropAllGoldCommand(this, this.getLocation()).execute();
        System.out.println("dropping gold");
    }

    public int attack(Direction dir){
        if(!isActive) {
            this.setFacingDirection(dir);
            return abilityManager.doWeaponAbility();
        }
        return 0;
    }
    public void equip(EquippableItems item) {
        item.addToEquipped(this.getEquipped());
    }

    public void unequip(EquippableItems item) {
        item.removeFromEquipped(this.getEquipped());
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
        if (facingDirection != null){
            this.facingDirection = facingDirection;
        }else{
            throw new NullPointerException("Direction is Null");
        }

            Iterator<EntityObserver> entityObserverIterator = observers.iterator();
            while (entityObserverIterator.hasNext()) {
                try {
                    entityObserverIterator.next().alertDirectionChange(this.facingDirection);
                }catch(Exception e){}
            }
    }

    public void takeDamage(int attackDamage){
        //do dmg calculations here (like lessening it for defense)
        StatsAddable damage = new CurrentHealthAddable(attackDamage);
        buffManager.modifyDamage(damage);

        //TODO: take defensive rating into consideration

        getStats().subtract(damage);
        for (EntityObserver observer: observers) {
            observer.alertTakeDamage(damage.getCurrentHealth());
        }
    }

    public void gainHealth(int health) {
        this.getStats().add(new HealthManaExperienceAddable(health, 0, 0, 0, 0));
    }

    public void decMana(int mana){
        this.getStats().subtract(new HealthManaExperienceAddable(0,0,mana,0,0));
    }

    public void gainMana(int mana){
        this.getStats().add(new HealthManaExperienceAddable(0,0,mana,0,0));
    }

    public void pickup(TakeableItem item){
        EntityPickupCommand epc = EntityMapInteractionFactory.getInstance().createPickUpCommand(this, this.getInventory(), item);
        epc.execute();
    }
    public void getGold(Gold gold){
        EntityPickUpGoldCommand epgc = EntityMapInteractionFactory.getInstance().createPickUpGoldCommand(this, this.getInventory(), gold);
        epgc.execute();
    }
    public void baddOOReceiveGoldForTesting(Gold g){
        this.inventory.addGold(g);
    }

    public void drop(InventoryItem item){
        EntityDropCommand edc = EntityMapInteractionFactory.getInstance().createDropCommand(item, this.getLocation(), this);
        edc.execute();
    }


    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
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

    public void locationChange(){
        Iterator<EntityObserver> entityObserverIterator = observers.iterator();
        while (entityObserverIterator.hasNext()) {
            //entityObserverIterator.next().alertDirectionChange(Direction.S);
            EntityObserver current = entityObserverIterator.next();
            entityObserverIterator.remove();
        }
        this.accept(TemporaryVOCreationVisitor.getInstance());
        //Map.getInstance().setActiveMapArea(Map.getInstance().getActiveMapArea());
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public Equipped getEquipped(){
        return this.equipped;
    }

    public void setEquipped(Equipped equipped){
        this.equipped = equipped;
    }

    public AbilityManager getAbilityManager(){
        return this.abilityManager;
    }

    public void setAbilityManager(AbilityManager abilityManager){
        this.abilityManager = abilityManager;
    }

    public BuffManager getBuffManager(){
        return this.buffManager;
    }

    public void setBuffManager(BuffManager buffManager){
        this.buffManager = buffManager;
    }

    public Occupation getOccupation(){
        return this.occupation;
    }

    public void setOccupation(Occupation occupation){
        this.occupation = occupation;
    }

    public Stats getStats(){
        return this.stats;
    }

    public void setStats(Stats stats){
        stats.setEntity(this);
        this.stats = stats;
        if(this.stats != null){
            Iterator<StatsObserver> iterator = this.stats.getObservers().iterator();
            while(iterator.hasNext()){
                StatsObserver current = iterator.next();
                this.stats.registerObserver(current);
            }
        }
    }

    public SkillManager getSkillManager(){
        return this.skillManager;
    }

    public void setSkillManager(SkillManager skillManager){
        this.skillManager = skillManager;
    }

    public ArrayList<EntityObserver> getObservers(){
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

    public void teleportPet(Location location){
        //do nothing
    }

    public void enrage(){
        new AggressiveNPCControllerManager(Map.getInstance().getActiveMapArea(), this);
    }

    public GenericStatsAddable getAllEntityStatEffects(){
        GenericStatsAddable gsa = new GenericStatsAddable();
        BuffManager bm = this.getBuffManager();
        Equipped eq = this.getEquipped();
        gsa.add(bm.getAllBuffStatEffects());
        gsa.add(eq.getAllEquippedStatEffects());
        return  gsa;
    }

}
