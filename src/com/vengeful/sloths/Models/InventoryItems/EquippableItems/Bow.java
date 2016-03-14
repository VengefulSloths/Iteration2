package com.vengeful.sloths.Models.InventoryItems.EquippableItems;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityFactory;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.ModelConfig;
import com.vengeful.sloths.Utility.WeaponClass;

import java.util.Iterator;

/**
 * Created by Alex on 3/13/2016.
 */
public class Bow extends Weapon {
//    private WeaponClass weaponClass;

    public Bow(String name, StatsAddable stats, int baseDamage) {
        super(name, stats, baseDamage);
//        this.weaponClass = weaponClass;
    }

    public Bow (){
    }

    @Override
    public WeaponClass getWeaponClassification() { return WeaponClass.BOW;}

    @Override
    public int calculateOffsensiveRating(Stats stats, SkillManager skills) {
        return (getBaseDamage() + stats.getAgility())*(1+skills.getRangedWeaponLevel());
    }

    @Override
    public Ability getAttackAbility(Entity entity) {
        return AbilityFactory.getInstance().createRangedAttackAbility(entity, getBaseDamage(), ModelConfig.BOW_WINDUP, ModelConfig.BOW_SPEED);
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitBow(this);
    }

}
