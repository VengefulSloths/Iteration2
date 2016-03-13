package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.StatAddables.GenericStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.TimeModel.Tickable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by luluding on 2/21/16.
 */
public class BuffManager implements Tickable, ModelVisitable {

    private ArrayList<Buff> buffs;
    private Entity entity;

    public BuffManager(Entity entity){
        this.entity = entity;
        this.buffs = new ArrayList<>();
        TimeModel.getInstance().registerTickable(this);
    }

    //////////////////////public api//////////////////////////////////
    public void modifyDamage(StatsAddable statsAddable) {
        for (int i = buffs.size() - 1; i >= 0; i--) {
            buffs.get(i).modifyDamage(statsAddable);
        }
    }

    public void addBuff(Buff buff){
        if (buffs.contains(buff)) {
            this.removeBuff(buff);
        }
        buff.apply(entity.getStats());
        this.buffs.add(buff);

    }

    public boolean removeBuff(Buff buff){
        if (buffs.remove(buff) ) {
            buff.remove(entity.getStats());
            return true;
        }
        return false;
    }
    public void destroy(){
        TimeModel.getInstance().removeTickable(this);
    }
//    public ArrayList<Buff> getBuffs(){
//        return buffs;
//    }
    @Override
    public void tick() {
        for (int i = buffs.size() - 1; i >=0; i-- ) {
            buffs.get(i).applyOnTick(entity.getStats());
        }
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitBuffManager(this);
    }

    public Buff[] getBuffs(){
        Buff[] bArrary = new Buff[buffs.size()];
        int i = 0;
        for(Buff b : buffs){
            bArrary[i] = b;
            ++i;
        }
        return bArrary;
    }

    public void alertObserversEverything() {
        for (Buff buff: buffs) {
            buff.refreshObservers();
        }
    }

    public GenericStatsAddable getAllBuffStatEffects(){
        GenericStatsAddable gsa = new GenericStatsAddable();
        Buff[] buffs = this.getBuffs();
        for(Buff b : buffs){
            gsa.add(b.getBuff());
        }
        return gsa;
    }
}
