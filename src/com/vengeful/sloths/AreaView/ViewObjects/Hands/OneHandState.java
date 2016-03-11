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
 * Created by alexs on 3/4/2016.
 */
public class OneHandState implements HandState {
    private ArrayList<SmartHandViewObject> foreground = new ArrayList<>();
    private ArrayList<SmartHandViewObject> background = new ArrayList<>();

    private SmartHandViewObject leftHand;
    private SmartHandViewObject rightHand;


    private WalkingStrategy  walkingStrategy;

    private final int radius = 27;
    private final int height = 42;

    private Direction direction;

    private double sweepAngle = Math.PI*5/6;
    private int radiusChange = 10;

    public OneHandState(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, Direction direction) {
        this.walkingStrategy = new SimpleWalkingStrategy(10);

        //- radius
        leftHand = new SmartHandViewObject(r, s, coordinateStrategy, locationStrategy,
                resourcePath, radius, height, Math.PI/2, 0,  direction );

        rightHand = new SmartHandViewObject(r, s, coordinateStrategy, locationStrategy,
                resourcePath, radius, height, -Math.PI/2, 0, direction );


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
                foreground.add(leftHand);
                background.add(rightHand);
                break;
            case SE:
                foreground.add(rightHand);
                background.add(leftHand);
                break;
            case NW:
                foreground.add(leftHand);
                background.add(rightHand);
                break;
            case NE:
                foreground.add(rightHand);
                background.add(leftHand);
                break;
        }

    }

    @Override
    public void addWeapon(WeaponImageContainer weapon) {
        rightHand.hold(weapon);
    }

    @Override
    public void attack(int r, int s, long windUpTime, long coolDownTime) {
        cast(windUpTime, coolDownTime);

        AttackViewObject attack = TemporaryVOCreationVisitor.getInstance().createAttack(r, s, "resources/effects/slash/slash.xml", windUpTime);
        ViewTime.getInstance().registerAlert(0, () ->attack.start());
    }


    public void cast(long windUpTime, long coolDownTime) {
        long startTime = ViewTime.getInstance().getCurrentTimeMilli();
        {
            double omega = Math.PI / 200;
            double alpha = (2d * (sweepAngle - omega * windUpTime) / Math.pow(windUpTime, 2));
            double vr = (double)radiusChange / windUpTime;

            positionHandForAttack(rightHand, sweepAngle, radius, alpha, omega, vr, startTime, startTime + windUpTime);
        }
        {
            double omega = sweepAngle/(coolDownTime-windUpTime)*2;
            double vr = (double)radiusChange/(coolDownTime - windUpTime)*2;

            ViewTime.getInstance().registerAlert(windUpTime, () ->withdrawHandFromAttack(rightHand, sweepAngle, radius - radiusChange, omega, vr, startTime + windUpTime, (coolDownTime + windUpTime)/2 + startTime));
        }
    }

    private void positionHandForAttack(SmartHandViewObject hand, double sweepAngle, int radius, double alpha, double omega, double vr, long startTime, long endTime) {
        long t = ViewTime.getInstance().getCurrentTimeMilli();

        if (direction == Direction.SW && t > (startTime - endTime)/3 + startTime) {
            foreground.add(rightHand);
            background.clear();
        } else if (direction == Direction.NE && t > (startTime - endTime)/3 + startTime) {
            foreground.clear();
            background.add(rightHand);
        }
        if (t <= endTime) {
            hand.setRadius((int)(radius-vr*(t-startTime)));
            hand.setAngle((alpha/2*Math.pow(t- startTime, 2) + omega*(t-startTime)));
            ViewTime.getInstance().registerAlert(0, () -> positionHandForAttack(hand, sweepAngle,radius, alpha, omega, vr, startTime, endTime));
        } else {
            hand.setAngle(sweepAngle);
            hand.setRadius(radius);
        }
    }

    private void withdrawHandFromAttack(SmartHandViewObject hand, double startAngle, int startRadius, double omega, double vr, long startTime, long endTime) {
        long t = ViewTime.getInstance().getCurrentTimeMilli();

//        if (direction == Direction.NE && t > (startTime - endTime)*5/6 + startTime) {
//            changeDirection(Direction.NE);
//        } else if (direction == Direction.SW && t > (startTime - endTime)*5/6 + startTime) {
//            changeDirection(Direction.SW);
//        }

        if (t <= endTime) {
            hand.setRadius((int)(startRadius+vr*(t-startTime)));
            hand.setAngle((startAngle - omega*(t-startTime)));
            ViewTime.getInstance().registerAlert(0, () -> withdrawHandFromAttack(hand, startAngle, startRadius, omega, vr, startTime, endTime));
        } else {
            hand.setAngle(0);
            hand.setRadius(radius);
            if (direction == Direction.NE) {
                changeDirection(Direction.NE);
            } else if (direction == Direction.SW) {
                changeDirection(Direction.SW);
            }

        }
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

    @Override
    public void setCustomYOffset(int offset) {
        leftHand.setCustomYOffset(offset);
        rightHand.setCustomYOffset(offset);
    }

}
