package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;

/**
 * Created by Alex on 3/12/2016.
 */
public class ActionRemovableBuff extends PermanantBuff {

    private BuffManager owner;

    public ActionRemovableBuff(StatsAddable buff, BuffManager owner) {
        super(buff);
        this.owner = owner;
    }

    @Override
    public final void modifyDamage(StatsAddable statsAddable) {
        if (owner.removeBuff(this) ) {
            modifyDamageHook();
        }
    }

    protected void modifyDamageHook() {
        //default do nothing
    }
}
