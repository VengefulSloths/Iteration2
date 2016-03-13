package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.Hands.HandsCoordinator;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Sound.SoundEffect;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.WeaponClass;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by zach on 3/9/16.
 */
public class HominidViewObject extends EntityViewObject {


    private HandsCoordinator hands;
    private HatViewObject hat;

    public HominidViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath) {
        super(r, s, coordinateStrategy, locationStrategy, resourcePath);

        this.hat = new HatViewObject(r, s, coordinateStrategy, locationStrategy, direction);
        this.hands = new HandsCoordinator(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);
    }

    public HominidViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, String defaultHatName) {
        super(r, s, coordinateStrategy, locationStrategy, resourcePath);

        if (this.hands == null)
            this.hands = new HandsCoordinator(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);

        if (this.hat == null)
            this.hat = new HatViewObject(r, s, coordinateStrategy, locationStrategy, direction);

        this.hat.setDefaultHatPath("resources/equipment/" + defaultHatName + "/");
    }


    @Override
    protected void paintBack(Graphics2D g) {
        hands.paintBack(g);
    }

    @Override
    protected void paintFront(Graphics2D g) {
        hat.paintComponent(g);
        hands.paintFront(g);
    }

//    @Override
//    public void paintComponent(Graphics2D g) {
//        if (isMounted() && !dead) {
//            super.paintComponent(g);
//            hat.paintComponent(g);
//        } else {
//            if (isStealthed()) {
//                getNonVisibleSnapShot().paintComponent(g);
//            } else {
//                if (!dead) {
//                    hands.paintBack(g);
//                }
//
//                super.paintComponent(g);
//
//                if (!dead) {
//                    hat.paintComponent(g);
//                    hands.paintFront(g);
//                }
//            }
//        }
//    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {

        NonVisibleViewObject output = new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), new ArrayList<>());

        if (isMounted() && !dead) {
            output.addNonVisibleViewObject(super.getNonVisibleSnapShot());
            output.addNonVisibleViewObject(hat.getNonVisibleSnapShot());
        } else {
            if (!dead) {
                output.addNonVisibleViewObject(hands.getNonVisibleBack());
            }
            output.addNonVisibleViewObject(super.getNonVisibleSnapShot());
            if (!dead) {
                output.addNonVisibleViewObject(hat.getNonVisibleSnapShot());
                output.addNonVisibleViewObject(hands.getNonVisibleFront());
            }
        }
        return output;
    }

    @Override
    public void setCustomYOffset(int offset) {
        super.setCustomYOffset(offset);
        this.hat.setCustomYOffset(offset);
        this.hands.setCustomYOffset(offset);
        this.getHealthBar().setCustomYOffset(offset);
    }


    @Override
    public void alertDirectionChange(Direction d) {
        super.alertDirectionChange(d);

        hands.changeDirection(d);
        hat.changeDirection(d);
    }

    @Override
    public void setLocation(int r, int s) {
        super.setLocation(r, s);
        hands.setLocation(r, s);
        hat.setLocation(r, s);
    }

    @Override
    public void movementHook(int r, int s, long duration) {

        super.movementHook(r, s, duration);

        hands.alertMove(r, s, duration);


        hat.alertMove(r, s, duration);


    }

    @Override
    public void alertAttack(int r, int s, long windUpTime, long coolDownTime) {
        hands.attack(r, s, windUpTime, coolDownTime);
    }

    @Override
    public void alertEquipWeapon(String name, WeaponClass weaponClass) {
        WeaponImageContainer weapon = new WeaponImageContainer("resources/weapons/" + name + "/", direction);
        hands.equipWeapon(weapon, weaponClass);
    }



    @Override
    public void alertUnequipWeapon() {
        hands.unequip();
    }

    @Override
    public void alertEquipHat(String name) {
        this.hat.equip("resources/equipment/" + name + "/");
    }

    @Override
    public void alertUnequipHat() {
        this.hat.unequip();
    }

    @Override
    public void alertCast(long windUpTime, long coolDownTime) {
        hands.cast(windUpTime, coolDownTime);
    }
}
