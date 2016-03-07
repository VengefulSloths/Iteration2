package com.vengeful.sloths.Models.Ability;

import com.vengeful.sloths.Models.Ability.Abilities.BindWoundsAbility;
import com.vengeful.sloths.Models.Ability.Abilities.MeleeAttackAbility;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Skills.SkillManager;

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

    public BindWoundsAbility createBindWoundsAbility(Entity entity, SkillManager skillManager){
        return new BindWoundsAbility(entity, skillManager);
    }


}
