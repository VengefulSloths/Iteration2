package com.vengeful.sloths.Controllers.ActionController;

import com.vengeful.sloths.Controllers.Target.*;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by John on 2/29/2016.
 */
public class AggressiveNPCActionController extends ActionController {

    public AggressiveNPCActionController(Entity entity){
        this.setEntity(entity);
    }

    @Override
    public void action(Target target) {
        if(target != null) {
            target.accept(this);
        }else{
            //System.out.println("idle");
        }
    }

    @Override
    public void visitAvatarTarget(AvatarTarget avatar) {
        //attack the avatar

        if(this.checkLocation(avatar, 1)) { //1 meaning he can attack an adjacent square
            //make attack command
            //System.out.println("attacking!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            this.getEntity().attack(this.getTargetDirection(avatar)); //tweak a bit later

        }else{
            //move towards it
            //bfs here
            //actually gonna try shitty directional code to not do bfs kek
            Direction desiredDirection = this.getPathDirection(avatar);

            this.getEntity().move(desiredDirection);
        }
    }

    @Override
    public void visitPiggyTarget(PiggyTarget piggy) {
        //attack the piggy
        if(this.checkLocation(piggy, 1)){
            //make attack command
        }else{
            //move towards it, bfs
        }
    }

    @Override
    public void visitAggressiveNPCTarget(AggressiveNPCTarget aNPC) {
        //probably do nothing
    }

    @Override
    public void visitNonAggressiveNPCTarget(NonAgressiveNPCTarget nonANPC) {
        //maybe attack
    }

    @Override
    public void visitMapItemTarget(MapItemTarget mapItemTarget) {}
}
