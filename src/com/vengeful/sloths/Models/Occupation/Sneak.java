package com.vengeful.sloths.Models.Occupation;


import com.vengeful.sloths.Models.Ability.Abilities.BindWoundsAbility;
import com.vengeful.sloths.Models.Ability.Abilities.ObservationAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.RemoveTrapAbility;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityFactory;
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
        stats.add(new BaseStatsAddable(2, 10, 1, 3, 1));
        this.addSharedSkills(skillManager);
        skillManager.addSkill(new Skill("pick pocket"));
        Skill remove = new Skill("remove trap");
        remove.setLevel(10);
        skillManager.addSkill(remove); //TODO: uncomment after testing
        skillManager.addSkill(new Skill("creep"));
        skillManager.addSkill(new Skill("ranged weapon"));

        Ability a1 = AbilityFactory.getInstance().createStealthAbility(entity);
        abilityManager.addAbility(a1);
        abilityManager.equipAbility(a1,0);

        Ability a2 = AbilityFactory.getInstance().createPoisonNPCAbility(entity);
        abilityManager.addAbility(a2);
        abilityManager.equipAbility(a2,1);

        Ability a3 = AbilityFactory.getInstance().createPickPocketAbility();
        abilityManager.addAbility(a3);
        abilityManager.equipAbility(a3,2);


        Ability a4 = new RemoveTrapAbility(entity,1,1);
        abilityManager.addAbility(a4);
        abilityManager.equipAbility(a4,3);

        abilityManager.addAbility(new BindWoundsAbility(entity));
        abilityManager.addAbility(new ObservationAbility(entity));


        this.addSharedAbility(abilityManager, entity);

    }
    public Sneak (){}
    @Override
    public void levelUp(Stats stats) {
        stats.add(new BaseStatsAddable(1, 2, 1, 1, 0));
    }

    public void accept(ModelVisitor mv){
        mv.visitSneak(this);
    }

    @Override
    public String toString(){
        return "Sneak";
    }
}
