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
public class EntityViewObject extends MovingViewObject implements EntityObserver {
    private DynamicImage walkingN;
    private DynamicImage walkingNE;
    private DynamicImage walkingNW;
    private DynamicImage walkingS;
    private DynamicImage walkingSE;
    private DynamicImage walkingSW;

    private HandViewObject leftHand;
    private HandViewObject rightHand;

    private Direction direction;

    private DynamicImage currentDynamicImage;
    public EntityViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy);

        //Logic for how to load all the images for an entity
        //Load the walking images
        this.walkingS = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "south_moving.xml");
        this.walkingSE = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "south_east_moving.xml");
        this.walkingSW = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "south_west_moving.xml");
        this.walkingN = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "north_moving.xml");
        this.walkingNE = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "north_east_moving.xml");
        this.walkingNW = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "north_west_moving.xml");
        this.currentDynamicImage = walkingS;
        this.direction = Direction.S;

        this.leftHand = new HandViewObject(r, s, coordinateStrategy, locationStrategy, resourcePath, 27, 18, Direction.S);
        this.rightHand = new HandViewObject(r, s, coordinateStrategy, locationStrategy, resourcePath, -27, 18, Direction.S);

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
                leftHand.paintComponent(g);
                rightHand.paintComponent(g);
                this.paintBody(g);
                break;
            case S:
                this.paintBody(g);
                leftHand.paintComponent(g);
                rightHand.paintComponent(g);
                break;
            case SW:
                leftHand.paintComponent(g);
                paintBody(g);
                rightHand.paintComponent(g);
                break;
            case SE:
                rightHand.paintComponent(g);
                paintBody(g);
                leftHand.paintComponent(g);
                break;
            case NW:
                leftHand.paintComponent(g);
                paintBody(g);
                rightHand.paintComponent(g);
                break;
            case NE:
                rightHand.paintComponent(g);
                paintBody(g);
                leftHand.paintComponent(g);
                break;
        }
    }

    @Override
    public void alertDirectionChange(Direction d) {
        this.direction = d;
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
        leftHand.changeDirection(d);
        rightHand.changeDirection(d);
    }

    @Override
    public void movementHook(int r, int s, long duration) {
        ((DynamicTimedImage) currentDynamicImage).start(duration);
        leftHand.alertMove(r, s, duration);
        rightHand.alertMove(r, s, duration);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitEntity(this);
    }
}
