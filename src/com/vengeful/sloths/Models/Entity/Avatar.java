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
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Direction;

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

    private boolean isMounted = false;

    public void avatarInit(String occupationString, AbilityManager abilityManager, BuffManager buffManager, SkillManager skillManager){

        this.setInventory(new Inventory());
        this.setAbilityManager(abilityManager);
        this.setBuffManager(buffManager);
        this.setSkillManager(skillManager);
        //this.setStats(stats);
        this.setEquipped(new Equipped(this));

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

    private int endTime = 0;
    public void mount() {
        if (!this.isMounted) {
            int ticks = this.getAbilityManager().getMountAbility().execute();
            TimeModel.getInstance().registerAlertable(() -> isMounted = true, ticks);
        }
        else {
            this.getAbilityManager().getDemountAbility().execute();
            isMounted = false;
        }
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (this.isMounted) {
            this.getAbilityManager().getDemountAbility().execute();
            isMounted = false;
        }
    }

    @Override
    public int attack(Direction dir) {
        if (this.isMounted) {
            this.getAbilityManager().getDemountAbility().execute();
            isMounted = false;
        }
        return super.attack(dir);
    }

    @Override
    public void doAbility(int index) {
        if (this.isMounted) {
            this.getAbilityManager().getDemountAbility().execute();
            isMounted = false;
        }
        super.doAbility(index);
    }

    public void avatarInit(String occupationString, Stats stats){
        this.avatarInit(occupationString, new AbilityManager(this), new BuffManager(this), new SkillManager());
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

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitAvatar(this);
    }
}
