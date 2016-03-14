package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DestroyVOObserver;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Models.Observers.HitBoxObserver;
import com.vengeful.sloths.Sound.SoundEffect;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by luluding on 3/10/16.
 */
public class ImmovableHitBoxViewObject extends ViewObject implements HitBoxObserver{
    private DynamicImage imageOnCreation;
    private DynamicImage imageOnDestroy;

    private DynamicImage itemImage;


    public ImmovableHitBoxViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy);
        imageOnCreation = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath+"_start.xml");
        imageOnDestroy = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath+"_destroy.xml");
        itemImage = imageOnCreation;
        ((DynamicTimedImage) itemImage).start(1000);
    }


    @Override
    public void alertDestroyed() {
        //itemImage = imageOnDestroy;
        //((DynamicTimedImage) itemImage).start(5000);
        //TODO: restore
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(itemImage.getImage(),
                getXPixels() + itemImage.getXOffset() + getLocationXOffset(),
                getYPixels() + itemImage.getYOffset() + getLocationYOffset(),
                this);
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        visibleImages.add(itemImage);
        return new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitImmovableHitBoxViewObject(this);
    }


    @Override
    public void alertMove(int r, int s, long duration) {
        //not used
    }
}
