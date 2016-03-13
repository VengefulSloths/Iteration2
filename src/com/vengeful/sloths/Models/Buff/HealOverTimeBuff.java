package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Observers.EntityObserver;
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

    public HealOverTimeBuff(ArrayList<EntityObserver> entityObservers, BuffManager owner, StatsAddable buff, String name, int duration, int interval) {
        super(entityObservers, owner, buff, name, duration);
        this.interval = interval;
        this.owner = owner;
    }

    @Override
    public void doApply(Stats stats) {
        super.doApply(stats);
        healOverTime(stats);
    }

    private void healOverTime(Stats stats){
        if(owner != null) //a bit hack may modify later
            stats.add(getBuff());

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
}