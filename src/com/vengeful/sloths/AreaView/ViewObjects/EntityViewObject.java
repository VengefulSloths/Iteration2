package com.vengeful.sloths.AreaView.ViewObjects;


import com.vengeful.sloths.AreaView.DynamicImages.CenteredPositioningStrategy;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.SingleFrameImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;

import java.awt.*;

/**
 * Created by alexs on 2/20/2016.
 */
public class EntityViewObject extends ViewObject{
    private DynamicImage walkingN;
    private DynamicImage walkingNE;
    private DynamicImage walkingNW;
    private DynamicImage walkingS;
    private DynamicImage walkingSE;
    private DynamicImage walkingSW;

    private DynamicImage currentDynamicImage;
    public EntityViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy) {
        super(r, s, coordinateStrategy, locationStrategy);

        //Logic for how to load all the images for an entity
        //Load the walking images
        this.walkingN = new SingleFrameImage("resources/entities/smasher/smasher_north_standing.png", new CenteredPositioningStrategy());
        this.currentDynamicImage = walkingN;
    }
    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(currentDynamicImage.getImage(), this.getXPixels() + currentDynamicImage.getXOffset(), this.getYPixels() + currentDynamicImage.getYOffset(), this);
    }
}
