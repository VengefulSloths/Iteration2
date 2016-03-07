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
public class Smasher extends Occupation {

    public Smasher(Stats stats, SkillManager skillManager, AbilityManager abilityManager, Entity entity) {
        stats.add(new BaseStatsAddable(10, 0, 0, 0, 0));
        this.addSharedSkills(skillManager);
        skillManager.addSkill(new Skill("one-handed weapon"));
        skillManager.addSkill(new Skill("two-handed weapon"));
        skillManager.addSkill(new Skill("brawling"));

        this.addSharedAbility(abilityManager, entity);
    }

    @Override
    public void levelUp(Stats stats) {
        stats.add(new BaseStatsAddable(2, 1, 1, 1, 0));
    }

    public void accept(ModelVisitor mv){
        mv.visitSmasher(this);
    }
}
