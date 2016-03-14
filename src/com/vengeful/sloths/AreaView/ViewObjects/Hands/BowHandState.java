package com.vengeful.sloths.AreaView.ViewObjects.Hands;

import com.vengeful.sloths.AreaView.ViewObjects.BowImageContainter;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.NonVisibleViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.ViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.WeaponImageContainer;
import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Sound.SoundEffect;
import com.vengeful.sloths.Utility.Direction;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alex on 3/13/2016.
 */
public class BowHandState implements HandState {

    private ArrayList<SmartHandViewObject> foreground = new ArrayList<>();
    private ArrayList<SmartHandViewObject> background = new ArrayList<>();

    private SmartHandViewObject leftHand;
    private SmartHandViewObject rightHand;

    private WalkingStrategy  walkingStrategy = new SimpleWalkingStrategy(10);

    private final int radius = 27;
    private final int height = 42;

    private Direction direction;

    private int bowStringXOffset =0;
    private int bowStringYOffsetUp =0;
    private int bowStringYOffsetDown =0;

    public BowHandState(int r, int s, CoordinateStrategy cs, LocationStrategy ls, String resourcePath, Direction direction) {
        leftHand = new SmartHandViewObject(r, s, cs, ls, resourcePath, radius, height, Math.PI/2, 0,direction);
        rightHand = new SmartHandViewObject(r, s, cs, ls, resourcePath, radius, height + 30, -Math.PI/2, 0,direction);

        changeDirection(direction);
    }


