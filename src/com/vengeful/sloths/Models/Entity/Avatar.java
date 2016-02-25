package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.AreaView.Observers.MovementObserver;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.*;
import com.vengeful.sloths.Models.ActionCommandFactory.*;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.ConsumableItems;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Models.InventoryTakeableItemFactory;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Occupation.Smasher;
import com.vengeful.sloths.Models.Occupation.Sneak;
import com.vengeful.sloths.Models.Occupation.Summoner;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

import java.util.ArrayList;
import java.util.Iterator;

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


    public void avatarInit(String occupationString, AbilityManager abilityManager, BuffManager buffManager, Stats stats){

        this.setInventory(new Inventory());
        this.setAbilityManager(abilityManager);
        this.setBuffManager(buffManager);
        this.setStats(stats);
        this.setEquipped(new Equipped(this.getStats()));

        switch (occupationString) {
            case "Smasher":
                this.setOccupation(new Smasher(this.getStats()));
                break;
            case "Sneak":
                this.setOccupation(new Sneak(this.getStats()));
                break;
            case "Summoner":
                this.setOccupation(new Summoner(this.getStats()));
                break;
            default:
                this.setOccupation(new Summoner(this.getStats()));
        }
    }

    public void avatarInit(String occupationString, AbilityManager abilityManager, Stats stats){
        this.avatarInit(occupationString, abilityManager, new BuffManager(this), stats);
    }


    private ActionCommandFactory commandFactory;


    public void setCommandFactory(ActionCommandFactory acf) {
        this.commandFactory = acf;
    }


    public void talk(){
        //create talk command
    }


    public Coord move(Direction dir) {

        if(!isMoving) {
            Coord dst = super.move(dir);

            this.commandFactory.createMovementCommand(this.getLocation(), dst, dir, this, this.getStats().getMovement());

            for (MovementObserver observer: this.getObservers()) {
                //TODO: dont hardcode the movement time
                observer.alertMove(dst.getR(), dst.getS(), 500);
            }

            return dst;


        }else{
            ////System.out.Println("<<<<<<<<<<<<<<<<<<movement rejected>>>>>>>>>>>>>>>>");
            return this.getLocation();
        }
    }



    public void equip(EquippableItems item) {
        item.addToEquipped(this.getEquipped());
    }

    public void unequip(EquippableItems item) {
        item.removeFromEquipped(this.getEquipped());
    }


    public void consumeItem(ConsumableItems item){
        item.use(this.getStats());
    }

    public boolean drop(InventoryItem item) {
        this.commandFactory.createDropCommand(item, this.getLocation(), this);
        return true;
    }

    public boolean pickup(TakeableItem item){
        this.commandFactory.createPickUpCommand(this.getLocation(), this, item);
        return true;
    }

    //called by levelUp AE
    public void levelUp() {
        this.getStats().add(new HealthManaExperienceAddable(0, 0, 0, 0, this.getStats().getMaxExperience() - this.getStats().getCurrentExperience()));
        this.getOccupation().levelUp(this.getStats());
    }

    public void gainXP(int xp) {
        this.getStats().add(new HealthManaExperienceAddable(0, 0, 0, 0, xp));
    }

    public void gainHealth(int health) {
        this.getStats().add(new HealthManaExperienceAddable(health, 0, 0, 0, 0));
    }

    public void takeDamage(int damage) {
        this.getStats().subtract(new HealthManaExperienceAddable(damage, 0, 0, 0, 0));
    }

    public void die() {
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitAvatar(this);
    }

}
