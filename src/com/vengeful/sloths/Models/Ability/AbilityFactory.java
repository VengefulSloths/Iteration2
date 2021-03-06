package com.vengeful.sloths.Models.Ability;

import com.vengeful.sloths.Models.Ability.Abilities.*;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.PickPocketAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.StealthAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities.*;
import com.vengeful.sloths.Models.Buff.*;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Stats.StatAddables.CurrentHealthAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.GenericStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.MovementAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StrengthAddable;
import com.vengeful.sloths.Utility.ModelConfig;

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
        return new SelfBuffAbility(name, "Blame the coders for this", entity, buff, windTime, coolTime);
    }

    public RemoveBuffAbility createRemoveBuffAbility(String name, Buff buff, BuffManager buffManager, Entity entity) {
        return new RemoveBuffAbility(name, buff, buffManager, entity);
    }

    public MountAbility createMountAbility( Entity entity, int moveSpeed, String mountName, int windTime, int coolTime) {
        Buff mountBuff = new MountBuff("mount", new MovementAddable(moveSpeed), entity.getBuffManager(), entity.getObservers(), mountName);
        return new MountAbility(entity, mountBuff, mountName, windTime, coolTime);
    }

    public StealthAbility createStealthAbility(Entity entity) {
        Buff stealthBuff = new StealthBuff(entity.getObservers(), "Creep", new GenericStatsAddable(30,20,0,0,0,0,0,0,0,0), entity.getBuffManager());
        return new StealthAbility(entity, stealthBuff, 20,25);
    }

    public SelfBuffAbility createProtectFromEvil(Entity entity) {
        Buff timedBuff = new ProtectFromEvilBuff(entity.getObservers(), entity.getBuffManager(), new GenericStatsAddable(), "protection", 2, 300);
        return new BoonBuffAbility("Mana Shield", "Block two hits from the enemy", entity, timedBuff, 8, 15);
    }

    public SelfBuffAbility createDamageBoost(Entity entity){
        Buff damageBuff = new TimedBuff(entity.getObservers(), entity.getBuffManager(), new StrengthAddable(entity.getStats().getStrength()*2), "damageBoost", 300);
        return new BoonBuffAbility("Roids", "Because you weren't strong enough already", entity, damageBuff, 8, 15);
    }

    public SelfBuffAbility createHealOverTime(Entity entity){
        Buff healBuff = new HealOverTimeBuff(entity, entity.getObservers(), entity.getBuffManager(), new CurrentHealthAddable(3), "healOverTime", 600, 60);
        return new BoonBuffAbility("Rejuvenation", "A nice slow heal", entity, healBuff, 8, 15);
    }

    public FireBallAbility createFireBallAbility(Entity entity){
        return new FireBallAbility(entity, 10, 5, 30, 35);
    }

    public ExplosionAbility createExplosionAbility(Entity entity, int expandingTime, int expandingDistance, int startupTicks, int coolDownTicks){
        return new ExplosionAbility(entity, expandingTime, expandingDistance, startupTicks, coolDownTicks);
    }

    public FlameThrowerAbility createAngleSpellAbility(Entity entity){
        return new FlameThrowerAbility(entity, 5, 4, 50, 55);
    }

    public NPCFallAsleepAbility createNPCFallAsleepAbility(Entity entity){
        return new NPCFallAsleepAbility(entity, 8, 15, 360);
    }
    public PoisonNPCAbility createPoisonNPCAbility(Entity entity){
        return new PoisonNPCAbility(entity, 8, 15);
    }

    public WeakenNPCAbility createWeakenNPCAbility(Entity entity) {
        return new WeakenNPCAbility(entity, 8, 15);
    }

    public RangedAttackAbility createRangedAttackAbility(Entity entity, int baseDamage, int windTicks, int coolTicks) {
        return new RangedAttackAbility(entity, entity.getSkillManager().getRangedWeaponSkill(), baseDamage, windTicks, coolTicks);
    }
    public PickPocketAbility createPickPocketAbility(){
        return new PickPocketAbility();
    }

    public ObservationAbility createObservationAbility(Entity e){
        return new ObservationAbility(e);
    }

}
