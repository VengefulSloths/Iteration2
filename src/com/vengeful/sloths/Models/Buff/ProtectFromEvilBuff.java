package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

import java.util.ArrayList;

/**
 * Created by Alex on 3/12/2016.
 */
public class ProtectFromEvilBuff extends TimedBuff{

    private int blockCount;
    private int counter;
    private BuffManager owner;

    public ProtectFromEvilBuff(ArrayList<EntityObserver> entityObservers, BuffManager owner, StatsAddable buff, String name, int blockCount, int duration) {
        super(entityObservers, owner, buff, name, duration);
        this.counter = blockCount;
        this.blockCount = blockCount;
        this.owner = owner;
    }

    @Override
    public void doApply(Stats stats) {
        super.doApply(stats);
        this.counter = blockCount;
    }

    @Override
    public void modifyDamage(StatsAddable statsAddable) {
        statsAddable.setCurrentHealth(0);
        if(--this.counter == 0) {
            this.owner.removeBuff(this);
        }
    }

    public void accept(ModelVisitor mv){
        mv.visitProtectFromEvilBuff(this);
    }
}
