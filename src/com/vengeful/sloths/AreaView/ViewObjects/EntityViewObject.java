package com.vengeful.sloths.AreaView.ViewObjects;


import com.vengeful.sloths.AreaView.DynamicImages.*;
import com.vengeful.sloths.AreaView.Observers.EntityObserver;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Utility.Direction;

import java.awt.*;

/**
 * Created by alexs on 2/20/2016.
 */
public class EntityViewObject extends ViewObject implements EntityObserver {
    private DynamicImage walkingN;
    private DynamicImage walkingNE;
    private DynamicImage walkingNW;
    private DynamicImage walkingS;
    private DynamicImage walkingSE;
    private DynamicImage walkingSW;

    private int previousXPixel;
    private int previousYPixel;

    private long startTime = 0;
    private long duration = 0;
    private boolean isMoving = false;

    private DynamicImage currentDynamicImage;
    public EntityViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy);

        //Logic for how to load all the images for an entity
        //Load the walking images
        this.walkingS = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "smasherSouthMoving.xml");
        this.walkingSE = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "smasherSouthEastMoving.xml");
        this.walkingSW = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "smasherSouthWestMoving.xml");
        this.walkingN = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "smasherNorthMoving.xml");
        this.walkingNE = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "smasherNorthEastMoving.xml");
        this.walkingNW = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "smasherNorthWestMoving.xml");

        this.currentDynamicImage = walkingS;
    }

    @Override
    public int getXPixels() {
        if (this.isMoving) {
            float ratio = (float)(ViewTime.getInstance().getCurrentTimeMilli() - startTime)/duration;
            if (ratio >= 1) {
                this.isMoving = false;
                return super.getXPixels();
            }
            return (int)(ratio*super.getXPixels() + (1.0-ratio)*previousXPixel);
        } else {
            return super.getXPixels();
        }
    }

    @Override
    public int getYPixels() {
        if (this.isMoving) {
            float ratio = (float)(ViewTime.getInstance().getCurrentTimeMilli() - startTime)/duration;
            if (ratio >= 1) {
                return super.getYPixels();
            }
            return (int)(ratio*super.getYPixels() + (1-ratio)*previousYPixel);
        } else {
            return super.getYPixels();
        }
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(currentDynamicImage.getImage(), this.getXPixels() + currentDynamicImage.getXOffset(), this.getYPixels() + currentDynamicImage.getYOffset(), this);
    }

    @Override
    public void alertDirectionChange(Direction d) {
        switch (d) {
            case N:
                currentDynamicImage = walkingN;
                break;
            case NW:
                currentDynamicImage = walkingNW;
                break;
            case NE:
                currentDynamicImage = walkingNE;
                break;
            case S:
                currentDynamicImage = walkingS;
                break;
            case SW:
                currentDynamicImage = walkingSW;
                break;
            case SE:
                currentDynamicImage = walkingSE;
                break;

        }
    }

    @Override
    public void alertMove(int r, int s, long duration) {
        this.previousXPixel = getXPixels();
        this.previousYPixel = getYPixels();
        setR(r);
        setS(s);
        this.startTime = ViewTime.getInstance().getCurrentTimeMilli();
        this.duration = duration;
        this.isMoving = true;

        ((DynamicTimedImage) currentDynamicImage).start(duration);
    }
}
