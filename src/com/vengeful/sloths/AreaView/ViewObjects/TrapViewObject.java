package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Models.Observers.DestroyableObserver;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by luluding on 3/12/16.
 */
public class TrapViewObject extends ViewObject implements DestroyableObserver{

    private DynamicImage myImage;


    public TrapViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy);
        myImage = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath);
    }


    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(myImage.getImage(),
                getXPixels() + myImage.getXOffset() + getLocationXOffset(),
                getYPixels() + myImage.getYOffset() + getLocationYOffset(),
                this);
    }

    @Override
    public void alertDestroyed() {
        ((DynamicTimedImage) myImage).start(20);
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        visibleImages.add(myImage);
        return new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitTrapViewObject(this);
    }
}
