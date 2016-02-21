package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Buff.BuffManager;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class NPC extends Entity{

    //pass in stats
    public NPC(String name, BuffManager buffManager){
        super(name, buffManager);
    }

    public void talk(){
        //create talk command
    }

}