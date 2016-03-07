package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.ConsumableItems;
import com.vengeful.sloths.Models.Occupation.Smasher;
import com.vengeful.sloths.Models.Occupation.Sneak;
import com.vengeful.sloths.Models.Occupation.Summoner;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Models.Stats.Stats;

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


    public void avatarInit(String occupationString, AbilityManager abilityManager, BuffManager buffManager, Stats stats, SkillManager skillManager){

        this.setInventory(new Inventory());
        this.setAbilityManager(abilityManager);
        this.setBuffManager(buffManager);
        this.setSkillManager(skillManager);
        this.setStats(stats);
        this.setEquipped(new Equipped(this.getStats()));

        switch (occupationString) {
            case "Smasher":
                this.setOccupation(new Smasher(this.getStats(), this.getSkillManager(), this.getAbilityManager(), this));
                break;
            case "Sneak":
                this.setOccupation(new Sneak(this.getStats(), this.getSkillManager(), this.getAbilityManager(), this));
                break;
            case "Summoner":
                this.setOccupation(new Summoner(this.getStats(), this.getSkillManager(), this.getAbilityManager(), this));
                break;
            default:
                this.setOccupation(new Summoner(this.getStats(), this.getSkillManager(), this.getAbilityManager(), this));
        }

        //TODO: test ability, remove
        Iterator<Skill> iter = skillManager.getSkillsIter();
        Skill bws = null;
        while(iter.hasNext()){
            Skill s = iter.next();
            if(s.getName() == "bind wounds"){
                bws = s;
                break;
            }
        }

        skillManager.updateSkillLevel(bws, 1);
        ///////////////////////////

    }

    public void avatarInit(String occupationString, Stats stats){
        this.avatarInit(occupationString, new AbilityManager(), new BuffManager(this), stats, new SkillManager());
    }


    public void talk(){
        //create talk command
    }

    public void consumeItem(ConsumableItems item){
        item.use(this.getStats());
    }



    public void incSkillLevel(Skill skill){
       this.getSkillManager().updateSkillLevel(skill, 1);
    }


    //called by levelUp AE
    public void levelUp() {
        this.getStats().add(new HealthManaExperienceAddable(0, 0, 0, 0, this.getStats().getMaxExperience() - this.getStats().getCurrentExperience()));
        this.getOccupation().levelUp(this.getStats());
        this.getSkillManager().setAvailableSkillPoint(this.getSkillManager().getAvailableSkillPoints() + 2); //hard coded to gain 2 sp every level
    }

    public void gainXP(int xp) {
        this.getStats().add(new HealthManaExperienceAddable(0, 0, 0, 0, xp));
    }

    public void takeDamage(int damage) {
        this.getStats().subtract(new HealthManaExperienceAddable(damage, 0, 0, 0, 0));
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitAvatar(this);
    }
}