    @Override
    public void paintFront(Graphics2D g) {
        for(SmartHandViewObject hand: foreground) {
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
    public NonVisibleViewObject getNonVisibleFront() {
        if (foreground.size() > 0) {
            NonVisibleViewObject nonVisibleViewObject = foreground.get(0).getNonVisibleSnapShot();
            if (foreground.size() > 1) {
                nonVisibleViewObject.addNonVisibleViewObject(foreground.get(1).getNonVisibleSnapShot());
            }
            return nonVisibleViewObject;
        } else {

            //This is essentially a joke
            return new NonVisibleViewObject(0, 0, new CoordinateStrategy() {
                @Override
                public void adjustXY(int r, int s, ViewObject subject) {

                }
            }, new LocationStrategy() {
                @Override
                public int offsetXPixels() {
                    return 0;
                }

                @Override
                public int offsetYPixels() {
                    return 0;
                }
            }, new ArrayList<>());
        }
    }

    @Override
    public NonVisibleViewObject getNonVisibleBack() {
        if (background.size() > 0) {
            NonVisibleViewObject nonVisibleViewObject = background.get(0).getNonVisibleSnapShot();
            if (background.size() > 1) {
                nonVisibleViewObject.addNonVisibleViewObject(background.get(1).getNonVisibleSnapShot());
            }
            return nonVisibleViewObject;
        } else {

            //This is essentially a joke
            return new NonVisibleViewObject(0, 0, new CoordinateStrategy() {
                @Override
                public void adjustXY(int r, int s, ViewObject subject) {

                }
            }, new LocationStrategy() {
                @Override
                public int offsetXPixels() {
                    return 0;
                }

                @Override
                public int offsetYPixels() {
                    return 0;
                }
            }, new ArrayList<>());
        }
    }

    @Override
    public void attack(int r, int s, long windUpTime, long coolDownTime) {

        cast(windUpTime, coolDownTime);
        ViewTime.getInstance().registerAlert(windUpTime, () -> {
            (new SoundEffect("resources/audio/bow2.wav")).play();
        });
    }

    @Override
    public void cast(long windUpTime, long coolDownTime) {

        reach(leftHand, rightHand, windUpTime/2);


        ViewTime.getInstance().registerAlert(coolDownTime, () -> {
            leftHand.setAngle(0);
            leftHand.setOffset(0);

            rightHand.setOffset(0);
            changeDirection(direction);
        });

    }

    private void reach(SmartHandViewObject reachingHand, SmartHandViewObject bowHand, long time) {
        double v = (double)bowStringXOffset/(double)time;
        double a = 2d*(3d*(double)bowStringXOffset - v/2d
                *(double)time)/Math.pow(time,2);
        double omega = -Math.PI/time;
        long startTime = ViewTime.getInstance().getCurrentTimeMilli();

        quadChangeOffset(reachingHand, bowStringXOffset, 0, v, startTime, time);
        linearChangeAngel(reachingHand, -Math.PI, omega, startTime, time);

        ViewTime.getInstance().registerAlert(time+50,() -> quadChangeOffset(reachingHand, 3*bowStringXOffset, a, v, startTime + time, time) );
        ViewTime.getInstance().registerAlert(time+50,() -> quadChangeOffset(bowHand, -bowStringXOffset, 0, -v, startTime + time, time) );
    }

    private void quadChangeOffset(SmartHandViewObject hand, int distance, double a, double v, long startTime, long duration) {
        long t = ViewTime.getInstance().getCurrentTimeMilli() - startTime;
        if (t < duration) {
            hand.setOffset((int)(a*Math.pow(t, 2)/2) + (int)(v*t));
            ViewTime.getInstance().registerAlert(0, () -> quadChangeOffset(hand, distance, a, v, startTime, duration));
        } else {
            hand.setOffset(distance);
        }
    }



    private void linearChangeAngel(SmartHandViewObject hand, double phi, double omega, long startTime, long duration) {
        long t = ViewTime.getInstance().getCurrentTimeMilli() - startTime;
        if (direction == Direction.SE) {
            background.clear();
            foreground.add(hand);
        } else if (direction == Direction.NW) {
            foreground.clear();
            background.add(hand);
        }

        if (t < duration) {
            hand.setAngle((int)(omega*t));
            ViewTime.getInstance().registerAlert(0, () -> linearChangeAngel(hand, phi, omega, startTime, duration));
        } else {
            if (direction == Direction.SW) {
                foreground.clear();
                background.add(hand);
            } else if (direction == Direction.NE) {
                background.clear();
                foreground.add(hand);
            } else if (direction == Direction.N) {
                background.add(hand);
            }
            hand.setAngle(phi);
        }
    }

    @Override
    public void addWeapon(WeaponImageContainer weapon) {
        rightHand.hold(weapon);
        try {
            BowImageContainter bow = (BowImageContainter) weapon;
            bowStringXOffset = bow.getX();
            bowStringYOffsetUp = bow.getyPlus();
            bowStringYOffsetDown = bow.getyMinus();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void setLocation(int r, int s) {
        leftHand.setR(r);
        rightHand.setR(r);
        leftHand.setS(s);
        rightHand.setS(s);
    }

    @Override
    public void changeDirection(Direction d) {
        this.direction = d;
        leftHand.changeDirection(d);
        rightHand.changeDirection(d);

        this.foreground.clear();
        this.background.clear();
        rightHand.setHeight(height + 30);
        leftHand.setHeight(height);
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
                rightHand.setHeight(height + 50);
                leftHand.setHeight(height + 20);
                break;
            case NW:
                foreground.add(leftHand);
                background.add(rightHand);
                break;
            case NE:
                foreground.add(rightHand);
                background.add(leftHand);
                rightHand.setHeight(height + 50);
                leftHand.setHeight(height + 20);
                break;
        }

    }

    @Override
    public void alertMove(int r, int s, long duration) {
        leftHand.alertMove(r, s, duration);
        rightHand.alertMove(r, s, duration);
        if (duration >= 15* TimeController.MODEL_TICK) {
            walkingStrategy.startWalking(leftHand, duration);
            walkingStrategy.startWalking(rightHand, duration);
        }
    }

    @Override
    public void setCustomYOffset(int offset) {
        leftHand.setCustomYOffset(offset);
        rightHand.setCustomYOffset(offset);
    }
}
