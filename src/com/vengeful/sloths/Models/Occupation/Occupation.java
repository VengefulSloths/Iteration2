package com.vengeful.sloths.Models.Occupation;


import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.Stats;


/**
 * Created by zach on 1/30/16.
 */
public abstract class Occupation implements ModelVisitable{

    public abstract void levelUp(Stats stats);
    public abstract void accept(ModelVisitor mv);

    protected void addSharedSkills(SkillManager skillManager){

        skillManager.addSkill(new Skill("bind wounds"));
        skillManager.addSkill(new Skill("bargain"));
        skillManager.addSkill(new Skill("observation"));

        //TODO: test, remove, and uncomment subclass
        skillManager.addSkill(new Skill("bane", 10, 10));
        skillManager.addSkill(new Skill("boon", 10, 10));
        skillManager.addSkill(new Skill("enchantment", 10, 10));

    }

    protected void addSharedAbility(AbilityManager abilityManager, Entity entity){
        //BindWoundsAbility bwa = AbilityFactory.getInstance().createBindWoundsAbility(entity, entity.getSkillManager(), 2, 5);
        //bwa.setItemName("fireball");
        //abilityManager.addAbility(bwa);
        //abilityManager.equipAbility(bwa, 1);

        //TODO: test, remove


        /*
        ExplosionAbility ea = new ExplosionAbility(entity, 10, 3, 5, 5);
        abilityManager.addAbility(ea);
        abilityManager.equipAbility(ea, 1);


        FlameThrowerAbility ea = new FlameThrowerAbility(entity, 10, 5, 5, 5);
        ea.setItemName("AngelSpell");
        abilityManager.addAbility(ea);
        abilityManager.equipAbility(ea, 0);


        RemoveTrapAbility rta = new RemoveTrapAbility(entity, 3, 5);
        abilityManager.addAbility(rta);
        abilityManager.equipAbility(rta, 3);

        FireBallAbility fba = new FireBallAbility(entity, 10, 3, 30, 40);
        abilityManager.addAbility(fba);
        abilityManager.equipAbility(fba, 2);*/

    }
}
