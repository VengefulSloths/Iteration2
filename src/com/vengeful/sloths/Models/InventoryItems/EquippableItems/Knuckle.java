package com.vengeful.sloths.Models.InventoryItems.EquippableItems;

import com.vengeful.sloths.Models.Ability.Abilities.MeleeAttackAbility;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityFactory;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.ModelConfig;
import com.vengeful.sloths.Utility.WeaponClass;

/**
 * Created by luluding on 3/7/16.
 */
public class Knuckle extends Weapon implements ModelVisitable{
    public Knuckle(String name, StatsAddable stats, int baseDamage){
        super(name, stats, baseDamage);
    }

    public Knuckle(){}

    @Override
    public WeaponClass getWeaponClassification() {
        return WeaponClass.FISTS;
    }


    @Override
    public int calculateOffsensiveRating(Stats stats, SkillManager skills) {
        return (getBaseDamage() + stats.getStrength())*(1+ skills.getBrawling());
    }

    @Override
    public Ability getAttackAbility(Entity entity) {
        return AbilityFactory.getInstance().createMeleeAttackAbility(entity, ModelConfig.getKnuckleWindup(), ModelConfig.getKnuckleSpeed());
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitKnuckle(this);
    }

    @Override
    public void interact() {

    }
}
