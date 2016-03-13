package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Utility.Tuple;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alex on 3/11/2016.
 */
public class NonVisibleViewObject extends ViewObject {
    private ArrayList<Tuple<Image, Integer, Integer>> images = new ArrayList<>();

    private boolean overridePixels;
    private int overriddenX = 0;
    private int overriddenY = 0;

    public NonVisibleViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, ArrayList<DynamicImage> dynamicImages) {
        super(r, s, coordinateStrategy, locationStrategy);
        for (DynamicImage dynamicImage : dynamicImages) {
            if (dynamicImage == null) {
                images.add(new Tuple<>(getNonVisibleImage(dynamicImage),
                        -26,
                        -130));
            } else {
                images.add(new Tuple<>(getNonVisibleImage(dynamicImage),
                        dynamicImage.getXOffset(),
                        dynamicImage.getYOffset()));
            }
        }
        overridePixels = false;
    }

    public NonVisibleViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, ArrayList<DynamicImage> dynamicImages, int xPixels, int yPixels) {
        this(r, s, coordinateStrategy, locationStrategy, dynamicImages);
        this.overridePixels = true;
        this.overriddenX = xPixels;
        this.overriddenY = yPixels;
    }

    @Override
    public void paintComponent(Graphics2D g) {
        for (int i = 0; i < images.size(); i++) {
            g.drawImage(images.get(i).x,
                    images.get(i).y + getLocationXOffset() + (overridePixels ? overriddenX : getXPixels()),
                    images.get(i).z + getLocationYOffset() + (overridePixels ? overriddenY : getYPixels()),
                    this);
        }
    }


    @Override
    public void accept(VOVisitor v) {

    }

    public boolean isOverridingPixels() {
        return overridePixels;
    }

    public int getOverriddenX() {
        return overriddenX;
    }

    public int getOverriddenY() {
        return overriddenY;
    }

    public ArrayList<Tuple<Image, Integer, Integer>> getImages() {
        return images;
    }

    public void addFromVisibleImage(DynamicImage visibleImage, int xOffset, int yOffset) {
        images.add(new Tuple<>(getNonVisibleImage(visibleImage),
                visibleImage.getXOffset() + xOffset,
                visibleImage.getYOffset() + yOffset));
    }


    public void addNonVisibleViewObject(NonVisibleViewObject other) {
        if (other.isOverridingPixels()) {
            this.overridePixels = true;
            this.overriddenX = other.getOverriddenX();
            this.overriddenY = other.getOverriddenY();
        }
        images.addAll(other.getImages());
    }

    private Image getNonVisibleImage(DynamicImage visibleImage) {
        if (visibleImage == null) return (new ImageIcon("resources/dark_resources/null/file_not_found.png")).getImage();

        String nonVisiblePath = "resources/dark_" + visibleImage.getCurrentImagePath();
        return (new ImageIcon(nonVisiblePath)).getImage();

    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        return this;
    }

}
