package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class NPC extends Entity{

    //pass in stats
    public NPC(String name, Stats stats){
        super(name, stats);
    }

    public void talk(){
        //create talk command
    }

    
}
