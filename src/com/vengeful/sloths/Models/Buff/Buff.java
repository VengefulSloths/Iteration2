package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StrengthAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/21/16.
 */
public class Buff {

    private StatsAddable buff;
    private int duration;

    public Buff(){
        buff = null;
        duration = 0;
    }

    public Buff(StatsAddable buff, int duration){
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

    public boolean applyOnTick(Stats stats){
        this.duration -= 1;
        if(duration <= 0){
            destroy(stats);
            return true;
        }
        return false;
    }
}
