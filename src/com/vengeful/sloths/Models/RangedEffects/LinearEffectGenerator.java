package com.vengeful.sloths.Models.RangedEffects;

import com.sun.javafx.geom.Path2D;
import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.RangedEffects.HitBox.HitBox;
import com.vengeful.sloths.Models.RangedEffects.HitBox.HitBoxMovementCommand;
import com.vengeful.sloths.Models.RangedEffects.HitBox.MovableHitBox;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;

/**
 * Created by luluding on 3/8/16.
 */
public abstract class LinearEffectGenerator extends RangedEffectGenerator{

    private MovableHitBox hitBox;
    private int travelDistanceLeft;
    private int travelTime;
    private int totalTravelDistance;
    private Coord initialLocation;
    private Direction facingDirection;
    private int initialDmg;
    private int initialAccuracy;
    private DefaultCanGenerateVisitor canGenerateVisitor;

    public LinearEffectGenerator(String name, Coord location, Direction direction, int travelDistance, int travelTime, int initialDmg, int initialAccuracy, DefaultCanGenerateVisitor canGenerateVisitor){
        this.travelDistanceLeft = travelDistance;
        this.travelTime = travelTime;
        this.initialLocation = location;
        this.facingDirection = direction;
        this.initialDmg = initialDmg;
        this.initialAccuracy = initialAccuracy;
        this.totalTravelDistance = travelDistanceLeft;

        //this is created on the next tile (for visual effect), not the tile that entity is standing on.
        this.hitBox = new MovableHitBox(name, location, initialDmg, initialAccuracy, this.facingDirection);
        this.hitBox.takeDamage();
        travelDistanceLeft--;
        //////////////////////////So that can hit adjacent NPC immediately

        TemporaryVOCreationVisitor creator = TemporaryVOCreationVisitor.getInstance();
        this.hitBox.accept(creator);
        canGenerateVisitor = canGenerateVisitor;
    }


    /* This method kicks start the hitbox moving process,
        once movement to one tile is done, tickAlert() is called
        which handles the creation of next movement
        goes on until either travelDistanceLeft = 0 or hit a non-existence tile
     */
    public void createRangedEffect(){
        HitBoxMovementCommand hbmc = new HitBoxMovementCommand(this.initialLocation, this.facingDirection, this.hitBox, this.travelTime, this, this.hitBox.getObservers(), canGenerateVisitor);
        if(hbmc.execute() == 0){
            //Alert hitbox view destroy
            hitBox.alertObserverOnDestroy();
        }
        //if .execute() returns 0, movement chain breaks and we are done with this ability
    }

    protected void updateHitBox(){
        getHitBox().setDamage(calculateAtt(initialDmg, totalTravelDistance-travelDistanceLeft));
        getHitBox().setAccuracy(calculateAccuracy(initialAccuracy, totalTravelDistance-travelDistanceLeft));
    }

    //implements by subclasses
    public abstract void tickAlert();

    public HitBox getHitBox() {
        return hitBox;
    }

    public void setHitBox(MovableHitBox hitBox) {
        this.hitBox = hitBox;
    }

    public int getTravelDistanceLeft() {
        return travelDistanceLeft;
    }

    public void setTravelDistanceLeft(int travelDistanceLeft) {
        this.travelDistanceLeft = travelDistanceLeft;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    public Coord getInitialLocation() {
        return initialLocation;
    }

    public void setInitialLocation(Coord initialLocation) {
        this.initialLocation = initialLocation;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public int getInitialDmg() {
        return initialDmg;
    }

    public void setInitialDmg(int initialDmg) {
        this.initialDmg = initialDmg;
    }

    public int getInitialAccuracy() {
        return initialAccuracy;
    }

    public void setInitialAccuracy(int initialAccuracy) {
        this.initialAccuracy = initialAccuracy;
    }

    public int getTotalTravelDistance() {
        return totalTravelDistance;
    }

    public void setTotalTravelDistance(int totalTravelDistance) {
        this.totalTravelDistance = totalTravelDistance;
    }

    public DefaultCanGenerateVisitor getCanGenerateVisitor() {
        return canGenerateVisitor;
    }

    public void setCanGenerateVisitor(DefaultCanGenerateVisitor canGenerateVisitor) {
        this.canGenerateVisitor = canGenerateVisitor;
    }

}
