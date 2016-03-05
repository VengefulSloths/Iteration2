package com.vengeful.sloths.Controllers.Target;

/**
 * Created by zach on 3/4/16.
 */
public class MapItemTarget extends Target {

    public MapItemTarget(int priority) {
        super(priority);
    }
    @Override
    public void accept(TargetVisitor targetVisitor) {
        targetVisitor.visitMapItemTarget(this);
    }
}
