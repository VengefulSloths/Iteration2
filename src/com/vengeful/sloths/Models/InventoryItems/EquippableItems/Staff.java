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
 * Created by Alex on 3/13/2016.
 */
public class Staff extends Weapon {
    private WeaponClass weaponClass;
    public Staff(String name, StatsAddable stats, int baseDamage, WeaponClass weaponClass) {
        super(name, stats, baseDamage);
        this.weaponClass = weaponClass;
    }

    @Override
    public WeaponClass getWeaponClassification() {
        return weaponClass;
    }


    @Override
    public int calculateOffsensiveRating(Stats stats, SkillManager skills) {
        return (getBaseDamage() + stats.getStrength())*(1+skills.getStaffLevel());
    }

    @Override
    public Ability getAttackAbility(Entity entity) {
        return AbilityFactory.getInstance().createMeleeAttackAbility(entity, entity.getSkillManager().getOneHandSkill(), getBaseDamage(), ModelConfig.getOneHandedWindup(), ModelConfig.getOneHandedSpeed());
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitStaff(this);
    }

    @Override
    public void interact() {

    }
}
