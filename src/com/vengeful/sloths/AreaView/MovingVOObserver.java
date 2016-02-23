package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.MovingViewObject;

/**
 * Created by alexs on 2/23/2016.
 */
public interface MovingVOObserver {
    void alertMove(int srcR, int srcS, int destR, int destS, MovingViewObject subject);
}
