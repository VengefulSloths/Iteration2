package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.DefaultCanMoveVisitor;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.WaterAndLandMoveVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

import java.util.ArrayList;

/**
 * Created by Alex on 3/12/2016.
 */
public class MountBuff extends ActionRemovableBuff {
    private String mountName;

    public MountBuff(String name, StatsAddable statsAddable, BuffManager owner, ArrayList<EntityObserver> observers, String mountName) {
        super(observers, name, statsAddable, owner);
        this.mountName = mountName;
    }

    @Override
    public void doApply(Stats stats) {
        super.doApply(stats);
        Avatar.getInstance().setMovementValidator(new WaterAndLandMoveVisitor());
        for (EntityObserver observer: getObservers()) {
            observer.alertMount(mountName);
        }
     }

    @Override
    public void doRemove(Stats stats) {
        super.doRemove(stats);
        Avatar.getInstance().setMovementValidator(new DefaultCanMoveVisitor());
        for (EntityObserver observer: getObservers()) {
            observer.alertDemount();
        }
    }

    @Override
    public void refreshObservers() {
        super.refreshObservers();
        for (EntityObserver observer: getObservers()) {
            observer.alertMount(mountName);
        }
    }
}
