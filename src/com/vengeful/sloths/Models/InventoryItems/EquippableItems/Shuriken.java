package com.vengeful.sloths.Models.InventoryItems.EquippableItems;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityFactory;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.ModelConfig;
import com.vengeful.sloths.Utility.WeaponClass;

/**
 * Created by zach on 3/13/16.
 */
public class Shuriken extends Weapon {
    private WeaponClass weaponClass;

    public Shuriken(String name, StatsAddable stats, int baseDamage, WeaponClass weaponClass) {
        super(name, stats, baseDamage);
        this.weaponClass = weaponClass;
    }

    @Override
    public WeaponClass getWeaponClassification() { return weaponClass;}

    @Override
    public int calculateOffsensiveRating(Stats stats, SkillManager skills) {
        return (getBaseDamage() + stats.getAgility())*(1+skills.getRangedWeaponLevel());
    }

    @Override
    public Ability getAttackAbility(Entity entity) {
        return AbilityFactory.getInstance().createRangedAttackAbility(entity, getBaseDamage(), ModelConfig.THROW_WINDUP, ModelConfig.THROW_SPEED);
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitShuriken(this);
    }
}
