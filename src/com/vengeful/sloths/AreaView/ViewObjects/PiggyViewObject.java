package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Models.Observers.EntityObserver;

import java.awt.*;

/**
 * Created by zach on 2/25/16.
 */
public class PiggyViewObject extends EntityViewObject implements EntityObserver {
    private DynamicImage walkingN;
    private DynamicImage walkingNE;
    private DynamicImage walkingNW;
    private DynamicImage walkingS;
    private DynamicImage walkingSW;
    private DynamicImage walkingSE;
    private Direction direction;
    private DynamicImage currentDynamicImage;

    public PiggyViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy, resourcePath);
        this.walkingN = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "north_moving.xml");
        this.walkingNE = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "north_east_moving.xml");
        this.walkingNW = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "north_west_moving.xml");
        this.walkingS = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "south_moving.xml");
        this.walkingSE = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "south_east_moving.xml");
        this.walkingSW = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "south_west_moving.xml");
        this.direction = Direction.S;
        this.currentDynamicImage = walkingS;
        this.setHealthBar(null);
    }

    private void paintBody(Graphics2D g) {
        g.drawImage(currentDynamicImage.getImage(),
                this.getXPixels() + currentDynamicImage.getXOffset() + this.getLocationXOffset(),
                this.getYPixels() + currentDynamicImage.getYOffset() + this.getLocationYOffset(), this);

    }

    @Override
    public void movementHook(int r, int s, long duration) {
        ((DynamicTimedImage) currentDynamicImage).start(duration);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitPiggy(this);
    }
}

