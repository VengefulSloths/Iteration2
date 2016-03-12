package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StrengthAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class Buff implements ModelVisitable{

    private StatsAddable buff;
    private int duration;


    public Buff(StatsAddable buff, int duration){
        this.buff = buff;
        this.duration = duration;
    }

    ////////////////getters/setters/////////////////////////


    public StatsAddable getBuff() {
        return buff;
    }

    public void setBuff(StatsAddable buff) {
        this.buff = buff;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    ////////////////////public api//////////////////////////
    public void apply(Stats stats){
        stats.add(buff);
    }

    public void destroy(Stats stats){
        stats.subtract(buff);
    }

    public void modifyDamage(StatsAddable statsAddable) {
        //default do nothing
    }

    public boolean applyOnTick(Stats stats){
        this.duration -= 1;
        if(duration <= 0){
            destroy(stats);
            return true;
        }
        return false;
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitBuff(this);
    }

}
