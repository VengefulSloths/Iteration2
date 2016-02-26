package com.vengeful.sloths.Controllers.Target;

/**
 * Created by zach on 2/25/16.
 */
public class NonAgressiveNPCTarget extends Target implements TargetVisitable {

    public NonAgressiveNPCTarget(int priority) {
        super(priority);
    }

    @Override
    public void accept(TargetVisitor targetVisitor) {
        //do stuff
    }
}
