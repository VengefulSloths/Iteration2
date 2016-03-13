package com.vengeful.sloths.Models;

import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.PickPocketAbility;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;

/**
 * Created by zach on 2/22/16.
 */
public interface ModelVisitable {

    void accept(ModelVisitor modelVisitor);

}
