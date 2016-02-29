package com.vengeful.sloths.Controllers.ActionController;

import com.vengeful.sloths.Controllers.Target.*;

/**
 * Created by John on 2/29/2016.
 */
public class AggressiveNPCActionController extends ActionController {
    @Override
    public void action(Target target) {
        target.accept(this);
    }

    @Override
    public void visitAvatarTarget(AvatarTarget avatar) {
        //attack the avatar
        
    }

    @Override
    public void visitPiggyTarget(PiggyTarget piggy) {
        //attack the piggy
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
