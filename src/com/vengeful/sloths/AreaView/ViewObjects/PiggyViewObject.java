package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.Observers.EntityObserver;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Utility.Direction;

import java.awt.*;

/**
 * Created by zach on 2/25/16.
 */
public class PiggyViewObject extends MovingViewObject implements EntityObserver {
    private DynamicImage walkingSW;
    private Direction direction;
    private DynamicImage currentDynamicImage;

    public PiggyViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy);
        this.walkingSW = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "piggy_southwest_standing.png");
        System.out.println("PIGYGYDFUSIFGJAIOSUFGHADOSIHADIOSFJADIOSFJADIOSFJAIOSDFJAIOSFJAOISDFJOAISDJFAIOSDFJASDOF");
        System.out.println(this.walkingSW);
        this.currentDynamicImage = this.walkingSW;
        this.direction = Direction.SW;
    }

    private void paintBody(Graphics2D g) {
        g.drawImage(currentDynamicImage.getImage(),
                this.getXPixels() + currentDynamicImage.getXOffset() + this.getLocationXOffset(),
                this.getYPixels() + currentDynamicImage.getYOffset() + this.getLocationYOffset(), this);

    }

    @Override
    public void paintComponent(Graphics2D g) {
        switch (this.direction) {
            case N:

                break;
            case S:

                break;
            case SW:
                paintBody(g);
                break;
            case SE:

                break;
            case NW:

                break;
            case NE:

                break;
        }
    }

    @Override
    public void accept(VOVisitor v) {

    }

    @Override
    public void alertDirectionChange(Direction d) {
        this.direction = d;
        switch (d) {
            case N:
//                currentDynamicImage = walkingN;
                break;
            case NW:
//                currentDynamicImage = walkingNW;
                break;
            case NE:
//                currentDynamicImage = walkingNE;
                break;
            case S:
//                currentDynamicImage = walkingS;
                break;
            case SW:
                currentDynamicImage = walkingSW;
                break;
            case SE:
//                currentDynamicImage = walkingSE;
                break;

        }

    }

    @Override
    public void movementHook(int r, int s, long duration) {
        ((DynamicTimedImage) currentDynamicImage).start(duration);

    }

}

