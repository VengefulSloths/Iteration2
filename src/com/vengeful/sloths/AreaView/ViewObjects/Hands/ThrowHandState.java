package com.vengeful.sloths.AreaView.ViewObjects.Hands;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by zach on 3/13/16.
 */
public class ThrowHandState extends OneHandState {

    public ThrowHandState(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, Direction direction) {
        super(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);
    }

    @Override
    public void attack(int r, int s, long windUpTime, long coolDownTime) {
        cast(windUpTime, coolDownTime);
    }
}
