package com.vengeful.sloths.Models.Ability;

import com.vengeful.sloths.Models.Ability.Abilities.*;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.StealthAbility;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Buff.MountBuff;
import com.vengeful.sloths.Models.Buff.StealthBuff;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Observers.EntityObserver;
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
    public MeleeAttackAbility createMeleeAttackAbility(Entity entity, int windTime, int coolTime) {
        return new MeleeAttackAbility(entity, windTime, coolTime);
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
        Buff stealthBuff = new StealthBuff(entity.getObservers(), "Creep", new StrengthAddable(entity.getStats().getStrength()), entity.getBuffManager());
        return new StealthAbility(entity, stealthBuff, 20,25);
    }


}
