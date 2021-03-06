package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.TileViewObject;
import com.vengeful.sloths.AreaView.Visibility;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alex on 3/8/2016.
 */
public class NullTile extends TileViewObject {
    public NullTile(int r, int s, CoordinateStrategy cs, LocationStrategy ls){
        super(r, s, cs, ls);
        super.setVisibility(Visibility.VISIBLE);
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        return new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
    }

    @Override
    public void setVisibility(Visibility visibility) {
        //do nothing
    }
}
