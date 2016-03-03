package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.MovingViewObject;
import com.vengeful.sloths.Utility.CartesionDirection;

/**
 * Created by Alex on 2/25/2016.
 */
public class AvatarViewFollower implements MovingVOObserver, vAlertable {
    private static final double sqrt2over2 = Math.sqrt(2)/2;

    private static AvatarViewFollower ourInstance = new AvatarViewFollower();
    private long movingEndTime= 0;
    private int xPixels;
    private int yPixels;

    private int xOffset = 0;
    private int yOffset = 0;

    private double snapBackFactor = 1;

    private MovingViewObject target;

    public static AvatarViewFollower getInstance() {
        return ourInstance;
    }

    private AvatarViewFollower() {
    }

    int freeMoveSpeed = 15;


    public void move(CartesionDirection.Direction direction) {
        switch (direction) {
            case N: this.yOffset -= freeMoveSpeed;
                break;
            case S: this.yOffset += freeMoveSpeed;
                break;
            case E: this.xOffset += freeMoveSpeed;
                break;
            case W: this.xOffset -= freeMoveSpeed;
                break;
            case NE: this.yOffset -= sqrt2over2*freeMoveSpeed;
                this.xOffset += sqrt2over2*freeMoveSpeed;
                break;
            case SE: this.yOffset += sqrt2over2*freeMoveSpeed;
                this.xOffset += sqrt2over2*freeMoveSpeed;
                break;
            case NW: this.yOffset -= sqrt2over2*freeMoveSpeed;
                this.xOffset -= sqrt2over2*freeMoveSpeed;
                break;
            case SW: this.yOffset += sqrt2over2*freeMoveSpeed;
                this.xOffset -= sqrt2over2*freeMoveSpeed;
                break;
        }
        this.xOffset *= snapBackFactor;
        this.yOffset *= snapBackFactor;

    }

    public void bindToViewObject(MovingViewObject mvo) {
        mvo.registerObserver(this);
        this.target = mvo;
        this.xPixels = target.getXPixels();
        this.yPixels = target.getYPixels();
    }

    @Override
    public void alertMove(int srcR, int srcS, int destR, int destS, long duration, MovingViewObject subject) {
        this.movingEndTime = ViewTime.getInstance().getCurrentTimeMilli() + duration;
        this.snapBackFactor = 0.9;
        this.freeMoveSpeed = 0;
        ViewTime.getInstance().registerAlert(this, 0);
    }

    @Override
    public void vAlert() {
        this.xPixels = target.getXPixels();
        this.yPixels = target.getYPixels();
        if (ViewTime.getInstance().getCurrentTimeMilli() < this.movingEndTime) {
            ViewTime.getInstance().registerAlert(this, 0);
        } else {
            this.snapBackFactor = 1;
            this.freeMoveSpeed = 15;
        }
    }

    public int getXPixels() {
        return xPixels + xOffset;
    }

    public int getYPixels() {
        return yPixels + yOffset;
    }
}
