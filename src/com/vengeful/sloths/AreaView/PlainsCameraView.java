package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.CameraView;
import com.vengeful.sloths.AreaView.PlainsPersistantViewObjectFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.SimpleHexCoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.CenterAvatarLocationStrategy;

/**
 * Created by alexs on 2/23/2016.
 */
public class PlainsCameraView extends CameraView {
    public PlainsCameraView() {
        super(new PlainsPersistantViewObjectFactory(new SimpleHexCoordinateStrategy(), new CenterAvatarLocationStrategy()));
    }
}
