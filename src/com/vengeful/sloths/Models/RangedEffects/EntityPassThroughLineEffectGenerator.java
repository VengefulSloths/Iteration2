package com.vengeful.sloths.Models.RangedEffects;

import com.vengeful.sloths.Models.RangedEffects.HitBox.HitBoxMovementCommand;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by luluding on 3/8/16.
 */
public class EntityPassThroughLineEffectGenerator extends LinearEffectGenerator{

    public EntityPassThroughLineEffectGenerator(String name, Coord location, Direction direction, int travelDistance, int travelTime, int initialDmg, int initialAccuracy){
        super(name, location, direction, travelDistance, travelTime, initialDmg, initialAccuracy);
    }

    @Override
    public void tickAlert() {
        this.setTravelDistanceLeft(this.getTravelDistanceLeft()-1);
        this.updateHitBox();
        this.getHitBox().takeDamage();

        if(this.getTravelDistanceLeft() > 0){
            HitBoxMovementCommand hbmc = new HitBoxMovementCommand(this.getHitBox().getLocation(), this.getFacingDirection(), this.getHitBox(), this.getTravelTime(), this);
            if(hbmc.execute() == 0){
                //Alert hitbox view destroy
            }
        }else{
            System.out.println("LINE ATT DONE!!!");
            //Alert view destroy
        }
    }
}
