package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Ability.Hooks.RemoveBuffHook;
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
    private String mountName;
    private Entity entity;
    private Buff buff;
    public MountAbility(Entity entity, Buff buff, String mountName, int windTicks, int coolTicks) {
        super("Mount", "Because your inpatient", entity, buff, windTicks, coolTicks);
        this.mountName = mountName;
        this.entity = entity;
        this.buff = buff;
    }


    @Override
    public int execute() {
        int output;
        if ((output = super.execute()) > 0) {
            TimeModel.getInstance().registerAlertable(() -> {
                entity.getAbilityManager().addAbilityHook(new RemoveBuffHook(buff, entity.getBuffManager()));
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

    public String toString() { return "Mount"; }
}
