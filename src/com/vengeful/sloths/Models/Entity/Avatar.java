package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.*;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.ConsumableItems;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Occupation.Smasher;
import com.vengeful.sloths.Models.Occupation.Sneak;
import com.vengeful.sloths.Models.Occupation.Summoner;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.WeaponClass;
import com.vengeful.sloths.View.Observers.EntityObserver;

import java.util.Iterator;

/**
 * Created by luluding on 2/21/16.
 */
public class Avatar extends Entity{

    private static Avatar avatar = null;

    private Avatar(){
        super("Phill", new Stats());
    }

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


    public void talk(){
        //create talk command
    }





    public void consumeItem(ConsumableItems item){
        item.use(this.getStats());
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

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitAvatar(this);
    }
}
