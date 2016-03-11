package com.vengeful.sloths.Models.Ability;

import com.vengeful.sloths.Models.Ability.Abilities.*;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Skills.SkillManager;

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

    public MountAbility createMountAbility( Entity entity, Buff buff, ArrayList<EntityObserver> observers, String mountName, int windTime, int coolTime) {
        return new MountAbility(entity, buff, observers, mountName, windTime, coolTime);
    }


}
