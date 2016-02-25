package com.vengeful.sloths.Controllers.Target;

/**
 * Created by zach on 2/25/16.
 */
public class AvatarTarget extends Target {

    public AvatarTarget(int priority) {
        super(priority);
    }

    @Override
    public void accept(TargetVisitor targetVisitor) {
        targetVisitor.visitAvatarTarget(this);
    }
}
