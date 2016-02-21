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
        doMove();
    }

    public void doMove()
    {
        ////System.out.Println(System.currentTimeMillis() + "executing movement command");
        Tile sourceTile = map.getTile(this.src);

        try {
            Tile destTile = map.getTile(this.dst);
            ////System.out.Println(" source BEFORE: " + sourceTile.getEntity());
            ////System.out.Println(destTile.getEntity());
            if (destTile.canMove()) {
                sourceTile.removeEntity();
                destTile.addEntity(entity);
                entity.setLocation(dst);

                Iterator<EntityObserver> iter = this.entity.entityObserverIterator();
                while (iter.hasNext()) {
                    EntityObserver eo = iter.next();
                    eo.alertMove(this.dst.getX(), this.dst.getY(), 200);
                }


                ////System.out.Println("My location: " + entity.getLocation().getX() + ", " + entity.getLocation().getY());


                /*
                //if there is takeable item on the tile, let entity pick it up
                if(destTile.getMapItemIterator().hasNext()){
                    //System.out.Println("Calling pick up!!!!");
                    ((Avatar)entity).pickup();
                }*/

                /*
                //Check if there is there is area of effect on map
                if(destTile.getAreaEffectIterator().hasNext()){
                    //System.out.Println("Calling creating effect!!!!");
                    destTile.createAEs();
                }*/

            }
        } catch (Exception e){
            //do something
        } finally {
            Iterator<EntityObserver> iter = this.entity.entityObserverIterator();
            while (iter.hasNext()) {
                EntityObserver eo = iter.next();
                eo.alertDirectionChange(this.direction);
            }
        }

        // Tile t = map.getTile()
        // can Tile t take an entity?
        // Move calling entity onto the tile
        map.getTile(this.entity.getLocation()).interact(entity);

        //System.out.Println("Avatar is at: " + entity.getLocation().getX() + ", " + entity.getLocation().getY());

    }

    @Override
    public void execute() {

        entity.setMoving(false);
    }


}