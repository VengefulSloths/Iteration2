package com.vengeful.sloths.Controllers.ActionController;

import com.vengeful.sloths.Controllers.Target.*;
import com.vengeful.sloths.Models.Entity.Entity;

/**
 * Created by John on 2/29/2016.
 */
public class AggressiveNPCActionController extends ActionController {

    public AggressiveNPCActionController(Entity entity){
        this.setEntity(entity);
    }

    @Override
    public void action(Target target) {
        target.accept(this);
    }

    @Override
    public void visitAvatarTarget(AvatarTarget avatar) {
        //attack the avatar
        if(this.checkLocation(avatar, 1)) { //1 meaning he can attack an adjacent square
            //make attack command
        }else{
            //move towards it
            //bfs here
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
    public void visitAggressiveNPCTarget(AgressiveNPCTarget aNPC) {
        //probably do nothing
    }

    @Override
    public void visitNonAggressiveNPCTarget(NonAgressiveNPCTarget nonANPC) {
        //maybe attack
    }
}
