package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.SaveLoad.SaveManager;
import com.vengeful.sloths.Models.SaveLoad.SaveVisitor;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

/**
 * Created by luluding on 3/6/16.
 */
public class BindWoundsAbility extends Ability {
    Entity entity;
    SkillManager skillManager;

    private int startupTicks; //not sure if this is needed
    private int cooldownTicks;

    private int manaCost = 1;

    public BindWoundsAbility(Entity entity, SkillManager skillManager, int startupTicks, int cooldownTicks){
        this.entity = entity;
        this.skillManager = skillManager; //to avoid lod
        this.startupTicks = startupTicks;
        this.cooldownTicks = cooldownTicks;
    }

    public BindWoundsAbility(){}

    @Override
    public int execute() {
        if(this.entity.isActive())
            return 0;

        if(!shouldDoAbility(entity.getSkillManager().getBindWoundsLevel(), entity.getSkillManager().getBindWoundsLevel()))
            return 0;

        this.entity.setActive(true);

        System.out.println("DOING BINDWOUNDS ABILITY");

        TimeModel.getInstance().registerAlertable(() -> {
            this.doHeal();
        }, startupTicks);


        TimeModel.getInstance().registerAlertable(() -> entity.setActive(false), cooldownTicks);

        //This is however long it will take to bind wounds
        return cooldownTicks;
    }

    private void doHeal(){
        int skillLevel = this.skillManager.getBindWoundsLevel();
        System.out.println("YOUR SKILL LEVEL: " + skillLevel);
        System.out.println("YOUR HEALTH: " + this.entity.getStats().getCurrentHealth());
        int health = skillLevel * 2;

        this.entity.gainHealth(health);
        this.entity.decMana(manaCost);

        System.out.println("HEAL " + health + " HP");
        System.out.println("YOUR HEALTH NOW: " + this.entity.getStats().getCurrentHealth());
    }

    public void accept(SaveVisitor sv){
        sv.visitBindWounds(this);
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setSkillManager(SkillManager skillManager) {
        this.skillManager = skillManager;
    }
    public String toString(){
        return "BindWoundsAbility";
    }
}
