package com.vengeful.sloths.Controllers.ActionController;

import com.vengeful.sloths.Controllers.Target.*;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Utility.Direction;

import java.util.Random;

/**
 * Created by John on 3/14/2016.
 */
public class NonAggressiveNPCActionController extends ActionController {

    private int counter =(int) (new Random()).nextInt(21) ;
    private Direction dir;
    private int numSteps = 0;

    public NonAggressiveNPCActionController(Entity entity, Direction initDirection, int numSteps){
        super(entity);
        dir = initDirection;
        this.numSteps = 2 * numSteps;
    }

    private void changeDirection(){
        dir = dir.oppositeDirection;
    }
    @Override
    public void action(Target target) {
        if(numSteps != 0){
            if(counter % numSteps == 0){
                this.changeDirection();
            }
            this.getEntity().move(dir);


            ++counter;
        }
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
