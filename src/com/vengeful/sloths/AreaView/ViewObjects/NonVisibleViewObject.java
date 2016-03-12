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

    public NonVisibleViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, ArrayList<DynamicImage> dynamicImages) {
        super(r, s, coordinateStrategy, locationStrategy);
        for (DynamicImage dynamicImage : dynamicImages) {
            images.add(new Tuple<>(getNonVisibleImage(dynamicImage),
                    dynamicImage.getXOffset(),
                    dynamicImage.getYOffset()));
        }
    }

    @Override
    public void paintComponent(Graphics2D g) {
        for (int i = 0; i < images.size(); i++) {
            g.drawImage(images.get(i).x,
                    images.get(i).y + getLocationXOffset() + getXPixels(),
                    images.get(i).z + getLocationYOffset() + getYPixels(),
                    this);
        }
    }

    @Override
    public void accept(VOVisitor v) {

    }

    public ArrayList<Tuple<Image, Integer, Integer>> getImages() {
        return images;
    }

    public void addFromVisibleImage(DynamicImage visibleImage) {
        images.add(new Tuple<>(getNonVisibleImage(visibleImage),
                visibleImage.getXOffset(),
                visibleImage.getYOffset()));
    }

    public void addNonVisibleViewObject(NonVisibleViewObject other) {
        images.addAll(other.getImages());
    }

    private Image getNonVisibleImage(DynamicImage visibleImage) {
        String nonVisiblePath = "resources/dark_" + visibleImage.getCurrentImagePath();
        return (new ImageIcon(nonVisiblePath)).getImage();
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        return this;
    }

}
