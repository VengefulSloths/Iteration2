package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DestroyVOObserver;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Models.Observers.DestroyableObserver;
import com.vengeful.sloths.Models.ViewObservable;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by luluding on 3/12/16.
 */
public class TrapViewObject extends ViewObject implements DestroyableObserver{

    private DynamicImage itemImage;


    public TrapViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy);
        itemImage = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath);
        System.out.println("CREATED!!!" + resourcePath);
    }


    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(itemImage.getImage(),
                getXPixels() + itemImage.getXOffset() + getLocationXOffset(),
                getYPixels() + itemImage.getYOffset() + getLocationYOffset(),
                this);
    }

    @Override
    public void alertDestroyed() {
        //TODO:
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        visibleImages.add(itemImage);
        return new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitTrapViewObject(this);
    }
}
