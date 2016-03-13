package com.vengeful.sloths.AreaView.ViewObjects;


import com.vengeful.sloths.AreaView.DynamicImages.*;
import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.Hands.HandsCoordinator;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.ObserverManager;
import com.vengeful.sloths.Sound.SoundEffect;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.WeaponClass;
import com.vengeful.sloths.Models.Observers.EntityObserver;

import java.awt.*;
import java.util.ArrayList;

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

    private DynamicImage mountImage;
    private boolean isMounted = false;

    private boolean isStealthed = false;

    private HealthBarViewObject healthBar;

    protected Direction direction;

    private DynamicImage currentDynamicImage;

    protected boolean dead = false;

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

        this.healthBar = new HealthBarViewObject(r,s,coordinateStrategy,locationStrategy);
    }

    public EntityViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, String defaultHatName) {
        this(r, s, coordinateStrategy, locationStrategy, resourcePath);
    }

    private void paintBody(Graphics2D g) {
        g.drawImage(currentDynamicImage.getImage(),
                this.getXPixels() + currentDynamicImage.getXOffset() + this.getLocationXOffset(),
                this.getYPixels() + currentDynamicImage.getYOffset() + this.getLocationYOffset(), this);

    }

    @Override
    public void paintComponent(Graphics2D g) {
        if (!dead) {
            if (isStealthed) {
                getNonVisibleSnapShot().paintComponent(g);
            } else {
                if (isMounted) {
                    g.drawImage(mountImage.getImage(),
                            getXPixels() + getLocationXOffset() + mountImage.getXOffset(),
                            getYPixels() + getLocationYOffset() + mountImage.getYOffset(),
                            this);
                }
                if(healthBar != null) {
                    healthBar.paintComponent(g);
                }
                paintBody(g);
            }
        }
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();

        if (!dead) {
            if (isMounted()) {
                visibleImages.add(mountImage);
            }
            visibleImages.add(currentDynamicImage);
        }
        NonVisibleViewObject output = new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages, getXPixels(), getYPixels());
        return output;
    }

    public boolean isMounted() {
        return isMounted;
    }
    public boolean isStealthed() { return isStealthed;
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
    }

    public void setLocation(int r, int s) {

        if(healthBar != null) {
            healthBar.setR(r);
            healthBar.setS(s);
        }


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
        if(!isMounted) {
            ((DynamicTimedImage) currentDynamicImage).start(duration);
            (new SoundEffect("resources/audio/grass_step.wav")).play();
        }
        if(healthBar != null) {
            healthBar.alertMove(r, s, duration);
        }

    }

    @Override
    public void alertAddBuff(String buffName) {
        healthBar.addBuff(buffName);
    }

    @Override
    public void alertRemoveBuff(String buffName) {
        healthBar.removeBuff(buffName);
    }

    @Override
    public void alertAttack(int r, int s, long windUpTime, long coolDownTime) {
        AttackViewObject attack = TemporaryVOCreationVisitor.getInstance().createAttack(r, s, "resources/effects/punch/punch.xml", windUpTime);
        ViewTime.getInstance().registerAlert(windUpTime, () ->attack.start());
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitEntity(this);
    }


    @Override
    public void alertCast(long windUpTime, long coolDownTime) {
        System.out.println("EntityVO recieved cast");
        //hands.cast(windUpTime, coolDownTime);
    }

    @Override
    public void alertEquipWeapon(String name, WeaponClass weaponClass) {
        //default do nothing
    }

    @Override
    public void alertUnequipWeapon() {
    }

    @Override
    public void alertEquipHat(String name) {
    }

    @Override
    public void alertUnequipHat() {
    }

    @Override
    public void alertLevelUp() {

    }

    @Override
    public final void alertMount(String mountName) {
        System.out.println("just mounted");
        this.setCustomYOffset(-20);
        mountImage = DynamicImageFactory.getInstance().loadDynamicImage("resources/mount/" + mountName + ".xml");
        this.isMounted = true;
        ViewTime.getInstance().registerAlert(0, () -> mountAnimation());
    }

    private int[] offsets = {0,1,1,2,2,3,3,2,1,1,0,0,-1,-1,-2,-2,-3,-3,-2,-2,-1,-1,0};
    private int count = 0;

    private void mountAnimation() {
        if (isMounted()) {
            //System.out.println("Setting");
            setCustomYOffset(-20 + offsets[count++%offsets.length]);
            ViewTime.getInstance().registerAlert(0, () -> mountAnimation());
        }
    }

    @Override
    public void alertDemount() {
        this.setCustomYOffset(0);
        this.isMounted = false;
    }

    @Override
    public void alertDeath() {
        System.out.println("this is getting called, to delte the view object");
        ObserverManager.getInstance().deregister(this);
        ObserverManager.getInstance().deregister(this.getHealthBar());

        this.dead = true;

//        this.currentDynamicImage = null;
//        AttackViewObject attack = TemporaryVOCreationVisitor.getInstance().createAttack(this.getR(), this.getS(), "resources/effects/slash/slash.xml", 60);
//        ViewTime.getInstance().registerAlert(0, () ->attack.start());


    }

    @Override
    public void alertStealth() {
        System.out.println("setting stealth to true");
        this.isStealthed = true;
    }

    @Override
    public void alertUnstealth() {
        System.out.println("setting stealth to false");
        this.isStealthed = false;
    }

    @Override
    public void alertTakeDamage(int damage) {
        //System.out.println("takingdamage");
        TemporaryVOCreationVisitor.getInstance().createDamageNumber(getR(), getS(), damage);
    }

    public HealthBarViewObject getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(HealthBarViewObject healthBar) {
        this.healthBar = healthBar;
    }
}
