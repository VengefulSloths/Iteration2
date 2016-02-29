package com.vengeful.sloths.Controllers.ActionController;

import com.vengeful.sloths.Controllers.Target.*;
import com.vengeful.sloths.Models.Entity.Entity;

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
