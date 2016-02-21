package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/21/16.
 */
public class BuffOverTime extends Buff{

    public BuffOverTime(StatsAddable buff, int duration){
        this.setBuff(buff);
        this.setDuration(duration);
    }


    ////////////////////public api//////////////////////////
    public void apply(Stats stats){
        stats.add(this.getBuff());
    }

    public void destroy(Stats stats){
        stats.subtract(this.getBuff());
    }

    public boolean applyOnTick(Stats stats){
        stats.add(this.getBuff());
        this.setDuration(this.getDuration() - 1);
        if(this.getDuration() <= 0){
            destroy(stats);
            return true;
        }
        return false;
    }
}
