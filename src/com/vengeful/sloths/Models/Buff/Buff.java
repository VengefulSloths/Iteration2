package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.GenericStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StrengthAddable;
import com.vengeful.sloths.Models.Stats.Stats;

import java.util.ArrayList;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class Buff implements ModelVisitable{

    private ArrayList<EntityObserver> observers;
    private String name;

    public Buff(ArrayList<EntityObserver> entityObservers, String name) {
        this.observers = entityObservers;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public ArrayList<EntityObserver> getObservers() {
        return observers;
    }

    public final void apply(Stats stats) {
        for (EntityObserver observer: observers) {
            observer.alertAddBuff(getName());
        }
        doApply(stats);
    }

    public final void remove(Stats stats) {
        for (EntityObserver observer: observers) {
            observer.alertRemoveBuff(getName());
        }
        doRemove(stats);

    }

    public abstract void doRemove(Stats stats);

    public abstract void doApply(Stats stats);

    public void modifyDamage(StatsAddable statsAddable) {
        //default do nothing
    }

    public abstract StatsAddable getBuff();

    public abstract boolean applyOnTick(Stats stats);

    public void refreshObservers () {
        for (EntityObserver observer: observers) {
            observer.alertAddBuff(getName());
        }
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitBuff(this);
    }

}
