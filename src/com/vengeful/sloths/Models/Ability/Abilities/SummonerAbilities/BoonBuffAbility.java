package com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities;

import com.vengeful.sloths.Models.Ability.Abilities.SelfBuffAbility;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.ProtectFromEvilBuff;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.GenericStatsAddable;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.ModelConfig;

import java.util.Iterator;

/**
 * Created by luluding on 3/12/16.
 */

//any boon related spell that modified player through buff can use this
public class BoonBuffAbility extends SelfBuffAbility{
    /* Summoner ability, uses boon level */

    private int manaCost = ModelConfig.getManaCostMid();

    public BoonBuffAbility(String name, Entity entity, Buff buff, int windTicks, int coolTicks){
        super(name, entity, buff, windTicks, coolTicks);
        //Buff timedBuff = new ProtectFromEvilBuff(getTarget().getObservers(), getTarget().getBuffManager(), new GenericStatsAddable(), "protection", 2, 300);
    }

    @Override
    public int execute() {
        if(!shouldDoAbility(getTarget().getSkillManager().getBoonLevel(), getTarget().getSkillManager().getMaxBoonLevel()))
            return 0;

        if(getTarget().getStats().getCurrentMana() < manaCost)
            return 0;

        int output = super.execute();
        if(output > 0){
            getTarget().decMana(manaCost);
        }

        return output;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public void accept(ModelVisitor mv){
        mv.visitBoonBuffAbility(this);
    }
}
