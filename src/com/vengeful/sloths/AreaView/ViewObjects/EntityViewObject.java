package com.vengeful.sloths.AreaView.ViewObjects;


import com.vengeful.sloths.AreaView.DynamicImages.*;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.Hands.HandViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.Hands.HandsCoordinator;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Sound.SoundEffect;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.View.Observers.EntityObserver;

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
    private HealthBarViewObject healthBar;

    private HandsCoordinator hands;

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

        hands = new HandsCoordinator(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);

        this.healthBar = new HealthBarViewObject(r,s,coordinateStrategy,locationStrategy);

        //TODO: delete this testing code
        //this.rightHand.hold(new WeaponImageContainer("resources/weapons/dagger/", Direction.S));
    }

    private void paintBody(Graphics2D g) {
        g.drawImage(currentDynamicImage.getImage(),
                this.getXPixels() + currentDynamicImage.getXOffset() + this.getLocationXOffset(),
                this.getYPixels() + currentDynamicImage.getYOffset() + this.getLocationYOffset(), this);

    }

    @Override
    public void paintComponent(Graphics2D g) {
        healthBar.paintComponent(g);
        hands.paintBack(g);
        paintBody(g);
        hands.paintFront(g);
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
        hands.changeDirection(d);
    }

    public void setLocation(int r, int s) {
        System.out.println("bleeeeeeeep");
        leftHand.setR(r);
        leftHand.setS(s);

        rightHand.setR(r);
        rightHand.setS(s);

        healthBar.setR(r);
        healthBar.setS(s);

        hands.setLocation(r, s);
        healthBar.setR(r);
        healthBar.setS(s);

        setR(r);
        setS(s);
    }


    //TODO: do we need this?
//    @Override
//    public void setIsMoving(boolean isMoving) {
//        super.setIsMoving(isMoving);
//        leftHand.setIsMoving(isMoving);
//        rightHand.setIsMoving(isMoving);
//        ((DynamicTimedImage) currentDynamicImage).end();
//    }


    @Override
    public void movementHook(int r, int s, long duration) {
        ((DynamicTimedImage) currentDynamicImage).start(duration);

        //leftHand.alertMove(r, s, duration);
        //rightHand.alertMove(r, s, duration);
        healthBar.alertMove(r,s,duration);

        hands.alertMove(r, s, duration);
        System.out.println("entity move hook activated");
        (new SoundEffect("resources/audio/grass_step.wav")).play();

    }

    @Override
    public void accept(VOVisitor v) {
        v.visitEntity(this);
    }


    @Override
    public void alertDrop(int x, int y, MapItem itemToDrop) {

    }

    @Override
    public void alertEquipWeapon(String name) {

    }

    @Override
    public void alertEquipHat(String name) {

    }

    @Override
    public void alertLevelUp() {

    }

    @Override
    public void alertDeath() {

    }

    public HealthBarViewObject getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(HealthBarViewObject healthBar) {
        this.healthBar = healthBar;
    }
}
