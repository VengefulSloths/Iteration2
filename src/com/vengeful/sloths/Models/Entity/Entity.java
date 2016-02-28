package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.ActionCommandFactory.ActionCommandFactory;
import com.vengeful.sloths.Models.ActionCommandFactory.AvatarActionCommandFactory;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Occupation.DummyOccupation;
import com.vengeful.sloths.Models.Occupation.Occupation;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.View.Observers.EntityObserver;
import com.vengeful.sloths.View.Observers.ModelObserver;

import java.util.ArrayList;

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
    private ActionCommandFactory commandFactory;


    protected boolean isMoving = false;

    private ArrayList<EntityObserver> observers = new ArrayList<>();

    //for avatar
    public Entity(){}

    public Entity(String name, BuffManager buffManager, Stats stats){
        this(name, stats);
        this.setBuffManager(buffManager);
    }

    public Entity(String name, Stats stats){
        this.name = name;
        this.stats = stats;
        this.inventory = new Inventory();
        this.equipped = new Equipped(stats);
        this.abilityManager = new AbilityManager();
        this.buffManager = new BuffManager(this);
        this.occupation = new DummyOccupation(stats);


        this.location = new Coord(1,2);
        this.facingDirection = Direction.N;
    }

    public void doAbility(){
        //do something
    }

    public int move(Direction dir){
        if(!isMoving) {
            this.setFacingDirection(dir);
            isMoving = true;
            Coord dst = new Coord(location.getR(), location.getS());
            switch (dir) {
                case N:
                    dst.setS(dst.getS() - 1);
                    break;
                case S:
                    dst.setS(dst.getS() + 1);
                    break;
                case NE:
                    dst.setR(dst.getR() + 1);
                    dst.setS(dst.getS() - 1);
                    break;
                case NW:
                    dst.setR(dst.getR() - 1);
                    break;
                case SE:
                    dst.setR(dst.getR() + 1);
                    break;
                case SW:
                    dst.setR(dst.getR() - 1);
                    dst.setS(dst.getS() + 1);
                    break;
                default:
                    break;
            }


            this.getCommandFactory().createMovementCommand(this.getLocation(), dst, dir, this, this.getStats().getMovement());

            for (EntityObserver observer: this.getObservers()) {
                //TODO: dont hardcode the movement time
                observer.alertMove(dst.getR(), dst.getS(), 500);
            }

        }

        //TODO: return number of miliseconds till the movement completes
        return 0;

    }


    public abstract void pickup(TakeableItem item);

    public abstract void die();


    public void registerObserver(ModelObserver observer) {
        this.observers.add((EntityObserver) observer);
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

    protected ArrayList<EntityObserver> getObservers(){
        return this.observers;
    }


    public void setCommandFactory(ActionCommandFactory acf) {
        this.commandFactory = acf;
    }

    public ActionCommandFactory getCommandFactory(){
        return this.commandFactory;
    }

    public boolean getMoving(){
        return this.isMoving;
    }

    public void setMoving(boolean isMoving){
        this.isMoving = isMoving;
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
