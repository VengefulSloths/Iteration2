package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;

import java.awt.*;

/**
 * Created by Alex on 3/9/2016.
 */
public class InteractiveItemViewObject extends ViewObject {

    private DynamicImage inactiveImage;
    private DynamicImage activeImage;

    private boolean isActive = false;

    public InteractiveItemViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String inactivePath, String activePath) {
        super(r, s, coordinateStrategy, locationStrategy);
        inactiveImage = DynamicImageFactory.getInstance().loadDynamicImage(inactivePath);
        activeImage = DynamicImageFactory.getInstance().loadDynamicImage(activePath);
    }

    @Override
    public void paintComponent(Graphics2D g) {
        if (isActive) {
            g.drawImage(activeImage.getImage(),
                    getXPixels() + getLocationXOffset() + activeImage.getXOffset(),
                    getYPixels() + getLocationYOffset() + activeImage.getYOffset(),
                    this);
        } else {
            g.drawImage(inactiveImage.getImage(),
                    getXPixels() + getLocationXOffset() + inactiveImage.getXOffset(),
                    getYPixels() + getLocationYOffset() + inactiveImage.getYOffset(),
                    this);
        }
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitInteractiveItem(this);
    }
}
