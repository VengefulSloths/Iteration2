package com.vengeful.sloths.AreaView.ViewObjects;


import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 2/20/2016.
 */
public class MountainViewObject extends ViewObject {
    private DynamicImage grassImage;
    public MountainViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String xmlPath) {
        super(r, s, coordinateStrategy, locationStrategy);
        grassImage = DynamicImageFactory.getInstance().loadDynamicImage(xmlPath);
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(grassImage.getImage(),
                this.getXPixels() + grassImage.getXOffset() + this.getLocationXOffset(),
                this.getYPixels()+ grassImage.getYOffset() + this.getLocationYOffset(),
                this);
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        visibleImages.add(grassImage);
        return new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitMountain(this);
    }
}
