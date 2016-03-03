package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DestroyVOObserver;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.MovingVOObserver;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Sound.SoundEffect;
import com.vengeful.sloths.View.Observers.DestroyableObserver;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by luluding on 3/2/16.
 */
public class TakeableViewObject extends ViewObject implements DestroyableObserver {

    private DynamicImage itemImage;

    //Used to alert tileVO on destroy
    ArrayList<DestroyVOObserver> observers = new ArrayList<>();

    public TakeableViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy);
        itemImage = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath);
    }

    @Override
    public void alertDestroyed() {
        for(DestroyVOObserver dvoo : this.observers){
            dvoo.alertDestroyVO(this);
        }
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(itemImage.getImage(),
                getXPixels() + itemImage.getXOffset() + getLocationXOffset(),
                getYPixels() + itemImage.getYOffset() + getLocationYOffset(),
                this);
    }

    public void registerObserver(DestroyVOObserver observer) {
        observers.add(observer);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitTakeable(this);
    }


}
