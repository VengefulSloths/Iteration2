package com.vengeful.sloths.Models.Occupation;


import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.BaseStatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by zach on 1/30/16.
 */
public class Sneak extends Occupation {

    public Sneak(Stats stats, SkillManager skillManager, AbilityManager abilityManager, Entity entity) {
        stats.add(new BaseStatsAddable(0, 10, 0, 0, 0));
        this.addSharedSkills(skillManager);
        skillManager.addSkill(new Skill("pick pocket"));
        skillManager.addSkill(new Skill("remove trap"));
        skillManager.addSkill(new Skill("creep"));
        skillManager.addSkill(new Skill("ranged weapon"));

        this.addSharedAbility(abilityManager, entity);
    }

    @Override
    public void levelUp(Stats stats) {
        stats.add(new BaseStatsAddable(1, 2, 1, 1, 0));
    }

    public void accept(ModelVisitor mv){
        mv.visitSneak(this);
    }
}
