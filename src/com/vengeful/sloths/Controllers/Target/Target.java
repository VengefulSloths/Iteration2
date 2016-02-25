package com.vengeful.sloths.Controllers.Target;

import com.vengeful.sloths.Utility.Coord;

/**
 * Created by zach on 2/22/16.
 */
public abstract class Target implements TargetVisitable {
    private static final int HIGHEST_PRIORITY = 0;
    private static final int LOWEST_PRIORITY = 100;

    private Coord coord;
    private int priority; // highest <---> lowest

    public Target(int priority) {
        this.priority = priority;
    }

    public int getPriority() { return this.priority; }

    public void setPriority(int newPriority) {
        try {
            if (newPriority < HIGHEST_PRIORITY || newPriority > LOWEST_PRIORITY) {
                throw new Exception();
            } else {
                this.priority = newPriority;
            }
        } catch (Exception e) {
            System.out.println("Error: Attempt to set invalid priority was made.");
        }
    }

    public abstract void accept(TargetVisitor targetVisitor);
}
