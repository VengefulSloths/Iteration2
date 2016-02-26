package com.vengeful.sloths.Controllers.Target;

/**
 * Created by zach on 2/25/16.
 */
public class AgressiveNPCTarget extends Target implements TargetVisitable {


    public AgressiveNPCTarget(int priority) {
        super(priority);
    }

    @Override
    public void accept(TargetVisitor targetVisitor) {
        targetVisitor.visitAggressiveNPCTarget(this);
    }

}
