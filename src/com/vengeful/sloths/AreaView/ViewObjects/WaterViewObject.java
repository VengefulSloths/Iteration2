package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 2/27/2016.
 */
public class WaterViewObject extends ViewObject{
    private DynamicImage waterImage;
    public WaterViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String xmlPath) {
        super(r, s, coordinateStrategy, locationStrategy);
        this.waterImage = DynamicImageFactory.getInstance().loadDynamicImage(xmlPath);
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(waterImage.getImage(), getXPixels() + waterImage.getXOffset() + getLocationXOffset(), getYPixels() + waterImage.getYOffset() + getLocationYOffset(), this);
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        visibleImages.add(waterImage);
        return new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitWater(this);
    }
}
