package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.SaveLoad.SaveManager;
import com.vengeful.sloths.Models.SaveLoad.SaveVisitor;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;

/**
 * Created by luluding on 3/6/16.
 */
public class BindWoundsAbility extends Ability {
    Entity entity;
    SkillManager skillManager;

    public BindWoundsAbility(Entity entity, SkillManager skillManager){
        this.entity = entity;
        this.skillManager = skillManager; //to avoid lod
    }

    public BindWoundsAbility(){}

    @Override
    public int execute() {
        System.out.println("DOING BINDWOUNDS ABILITY");

        int skillLevel = this.skillManager.getBindWoundsLevel();
        System.out.println("YOUR SKILL LEVEL: " + skillLevel);
        System.out.println("YOUR HEALTH: " + this.entity.getStats().getCurrentHealth());
        int health = skillLevel * 2;

        this.entity.gainHealth(health);
        this.entity.getStats().subtract(new HealthManaExperienceAddable(0,0,1,0,0));
        //TODO: maybe add gainMana to entity?

        System.out.println("HEAL " + health + " HP");
        System.out.println("YOUR HEALTH NOW: " + this.entity.getStats().getCurrentHealth());

        //This is however long it will take to bind wounds
        return 30;
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
