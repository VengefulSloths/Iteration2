package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Models.Observers.MovementObserver;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class Pet extends NPC{

    protected int timeToRespawn;

    public Pet(String name, Stats stats) { super(name, stats); }

    public int getTimeToRespawn() {
        return timeToRespawn;
    }

    public void setTimeToRespawn(int timeToRespawn) {
        this.timeToRespawn = timeToRespawn;
    }
}
