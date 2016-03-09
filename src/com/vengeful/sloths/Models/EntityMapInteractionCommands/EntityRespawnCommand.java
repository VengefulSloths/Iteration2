package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.HexMath;

import java.util.Iterator;

/**
 * Created by John on 3/8/2016.
 */
public class EntityRespawnCommand implements Alertable {
    Entity entity;
    Coord respawnCoord;
    int timeToRespawn;

    public EntityRespawnCommand(Entity entity, Coord respawnCoord, int timeToRespawn){
        this.entity = entity;
        this.respawnCoord = respawnCoord;
        this.timeToRespawn = timeToRespawn;

        TimeModel.getInstance().registerAlertable(this, timeToRespawn);
    }

    @Override
    public int execute() {
        return 0;
    }

    @Override
    public void mAlert() {
        entity.getStats().resetStats();
        entity.setDead(false);  // Bring entity back to life
        entity.setActive(false); // Even more back to life


        //Coord respawnCoordCopy = new Coord(respawnCoord.getR(), respawnCoord.getS());
        Coord resCoord = HexMath.getClosestMovableTile(respawnCoord); // get closest tile to respawnCoord (may be respawnCoord) to respawn at
        Map.getInstance().getTile(resCoord).addEntity(entity);

        entity.setLocation(resCoord);

        System.out.println("entity location is :" + entity.getLocation());
        System.out.println("entity dead is : " + entity.isDead());
        System.out.println("entity is active: " + entity.isActive());
        entity.locationChange();




    }
}
