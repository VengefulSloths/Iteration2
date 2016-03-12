package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.NonVisibleViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.VOVisitor;
import com.vengeful.sloths.AreaView.ViewObjects.ViewObject;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 3/2/2016.
 */
public class DecalViewObject extends ViewObject{

    private DynamicImage dynamicImage;

    public DecalViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String configPath) {
        super(r, s, coordinateStrategy, locationStrategy);
        this.dynamicImage = DynamicImageFactory.getInstance().loadDynamicImage(configPath);
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(dynamicImage.getImage(),
                getXPixels() + getLocationXOffset() + dynamicImage.getXOffset(),
                getYPixels() + getLocationYOffset() + dynamicImage.getYOffset(),
                this);
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        visibleImages.add(dynamicImage);
        return new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitDecal(this);
    }
}
