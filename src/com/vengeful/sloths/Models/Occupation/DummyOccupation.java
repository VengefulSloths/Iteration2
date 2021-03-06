package com.vengeful.sloths.Models.Occupation;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.BaseStatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/21/16.
 */
public class DummyOccupation extends Occupation{

    public DummyOccupation(Stats stats, SkillManager skillManager, AbilityManager abilityManager, Entity entity){
        //does nothing
    }
    public DummyOccupation(){}

    @Override
    public void levelUp(Stats stats) {
        //does nothing
    }

    @Override
    public void accept(ModelVisitor mv) {
        mv.visitDummyOcc(this);
    }

}
