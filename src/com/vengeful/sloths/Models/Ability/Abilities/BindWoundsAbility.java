package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.SaveLoad.SaveManager;
import com.vengeful.sloths.Models.SaveLoad.SaveVisitor;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.ModelConfig;

/**
 * Created by luluding on 3/6/16.
 */
public class BindWoundsAbility extends Ability {
    Entity entity;
    SkillManager skillManager;


    private int manaCost = ModelConfig.getManaCostLow();

    public BindWoundsAbility(Entity entity){
        super("Bind Wounds", 45, 60);
        this.entity = entity;
        this.skillManager = entity.getSkillManager();
    }


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
        }, getWindTicks());


        TimeModel.getInstance().registerAlertable(() -> entity.setActive(false), getCoolTicks());

        //This is however long it will take to bind wounds
        return getCoolTicks();
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

    public void accept(ModelVisitor sv){
        sv.visitBindWounds(this);
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setSkillManager(SkillManager skillManager) {
        this.skillManager = skillManager;
    }

    @Override
    public String toString(){
        return "Bind Wounds";
    }
}
