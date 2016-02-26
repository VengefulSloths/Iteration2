package com.vengeful.sloths.Models.ActionCommandFactory;

import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.View.Observers.EntityObserver;

import java.util.Iterator;

/**
 * Created by zach on 1/30/16.
 */
public class AvatarMovementCommand extends MovementCommand {

    public AvatarMovementCommand(Map map, Coord src, Coord dst, Direction dir, Entity avatar, int movementSpeed) {
        super(map, src, dst, dir, avatar, movementSpeed);
        doMove(); //WHY IS THIS HERE not in execute()?
    }

    public void doMove()
    {
        Tile sourceTile = map.getTile(this.src);

        try {
            Tile destTile = map.getTile(this.dst);

            if (destTile.canMove()) {
//                sourceTile.removeEntity();
                destTile.addEntity(entity);
                entity.setLocation(dst);
            }
        } catch (Exception e){
            //not tile found
            //do something
        } finally {
//            Iterator<EntityObserver> iter = this.entity.entityObserverIterator();
//            while (iter.hasNext()) {
//                EntityObserver eo = iter.next();
//                eo.alertDirectionChange(this.direction);
//            }
        }

        map.getTile(this.entity.getLocation()).interact(entity);

        //System.out.Println("Avatar is at: " + entity.getLocation().getX() + ", " + entity.getLocation().getY());

    }

    @Override
    public void execute() {
        entity.setMoving(false);
    }


}