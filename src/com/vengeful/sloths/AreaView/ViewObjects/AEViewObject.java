package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;

import java.awt.*;

/**
 * Created by Alex on 3/10/2016.
 */
public class AEViewObject extends ViewObject{

    private DynamicImage image;

    public AEViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy);
        this.image = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath);
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(image.getImage(),
                getXPixels() + getLocationXOffset() + image.getXOffset(),
                getYPixels() + getLocationYOffset() + image.getYOffset(),
                this);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitAE(this);
    }
}
