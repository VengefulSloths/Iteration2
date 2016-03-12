package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;

import java.util.ArrayList;

/**
 * Created by Alex on 3/12/2016.
 */
public class MountBuff extends ActionRemovableBuff {
    private ArrayList<EntityObserver> observers;

    public MountBuff(StatsAddable buff, BuffManager owner, ArrayList<EntityObserver> observers) {
        super(buff, owner);
        this.observers = observers;
    }

    @Override
    protected void modifyDamageHook() {
        for (EntityObserver observer: observers) {
            observer.alertDemount();
        }
    }
}
