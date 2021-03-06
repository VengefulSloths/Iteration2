package com.vengeful.sloths.AreaView.DynamicImages;

import java.awt.*;

/**
 * Created by alexs on 2/20/2016.
 */
public abstract class DynamicImage {
    private PositioningStrategy positioningStrategy;
    private int height;
    private int width;

    public abstract Image getImage();

    public String getCurrentImagePath() {
        try {
            return doGetCurrentImagePath();
        } catch (NullPointerException e) {
            System.out.println("COULD NOT FIND DYNAMIC IMAGE");
            e.printStackTrace();
            return "resources/null/file_not_found.png";
        }
    }
    public abstract String doGetCurrentImagePath();
    //TODO pull positioningStrategy out of the config
    public DynamicImage(int width, int height, PositioningStrategy positioningStrategy) {
        this.positioningStrategy = positioningStrategy;
        this.height = height;
        this.width = width;
    }
    public int getHeight() {
        return this.height;
    }
    public int getWidth() {
        return this.width;
    }
    public int getXOffset() {
        return positioningStrategy.offsetXPixels(this);
    }
    public int getYOffset() {
        return positioningStrategy.offsetYPixels(this);
    }


}
