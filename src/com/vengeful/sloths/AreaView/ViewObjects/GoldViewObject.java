package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DestroyVOObserver;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Models.Observers.DestroyableObserver;
import com.vengeful.sloths.Sound.SoundEffect;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by harrison on 3/12/16.
 */
public class GoldViewObject extends ViewObject implements DestroyableObserver {
    private DynamicImage itemImage;
    ArrayList<DestroyVOObserver> observers = new ArrayList<>();

    public GoldViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy);
        itemImage = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath);
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
        v.acceptGold(this);
    }

    @Override
    public void alertDestroyed() {
        (new SoundEffect("resources/audio/pickup.wav")).play();
        for(DestroyVOObserver dvoo : this.observers){
            dvoo.alertDestroyVO(this);
        }
    }

    public void registerObserver(DestroyVOObserver observer) {
        observers.add(observer);
    }
}
