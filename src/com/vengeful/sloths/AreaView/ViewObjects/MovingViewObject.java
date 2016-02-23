package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.Observers.MovementObserver;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewTime;

/**
 * Created by Alex on 2/22/2016.
 */
public abstract class MovingViewObject extends ViewObject implements MovementObserver{
    private int previousXPixel;
    private int previousYPixel;
    private long startTime;
    private long duration;
    private boolean isMoving;
    public MovingViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy) {
        super(r, s, coordinateStrategy, locationStrategy);
    }

    @Override
    public int getXPixels() {
        if (this.isMoving) {
            float ratio = (float)(ViewTime.getInstance().getCurrentTimeMilli() - startTime)/duration;
            if (ratio >= 1) {
                this.isMoving = false;
                return super.getXPixels();
            }
            return (int)(ratio*super.getXPixels() + (1.0-ratio)*previousXPixel);
        } else {
            return super.getXPixels();
        }
    }

    @Override
    public int getYPixels() {
        if (this.isMoving) {
            float ratio = (float)(ViewTime.getInstance().getCurrentTimeMilli() - startTime)/duration;
            if (ratio >= 1) {
                return super.getYPixels();
            }
            return (int)(ratio*super.getYPixels() + (1-ratio)*previousYPixel);
        } else {
            return super.getYPixels();
        }
    }

    protected void movementHook(int r, int s, long duration) {
        //For subclasses to hook into alertMove for animations and whatnot
    }

    @Override
    final public void alertMove(int r, int s, long duration) {
        this.previousXPixel = getXPixels();
        this.previousYPixel = getYPixels();
        setR(r);
        setS(s);
        this.startTime = ViewTime.getInstance().getCurrentTimeMilli();
        this.duration = duration;
        this.isMoving = true;

        movementHook(r, s, duration);
    }
}
