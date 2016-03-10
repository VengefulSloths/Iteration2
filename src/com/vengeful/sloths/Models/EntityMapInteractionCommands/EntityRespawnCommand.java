package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
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


        // @TODO:
        //  If map area changes, a bug happens where entity shows up there
        //  FIX THIS

        System.out.println("executing the respawn command");

        entity.getStats().resetStats();
        entity.setDead(false);  // Bring entity back to life
        entity.setActive(false); // Even more back to life

        Coord resCoord = HexMath.getClosestMovableTile(respawnCoord); // get closest tile to respawnCoord (may be respawnCoord) to respawn at
        Map.getInstance().addEntity(resCoord, entity);

        entity.accept(TemporaryVOCreationVisitor.getInstance());
    }
}
