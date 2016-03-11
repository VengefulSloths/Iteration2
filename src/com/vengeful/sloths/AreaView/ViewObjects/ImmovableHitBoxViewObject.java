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

    private DynamicImage itemImage;


    public ImmovableHitBoxViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy);
        itemImage = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath);

    }


    @Override
    public void alertDestroyed() {
        ((DynamicTimedImage) itemImage).start(20);
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(itemImage.getImage(),
                getXPixels() + itemImage.getXOffset() + getLocationXOffset(),
                getYPixels() + itemImage.getYOffset() + getLocationYOffset(),
                this);
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
