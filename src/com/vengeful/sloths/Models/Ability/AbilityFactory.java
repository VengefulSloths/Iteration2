package com.vengeful.sloths.Models.Ability;

import com.vengeful.sloths.Models.Ability.Abilities.*;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.StealthAbility;
import com.vengeful.sloths.Models.Buff.*;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.GenericStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.MovementAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StrengthAddable;

import java.util.ArrayList;

/**
 * Created by luluding on 3/7/16.
 */
public class AbilityFactory {

    //remove trap needs to know map

    private static AbilityFactory instance = new AbilityFactory();

    public static AbilityFactory getInstance() {
        return instance;
    }

    private AbilityFactory() {
    }


    public MeleeAttackAbility createMeleeAttackAbility(Entity entity, Skill relevantSkill, int baseDamage, int windTime, int coolTime) {
        return new MeleeAttackAbility(entity, relevantSkill, baseDamage, windTime, coolTime);
    }

    public BindWoundsAbility createBindWoundsAbility(Entity entity, SkillManager skillManager, int startupTime, int cooldownTime){
        return new BindWoundsAbility(entity, skillManager, startupTime, cooldownTime);
    }

    public SelfBuffAbility createSelfBuffAbility(Entity entity, Buff buff, int windTime, int coolTime) {
        return new SelfBuffAbility(entity, buff, windTime, coolTime);
    }

    public RemoveBuffAbility createRemoveBuffAbility(Buff buff, BuffManager buffManager, Entity entity) {
        return new RemoveBuffAbility(buff, buffManager, entity);
    }

    public MountAbility createMountAbility( Entity entity, int moveSpeed, String mountName, int windTime, int coolTime) {
        Buff mountBuff = new MountBuff("Mount", new MovementAddable(moveSpeed), entity.getBuffManager(), entity.getObservers(), mountName);
        return new MountAbility(entity, mountBuff, mountName, windTime, coolTime);
    }

    public StealthAbility createStealthAbility(Entity entity) {
        Buff stealthBuff = new StealthBuff(entity.getObservers(), "Creep", new StrengthAddable(30), entity.getBuffManager());
        return new StealthAbility(entity, stealthBuff, 20,25);
    }

    public SelfBuffAbility createProtectFromEvil(Entity entity) {
        Buff timedBuff = new ProtectFromEvilBuff(entity.getObservers(), entity.getBuffManager(), new GenericStatsAddable(), "protection", 2, 300);
        return new SelfBuffAbility(entity, timedBuff, 8, 15);
    }



}
