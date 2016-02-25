package com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies;




/**
 * Created by alexs on 2/20/2016.
 */
public class CenterAvatarLocationStrategy implements LocationStrategy {
    public CenterAvatarLocationStrategy() {
        System.out.println("do not delete, someone created a new location strategy");
        Exception e = new Exception();
        e.printStackTrace();
    }
    @Override
    public int offsetXPixels(int xPixels) {
        return xPixels;
    }

    @Override
    public int offsetYPixels(int yPixels) {
        return yPixels;
    }
}
