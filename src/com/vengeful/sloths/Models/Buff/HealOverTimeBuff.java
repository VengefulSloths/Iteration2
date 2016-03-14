package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.CurrentHealthAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.GenericStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

import java.util.ArrayList;

/**
 * Created by luluding on 3/12/16.
 */
public class HealOverTimeBuff extends TimedBuff{

    private int interval;
    private BuffManager owner;
    private int healAmount =0;
    private Entity target;

    public HealOverTimeBuff(Entity target, ArrayList<EntityObserver> entityObservers, BuffManager owner, StatsAddable buff, String name, int duration, int interval) {
        super(entityObservers, owner, new CurrentHealthAddable(0), name, duration);
        this.interval = interval;
        this.healAmount = buff.getCurrentHealth();
        this.owner = owner;
        this.target = target;
    }

    @Override
    public void doApply(Stats stats) {
        super.doApply(stats);
        healOverTime(stats);
    }

    private void healOverTime(Stats stats){
        if(owner != null) //a bit hack may modify later
            target.takeDamage(-healAmount);

        if((getDuration()-interval) > 0)
            TimeModel.getInstance().registerAlertable(() -> {
                //stats.add(getBuff());
                healOverTime(stats);
                System.out.println("HHHHHHHHHHHHHHHEAL!!!!!");
            }, interval);
    }

    @Override
    public void doRemove(Stats stats) {
        //don't want to dec health after buff removed
        System.out.println("Heal Over Time Buff Ended");
        setDuration(0);
        owner = null; //won't affect superclass's owner
    }

    public void accept(ModelVisitor mv){
        mv.visitHealOverTimeBuff(this);
    }
}
