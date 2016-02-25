package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.MovingViewObject;

/**
 * Created by Alex on 2/25/2016.
 */
public class AvatarViewFollower implements MovingVOObserver, vAlertable {
    private static AvatarViewFollower ourInstance = new AvatarViewFollower();
    private long movingEndTime= 0;
    private int xPixels;
    private int yPixels;

    private MovingViewObject target;

    public static AvatarViewFollower getInstance() {
        return ourInstance;
    }

    private AvatarViewFollower() {
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
        ViewTime.getInstance().registerAlert(this, 0);
    }

    @Override
    public void vAlert() {
        this.xPixels = target.getXPixels();
        this.yPixels = target.getYPixels();
        if (ViewTime.getInstance().getCurrentTimeMilli() < this.movingEndTime) {
            ViewTime.getInstance().registerAlert(this, 0);
        }
    }

    public int getXPixels() {
        return xPixels;
    }

    public int getYPixels() {
        return yPixels;
    }
}
