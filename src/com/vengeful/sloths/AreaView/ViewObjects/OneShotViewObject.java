package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Sound.SoundEffect;
import com.vengeful.sloths.Models.Observers.DestroyableObserver;

import java.awt.*;

/**
 * Created by alexs on 2/29/2016.
 */
public class OneShotViewObject extends ViewObject implements DestroyableObserver{
    private DynamicTimedImage itemImage;
    private String breakSoundPath;

    public OneShotViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, String breakSoundPath) {
        super(r, s, coordinateStrategy, locationStrategy);
        itemImage = (DynamicTimedImage) DynamicImageFactory.getInstance().loadDynamicImage(resourcePath);
        this.breakSoundPath = breakSoundPath;
    }

    @Override
    public void alertDestroyed() {
        itemImage.start(1000);
        (new SoundEffect(breakSoundPath)).play();
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
        v.visitOneShot(this);
    }
}
