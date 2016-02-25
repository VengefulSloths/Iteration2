package com.vengeful.sloths.AreaView.ViewObjects;


import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;

import java.awt.*;

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
        g.drawImage(grassImage.getImage(), this.getXPixels() + grassImage.getXOffset(), this.getYPixels()+ grassImage.getYOffset(), this);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitMountain(this);
    }
}
