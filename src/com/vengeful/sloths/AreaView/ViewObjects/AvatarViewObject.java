package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;

/**
 * Created by alexs on 2/23/2016.
 */
public class AvatarViewObject extends HominidViewObject{
    public AvatarViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy, resourcePath, "bald");
    }

}
