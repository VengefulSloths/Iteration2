package com.vengeful.sloths.Models.Buff;

import com.vengeful.sloths.Models.Entity.Entity;

import java.util.ArrayList;

/**
 * Created by luluding on 2/21/16.
 */
public class BuffManager {

    private ArrayList<Buff> buffs;
    private Entity entity;

    public BuffManager(Entity entity){
        this.entity = entity;
        this.buffs = new ArrayList<Buff>();
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


}
