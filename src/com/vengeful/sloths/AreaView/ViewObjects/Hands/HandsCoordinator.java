package com.vengeful.sloths.AreaView.ViewObjects.Hands;


import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.NonVisibleViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.WeaponImageContainer;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.WeaponClass;

import java.awt.*;

/**
 * Created by alexs on 3/3/2016.
 */
public class HandsCoordinator {


    private HandState state;


    private CoordinateStrategy coordinateStrategy;
    private LocationStrategy locationStrategy;
    private String resourcePath;
    private Direction direction;

    private int r;
    private int s;

    public HandsCoordinator(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, Direction direction) {
        this.r = r;
        this.s = s;
        this.coordinateStrategy = coordinateStrategy;
        this.locationStrategy = locationStrategy;
        this.resourcePath = resourcePath;
        this.direction = direction;

        this.state = new UnarmedState(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);
    }

    public void equipWeapon(WeaponImageContainer weapon, WeaponClass weaponClass) {
        System.out.println("Equipping " + weapon.toString() + " with class " + weaponClass);
        switch (weaponClass) {
            case FISTS:
                this.state = new UnarmedState(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);
                break;
            case ONE_HAND:
                this.state = new OneHandState(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);
                break;
            case TWO_HAND:
                this.state = new TwoHandState(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);
                break;
            case BOW:
                System.out.println("BOWHANDSTATE!!!");
                System.out.println("rsource: " + resourcePath);
                this.state = new BowHandState(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);
                break;
            case THROW:
                this.state = new ThrowHandState(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);
                break;
        }
        state.addWeapon(weapon);
    }

    public void unequip() {
        this.state = new UnarmedState(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);
    }

    public void alertMove(int r, int s, long duration) {
        this.r = r;
        this.s = s;
        state.alertMove(r, s, duration);
    }

    public void attack(int r, int s, long windUpTime, long coolDownTime) {
        state.attack(r, s, windUpTime, coolDownTime);
    }

    public void cast(long windUpTime, long coolDownTime) {
        state.cast(windUpTime, coolDownTime);
    }

    public void setLocation(int r, int s) {
        state.setLocation(r, s);
    }

    public void changeDirection(Direction d) {
        this.direction = d;
        state.changeDirection(d);
    }

    public void setCustomYOffset(int offset) {
        state.setCustomYOffset(offset);
    }

    public void paintFront(Graphics2D g) {
        state.paintFront(g);
    }

    public void paintBack(Graphics2D g) {
        state.paintBack(g);
    }

    public NonVisibleViewObject getNonVisibleFront() { return state.getNonVisibleFront(); }

    public NonVisibleViewObject getNonVisibleBack() { return state.getNonVisibleBack(); }

    private boolean isFrozen = false;
    public void setFreeze(boolean isFrozen) {
        this.isFrozen = true;
    }

    public boolean isFrozen() {
        return isFrozen;
    }
}
