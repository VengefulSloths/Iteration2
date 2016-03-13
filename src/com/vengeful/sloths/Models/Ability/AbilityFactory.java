package com.vengeful.sloths.Models.Ability;

import com.vengeful.sloths.Models.Ability.Abilities.*;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.StealthAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities.*;
import com.vengeful.sloths.Models.Buff.*;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.CurrentHealthAddable;
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

    public BindWoundsAbility createBindWoundsAbility(Entity entity){
        return new BindWoundsAbility(entity);
    }

    public SelfBuffAbility createSelfBuffAbility(String name, Entity entity, Buff buff, int windTime, int coolTime) {
        return new SelfBuffAbility(name, entity, buff, windTime, coolTime);
    }

    public RemoveBuffAbility createRemoveBuffAbility(String name, Buff buff, BuffManager buffManager, Entity entity) {
        return new RemoveBuffAbility(name, buff, buffManager, entity);
    }

    public MountAbility createMountAbility( Entity entity, int moveSpeed, String mountName, int windTime, int coolTime) {
        Buff mountBuff = new MountBuff("mount", new MovementAddable(moveSpeed), entity.getBuffManager(), entity.getObservers(), mountName);
        return new MountAbility(entity, mountBuff, mountName, windTime, coolTime);
    }

    public StealthAbility createStealthAbility(Entity entity) {
        Buff stealthBuff = new StealthBuff(entity.getObservers(), "Creep", new StrengthAddable(30), entity.getBuffManager());
        return new StealthAbility(entity, stealthBuff, 20,25);
    }

    public SelfBuffAbility createProtectFromEvil(Entity entity) {
        Buff timedBuff = new ProtectFromEvilBuff(entity.getObservers(), entity.getBuffManager(), new GenericStatsAddable(), "protection", 2, 300);
        return new BoonBuffAbility("Protect From Evil", entity, timedBuff, 8, 15);
    }

    public SelfBuffAbility createDamageBoost(Entity entity){
        Buff damageBuff = new TimedBuff(entity.getObservers(), entity.getBuffManager(), new StrengthAddable(entity.getStats().getStrength()*2), "damageBoost", 300);
        return new BoonBuffAbility("Roids", entity, damageBuff, 8, 15);
    }

    public SelfBuffAbility createHealOverTime(Entity entity){
        Buff healBuff = new HealOverTimeBuff(entity.getObservers(), entity.getBuffManager(), new CurrentHealthAddable(1), "healOverTime", 600, 60);
        return new BoonBuffAbility("Rejuvenation", entity, healBuff, 8, 15);
    }

    public FireBallAbility createFireBallAbility(Entity entity, int travelTime, int travelDistance, int startupTicks, int coolDownTicks){
        return new FireBallAbility(entity, travelTime, travelDistance, startupTicks, coolDownTicks);
    }

    public ExplosionAbility createExplosionAbility(Entity entity, int expandingTime, int expandingDistance, int startupTicks, int coolDownTicks){
        return new ExplosionAbility(entity, expandingTime, expandingDistance, startupTicks, coolDownTicks);
    }

    public AngleSpellAbility createAngleSpellAbility(Entity entity, int expandingTime, int expandingDistance, int startupTicks, int coolDownTicks){
        return new AngleSpellAbility(entity, expandingTime, expandingDistance, startupTicks, coolDownTicks);
    }

    public NPCFallAsleepAbility createNPCFallAsleepAbility(Entity entity){
        return new NPCFallAsleepAbility(entity, 8, 15, 360);
    }

}
