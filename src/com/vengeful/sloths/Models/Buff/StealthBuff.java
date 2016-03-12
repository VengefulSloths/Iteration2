package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

import java.util.ArrayList;

/**
 * Created by alexs on 3/12/2016.
 */
public class StealthBuff extends ActionRemovableBuff {
    public StealthBuff(ArrayList<EntityObserver> entityObservers, String name, StatsAddable statsAddable, BuffManager owner) {
        super(entityObservers, name, statsAddable, owner);
    }

    @Override
    public void doApply(Stats stats) {
        super.doApply(stats);
        for (EntityObserver observer: getObservers()) {
            observer.alertStealth();
        }
    }

    @Override
    public void doRemove(Stats stats) {
        super.doRemove(stats);
        for (EntityObserver observer: getObservers()) {
            observer.alertUnstealth();
        }
    }

    @Override
    public void refreshObservers() {
        super.refreshObservers();
        for (EntityObserver observer: getObservers()) {
            observer.alertStealth();
        }
    }
}
