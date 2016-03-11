package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.SaveLoad.SaveVisitor;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

import java.util.ArrayList;

/**
 * Created by Alex on 3/10/2016.
 */
public class MountAbility extends SelfBuffAbility {
    private ArrayList<EntityObserver> observers;
    private String mountName;
    private Entity entity;

    public MountAbility(Entity entity, Buff buff, ArrayList<EntityObserver> observers, String mountName, int windTicks, int coolTicks) {
        super(entity, buff, windTicks, coolTicks);
        this.observers = observers;
        this.mountName = mountName;
    }

    @Override
    public int execute() {
        int output;
        if ((output = super.execute()) > 0) {
            TimeModel.getInstance().registerAlertable(() -> {
                for (EntityObserver observer : observers) {
                    observer.alertMount(mountName);
                }
            }, getWindTicks());
        }
        return output;

    }

    @Override
    public void accept(ModelVisitor v) {
        v.visitMountAbility(this);
    }

    public String getMountName() {
        return mountName;
    }

    public void setMountName(String mountName) {
        this.mountName = mountName;
    }
}
