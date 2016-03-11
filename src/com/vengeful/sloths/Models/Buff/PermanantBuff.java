package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by Alex on 3/10/2016.
 */
public class PermanantBuff extends Buff {


    public PermanantBuff(StatsAddable buff) {
        super(buff, 0);
    }

    @Override
    public boolean applyOnTick(Stats stats) {
        //Do nothing because this is ok
        return false;
    }
}
