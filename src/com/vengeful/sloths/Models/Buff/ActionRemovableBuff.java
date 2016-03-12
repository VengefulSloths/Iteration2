package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;

import java.util.ArrayList;

/**
 * Created by Alex on 3/12/2016.
 */
public class ActionRemovableBuff extends PermanantBuff {

    private BuffManager owner;

    public ActionRemovableBuff(ArrayList<EntityObserver> entityObservers, String name, StatsAddable statsAddable, BuffManager owner) {
        super(entityObservers, name, statsAddable);
        this.owner = owner;
    }

    @Override
    public final void modifyDamage(StatsAddable statsAddable) {
        owner.removeBuff(this);
    }


}
