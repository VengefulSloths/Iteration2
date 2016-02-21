package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/21/16.
 */
public class BuffOverTime extends Buff{
    private StatsAddable buff;
    private int duration;

    public BuffOverTime(StatsAddable buff, int duration){
        this.buff = buff;
        this.duration = duration;
    }


    ////////////////////public api//////////////////////////
    public void apply(Stats stats){
        stats.add(buff);
    }

    public void destroy(Stats stats){
        stats.subtract(buff);
    }

    public void applyOnTick(Stats stats){
        stats.add(buff);
    }
}
