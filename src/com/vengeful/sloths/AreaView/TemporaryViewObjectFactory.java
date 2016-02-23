package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.AvatarViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.EntityViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;

/**
 * Created by alexs on 2/23/2016.
 */
public class TemporaryViewObjectFactory {
    private CoordinateStrategy cs;
    private LocationStrategy ls;

    public TemporaryViewObjectFactory(CoordinateStrategy cs, LocationStrategy ls) {
        this.cs = cs;
        this.ls = ls;
    }
    private void setCs(CoordinateStrategy cs) {
        this.cs = cs;
    }
    private void setLs(LocationStrategy ls) {
        this.ls = ls;
    }


    public AvatarViewObject createAvatarViewObject(int r, int s) {
        return new AvatarViewObject(r, s, cs, ls, "resources/entities/smasher/");
    }
}
