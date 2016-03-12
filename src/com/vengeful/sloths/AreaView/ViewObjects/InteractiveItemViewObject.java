package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Models.Observers.InteractiveItemObserver;
import com.vengeful.sloths.Sound.SoundEffect;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alex on 3/9/2016.
 */
public class InteractiveItemViewObject extends ViewObject implements InteractiveItemObserver{

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
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        if (isActive) {
            visibleImages.add(activeImage);
        } else {
            visibleImages.add(inactiveImage);
        }

        return new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitInteractiveItem(this);
    }

    @Override
    public void alertActivate() {
        this.isActive = true;
        (new SoundEffect("resources/audio/click.wav")).play();
    }

    @Override
    public void alertDeactivate() {
        this.isActive = false;
    }
}
