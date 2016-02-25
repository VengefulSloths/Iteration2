package com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies;


import com.vengeful.sloths.AreaView.AvatarViewFollower;

/**
 * Created by alexs on 2/20/2016.
 */
public class CenterAvatarLocationStrategy implements LocationStrategy {
    private AvatarViewFollower target;
    public CenterAvatarLocationStrategy() {
        System.out.println("do not delete, someone created a new location strategy");
        this.target = AvatarViewFollower.getInstance();
    }
    @Override
    public int offsetXPixels() {
        return -target.getXPixels()+300;
    }

    @Override
    public int offsetYPixels() {
        return -target.getYPixels()+300;
    }
}
