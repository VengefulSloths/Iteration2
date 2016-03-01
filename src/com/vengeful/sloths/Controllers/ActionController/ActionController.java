package com.vengeful.sloths.Controllers.ActionController;

import com.vengeful.sloths.Controllers.Target.*;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by zach on 2/22/16.
 */
public abstract class ActionController implements TargetVisitor {

    private Entity entity;

    public ActionController(){}
    public ActionController(Entity entity){
        this.setEntity(entity);
    }

    public abstract void action(Target target);


    @Override
    public abstract void visitAvatarTarget(AvatarTarget avatar);

    @Override
    public abstract void visitPiggyTarget(PiggyTarget piggy);

    @Override
    public abstract void visitAggressiveNPCTarget(AgressiveNPCTarget aNPC);

    @Override
    public abstract void visitNonAggressiveNPCTarget(NonAgressiveNPCTarget nonANPC);

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    protected Direction getTargetDirection(Target target){
        int Rmag = entity.getLocation().getR() - target.getCoord().getR();
        int Smag = entity.getLocation().getS() - target.getCoord().getS();
        double angle = Math.atan(Smag/Rmag);
        angle *= 57.3; //wtf am i doing, i dont even know math
        //int direction = (int)angle % 60;
        angle %= 360;
        if(angle > 330 || angle <= 30){
            return Direction.N;
        }else if (angle > 30 && angle <= 90){
            return Direction.NE;
        }else if (angle > 90 && angle <= 150){
            return Direction.SE;
        }else if (angle > 150 && angle <= 210){
            return Direction.S;
        }else if (angle > 210 && angle <= 270){
            return Direction.SW;
        }else if (angle > 270 && angle <= 330){
            return Direction.NW;
        }

        System.out.println("johns shitty directional code is breaking");
        return Direction.N; //should not happen
    }

    protected boolean checkLocation(Target target, int distance){
        if((Math.abs(target.getCoord().getR()) - Math.abs(entity.getLocation().getR())) > distance){
            return false;
        }
        if((Math.abs(target.getCoord().getS()) - Math.abs(entity.getLocation().getS())) > distance){
            return false;
        }
        //will only get here to return true if the target is in desired location
        return true;
    }
}
