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
 * Created by luluding on 2/22/16.
 */
public class OneHandedWeapon extends Weapon implements ModelVisitable {

    public OneHandedWeapon(String name, StatsAddable stats, int baseDamage){
        super(name, stats, baseDamage);
    }
    public OneHandedWeapon(){}

    @Override
    public WeaponClass getWeaponClassification() {
        return WeaponClass.ONE_HAND;
    }


    @Override
    public int calculateOffsensiveRating(Stats stats, SkillManager skills) {
        return (getBaseDamage() + stats.getStrength())*(1+skills.getOneHandedLevel());
    }

    @Override
    public Ability getAttackAbility(Entity entity) {
        return AbilityFactory.getInstance().createMeleeAttackAbility(entity, ModelConfig.getOneHandedWindup(), ModelConfig.getOneHandedSpeed());
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitOneHandedWeapon(this);
    }

    @Override
    public void interact() {

    }
}
