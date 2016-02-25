package com.vengeful.sloths.Models.ActionCommandFactory;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by luluding on 2/25/16.
 */
public class PetMovementCommand extends MovementCommand{

    public PetMovementCommand(Map map, Coord src, Coord dst, Direction dir, Entity pet, int movementSpeed) {
        super(map, src, dst, dir, pet, movementSpeed);
    }

    public void doMove()
    {
        Tile sourceTile = map.getTile(this.src);

        try {
            Tile destTile = map.getTile(this.dst);

            if (destTile.canMove()) {
                sourceTile.removeNonCollideableEntity(this.entity);
                destTile.addNonCollideableEntity(this.entity);
                entity.setLocation(dst);
            }
        } catch (Exception e){
            //do something
        } finally {
            //NEED TO ALERT VIEW

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
        doMove();
        entity.setMoving(false);
    }



}
