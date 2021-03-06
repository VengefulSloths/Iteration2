package com.vengeful.sloths.Models.RangedEffects;

import com.vengeful.sloths.Models.RangedEffects.CanGenerateVisitor.DefaultCanGenerateVisitor;
import com.vengeful.sloths.Models.RangedEffects.HitBox.HitBoxMovementCommand;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by luluding on 3/8/16.
 */
public class EntityPassThroughLineEffectGenerator extends LinearEffectGenerator{

    public EntityPassThroughLineEffectGenerator(String name, Coord location, Direction direction, int travelDistance, int travelTime, int initialDmg, int initialAccuracy, DefaultCanGenerateVisitor canGenerateVisitor){
        super(name, location, direction, travelDistance, travelTime, initialDmg, initialAccuracy, canGenerateVisitor);
    }

    @Override
    public void tickAlert() {
        this.setTravelDistanceLeft(this.getTravelDistanceLeft()-1);
        this.updateHitBox();
        this.getHitBox().takeDamage();

        if(this.getTravelDistanceLeft() > 0){
            HitBoxMovementCommand hbmc = new HitBoxMovementCommand(this.getHitBox().getLocation(), this.getFacingDirection(), this.getHitBox(), this.getTravelTime(), this, this.getHitBox().getObservers(), getCanGenerateVisitor());
            if(hbmc.execute() == 0){
                this.getHitBox().alertObserverOnDestroy();
            }
        }else{
            System.out.println("LINE ATT DONE!!!");
            this.getHitBox().alertObserverOnDestroy();
        }
    }
}
