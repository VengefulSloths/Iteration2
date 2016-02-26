package com.vengeful.sloths.Controllers.Target;

/**
 * Created by zach on 2/25/16.
 */
public interface TargetVisitable {
    void accept(TargetVisitor targetVisitor);
}
