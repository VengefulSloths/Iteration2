package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
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
        this.buffs = new ArrayList<Buff>();
        TimeModel.getInstance().registerTickable(this);
    }

    //////////////////////public api//////////////////////////////////
    public void addBuff(Buff buff){
        buff.apply(entity.getStats());
        this.buffs.add(buff);
    }

    public void removeBuff(Buff buff){
        buff.destroy(entity.getStats());
        this.buffs.remove(buff);
    }
    public void destroy(){
        TimeModel.getInstance().removeTickable(this);
    }
//    public ArrayList<Buff> getBuffs(){
//        return buffs;
//    }
    @Override
    public void tick() {
        //System.out.println("tick"); //THIS LINE WAS TOO HARD TO FIND
        for (Iterator<Buff> iterator = buffs.iterator(); iterator.hasNext();) {
            Buff buff = iterator.next();
            buff.applyOnTick(entity.getStats());
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
}
