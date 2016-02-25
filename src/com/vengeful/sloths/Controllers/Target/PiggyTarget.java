package com.vengeful.sloths.Controllers.Target;

/**
 * Created by zach on 2/25/16.
 */
public class PiggyTarget extends Target implements TargetVisitable {

    public PiggyTarget(int priority) {
        super(priority);
    }

    @Override
    public void accept(TargetVisitor targetVisitor) {
        //do stuff
    }
}
