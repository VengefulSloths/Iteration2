package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

import java.util.ArrayList;

/**
 * Created by luluding on 3/13/16.
 */
public class PoisonBuff extends TimedBuff{
    private int interval;
    private boolean doOverTime;
    private StatsAddable repeatDamage;
    private BuffManager owner;

    public PoisonBuff(ArrayList<EntityObserver> entityObservers, BuffManager owner, StatsAddable oneTime, StatsAddable repeat, String name, int duration, int interval){
        super(entityObservers, owner, oneTime, name, duration);
        this.interval = interval;
        doOverTime = true;
        repeatDamage = repeat;
        this.owner = owner;
    }


    @Override
    public void doApply(Stats stats) {
        super.doApply(stats);
        poisonOverTime(stats);
    }

    private void poisonOverTime(Stats stats){
        if(doOverTime){
            stats.subtract(repeatDamage);
            for (EntityObserver observer: this.getObservers()) {
                observer.alertTakeDamage(repeatDamage.getCurrentHealth());
            }
        }

        if((getDuration()-interval) > 0)
            TimeModel.getInstance().registerAlertable(() -> {
                poisonOverTime(stats);
                //System.out.println("POISON: DMG!!!!");
            }, interval);
    }

    @Override
    public void doRemove(Stats stats) {
        //System.out.println("POISON Buff Ended");
        setDuration(0);
        doOverTime = false;

    }
}
