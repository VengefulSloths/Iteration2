package com.vengeful.sloths.Models.RangedEffects.HitBox;

import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.HitBoxObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;

import java.util.Iterator;

/**
 * Created by luluding on 3/10/16.
 */
public class MovableHitBox extends HitBox implements ModelVisitable{
    private Direction direction;
    private boolean isActive = false; //TODO: remove?

    public MovableHitBox(String name, Coord location, int dmg, int accuracy, Direction direction){
        super(name, location, dmg, accuracy);
        this.direction = direction;
        this.isActive = false;
    }

    public boolean isActive(){
        return this.isActive;
    }

    public void setActive(boolean active){
        this.isActive = active;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitMovableHitBox(this);
    }


    /* hold this off for now
    public void move(){
        Coord dst = HexMath.getNextFacingCoord(this.getLocation(), this.direction);

        boolean doesTileExist = HexMath.isValidTile(dst, Map.getInstance().getActiveMapArea().getMaxR(), Map.getInstance().getActiveMapArea().getMaxS());

        if(!doesTileExist){
            System.out.println("HITBOX: movement rejected " + this.getLocation().toString() + " to " + dst.toString());
            //this.alertMove();
        }
    }*/


    //private void alertMove(){
    //    Iterator<HitBoxObserver> observerIterator = this.getObservers();
        //while(observerIterator.hasNext()){
        //    observerIterator.next().alertMove();
            //Alert View
        //}
    //}
}
