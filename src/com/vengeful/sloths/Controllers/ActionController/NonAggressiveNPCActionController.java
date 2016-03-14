package com.vengeful.sloths.Controllers.ActionController;

import com.vengeful.sloths.Controllers.Target.*;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by John on 3/14/2016.
 */
public class NonAggressiveNPCActionController extends ActionController {

    private int counter = 0;
    private Direction dir;

    public NonAggressiveNPCActionController(Entity entity, Direction initDirection){
        super(entity);
        dir = initDirection;
    }

    private void changeDirection(){
        dir = dir.oppositeDirection;
    }
    @Override
    public void action(Target target) {
        if(counter % 4 == 0){
            this.changeDirection();
        }
        this.getEntity().move(dir);
        ++counter;
    }

    @Override
    public void visitAvatarTarget(AvatarTarget avatar) {

    }

    @Override
    public void visitPiggyTarget(PiggyTarget piggy) {

    }

    @Override
    public void visitAggressiveNPCTarget(AggressiveNPCTarget aNPC) {

    }

    @Override
    public void visitNonAggressiveNPCTarget(NonAgressiveNPCTarget nonANPC) {

    }

    @Override
    public void visitMapItemTarget(MapItemTarget mapItemTarget) {

    }
}
