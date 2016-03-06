package com.vengeful.sloths.AreaView.ViewObjects.Hands;

import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
import com.vengeful.sloths.AreaView.ViewObjects.AttackViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.WeaponImageContainer;
import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Utility.Direction;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 3/6/2016.
 */
public class TwoHandState implements  HandState {
    private ArrayList<SmartHandViewObject> foreground = new ArrayList<>();
    private ArrayList<SmartHandViewObject> background = new ArrayList<>();

    private SmartHandViewObject leftHand;
    private SmartHandViewObject rightHand;

    private WeaponImageContainer weapon;

    private WalkingStrategy  walkingStrategy;

    private final int radius = 27;
    private final int heightLower = 30;
    private final int heightUpper = 60;

    private Direction direction;

    public TwoHandState(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, Direction direction) {
        this.walkingStrategy = new SimpleWalkingStrategy(6);

        //- radius
        leftHand = new SmartHandViewObject(r, s, coordinateStrategy, locationStrategy,
                resourcePath, radius, heightLower, -Math.PI/2, 0,  direction );

        rightHand = new SmartHandViewObject(r, s, coordinateStrategy, locationStrategy,
                resourcePath, radius, heightUpper, -Math.PI/2, 0, direction );


        changeDirection(direction);
    }

    @Override
    public void setLocation(int r, int s) {
        leftHand.setR(r);
        leftHand.setS(s);
        rightHand.setR(r);
        rightHand.setS(s);
    }

    @Override
    public void changeDirection(Direction d) {
        this.direction = d;
        leftHand.changeDirection(d);
        rightHand.changeDirection(d);

        this.foreground.clear();
        this.background.clear();

        switch (this.direction) {
            case N:
                background.add(leftHand);
                background.add(rightHand);
                break;
            case S:
                foreground.add(leftHand);
                foreground.add(rightHand);
                break;
            case SW:
                background.add(leftHand);
                background.add(rightHand);
                break;
            case SE:
                foreground.add(leftHand);
                foreground.add(rightHand);
                break;
            case NW:
                background.add(leftHand);
                background.add(rightHand);
                break;
            case NE:
                foreground.add(leftHand);
                foreground.add(rightHand);

                break;
        }

    }

    @Override
    public void addWeapon(WeaponImageContainer weapon) {
        this.weapon = weapon;
        rightHand.hold(weapon);
    }

    @Override
    public void attack(int r, int s, long windUpTime, long coolDownTime) {
        long t = ViewTime.getInstance().getCurrentTimeMilli();

        double omega = Math.PI/600;
        double alpha = 2d*(Math.PI/2 - omega*windUpTime)/Math.pow(windUpTime, 2);

        double vh = 0.3;
        double ah = 2d*(60 - vh*windUpTime)/Math.pow(windUpTime, 2);

        windUpAttack(leftHand, rightHand, alpha, omega, ah, vh, t, t + windUpTime);

        ViewTime.getInstance().registerAlert((windUpTime + coolDownTime)/2, () -> resetAttack(leftHand, rightHand));
        ViewTime.getInstance().registerAlert(windUpTime, () -> doAttack(leftHand, rightHand));

        AttackViewObject attack = TemporaryVOCreationVisitor.getInstance().createAttack(r, s, "resources/effects/bash/bash.xml", coolDownTime - windUpTime);
        ViewTime.getInstance().registerAlert(windUpTime, () ->attack.start());
    }

    private void windUpAttack(SmartHandViewObject handLower, SmartHandViewObject handUpper, double alpha, double omega, double ah, double vh, long startTime, long endTime) {
        long t = ViewTime.getInstance().getCurrentTimeMilli();

        if (direction ==  Direction.NE && t - startTime > (endTime - startTime)/2 ) {
            foreground.clear();
            background.clear();
            background.add(handLower);
            background.add(handUpper);
        } else if (direction == Direction.SW && t - startTime > (endTime - startTime)/2 ) {
            foreground.clear();
            background.clear();
            foreground.add(handLower);
            foreground.add(handUpper);
        }

        if (t <= endTime) {
            double angle = alpha/2*Math.pow(t-startTime, 2) + omega * (t - startTime);
            int h =  (int) (ah/2*Math.pow(t-startTime, 2) + vh * (t- startTime));
            handLower.setAngle(angle);
            handUpper.setAngle(angle);
            handLower.setHeight(h + heightLower);
            handUpper.setHeight(h + heightUpper);

            ViewTime.getInstance().registerAlert(0, () -> windUpAttack(handLower, handUpper, alpha, omega, ah, vh, startTime, endTime));

        } else {
            handLower.setAngle(Math.PI/2);
            handUpper.setAngle(Math.PI/2);
            handUpper.setHeight(60 + heightUpper);
            handLower.setHeight(60 + heightLower);
        }
    }

    private void doAttack(SmartHandViewObject handLower, SmartHandViewObject handUpper) {
        handUpper.drop();
        handUpper.setHeight(20);
        handLower.setHeight(20);
        handLower.setRadius(radius-10);
    }

    private void resetAttack(SmartHandViewObject handLower, SmartHandViewObject handUpper) {
        handLower.setAngle(0);
        handUpper.setAngle(0);

        handLower.setRadius(radius);
        handUpper.setRadius(radius);

        handLower.setHeight(heightLower);
        handUpper.setHeight(heightUpper);
        if (weapon != null) {
            rightHand.hold(weapon);
        }

        changeDirection(direction);
    }

    @Override
    public void paintFront(Graphics2D g) {
        for (SmartHandViewObject hand: foreground) {
            hand.paintComponent(g);
        }
    }

    @Override
    public void paintBack(Graphics2D g) {
        for (SmartHandViewObject hand: background) {
            hand.paintComponent(g);
        }
    }

    @Override
    public void alertMove(int r, int s, long duration) {
        leftHand.alertMove(r, s, duration);
        rightHand.alertMove(r, s, duration);
        walkingStrategy.startWalking(leftHand, duration);
        walkingStrategy.startWalking(rightHand, duration);
    }

}
