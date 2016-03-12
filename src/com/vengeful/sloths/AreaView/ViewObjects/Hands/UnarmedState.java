package com.vengeful.sloths.AreaView.ViewObjects.Hands;

import com.vengeful.sloths.AreaView.PlainsPersistantViewObjectFactory;
import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
import com.vengeful.sloths.AreaView.ViewObjects.AttackViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.NonVisibleViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.ViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.WeaponImageContainer;
import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.WeaponClass;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 3/3/2016.
 */
public class UnarmedState implements HandState{

    //private HandViewObject leftHand;
    //private HandViewObject rightHand;

    private ArrayList<SmartHandViewObject> foreground = new ArrayList<>();
    private ArrayList<SmartHandViewObject> background = new ArrayList<>();

    private SmartHandViewObject leftHand;
    private SmartHandViewObject rightHand;

    private SmartHandViewObject attackingHand;




    private int punchDistance = 30;

    private WalkingStrategy  walkingStrategy;

    private final int radius = 27;
    private final int height = 42;

    private Direction direction;

    public UnarmedState(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy,
                        String resourcePath, Direction direction) {

        this.walkingStrategy = new SimpleWalkingStrategy(10);

        //- radius
        leftHand = new SmartHandViewObject(r, s, coordinateStrategy, locationStrategy,
                resourcePath, radius, height, Math.PI/2, 0,  direction );

        rightHand = new SmartHandViewObject(r, s, coordinateStrategy, locationStrategy,
                resourcePath, radius, height, -Math.PI/2, 0, direction );

        attackingHand = rightHand;

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
        leftHand.hold(weapon);
    }

    @Override
    public void alertMove(int r, int s, long duration) {
        leftHand.alertMove(r, s, duration);
        rightHand.alertMove(r, s, duration);
        walkingStrategy.startWalking(leftHand, duration);
        walkingStrategy.startWalking(rightHand, duration);
    }

    private boolean isLeftHand = false;
    @Override
    public void attack(int r, int s, long windUpTime, long coolDownTime) {
        cast(windUpTime, coolDownTime);

        AttackViewObject attack = TemporaryVOCreationVisitor.getInstance().createAttack(r, s, "resources/effects/punch/punch.xml", windUpTime);
        ViewTime.getInstance().registerAlert(windUpTime, () ->attack.start());


    }

    @Override
    public void cast(long windUpTime, long coolDownTime) {
        double v = -0.15;
        double a = (2d*(punchDistance-v*windUpTime)/Math.pow(windUpTime, 2));
        long startTime = ViewTime.getInstance().getCurrentTimeMilli();
        long t = ViewTime.getInstance().getCurrentTimeMilli();


        if (isLeftHand) {
            v = -v;
            a = -a;
            positionHandForAttack(leftHand, -punchDistance, a, v, startTime, startTime + windUpTime);
            ViewTime.getInstance().registerAlert((windUpTime+coolDownTime)/2, () ->retractHand(leftHand, -punchDistance, (windUpTime+coolDownTime)/2 + t, coolDownTime + t));


        }
        else {
            positionHandForAttack(rightHand, punchDistance, a, v, startTime, startTime + windUpTime);
            ViewTime.getInstance().registerAlert((windUpTime+coolDownTime)/2, () ->retractHand(rightHand, punchDistance, (windUpTime+coolDownTime)/2 + t, coolDownTime + t));
        }
        if (attackingHand == rightHand) {
            attackingHand = leftHand;
        } else {
            attackingHand = rightHand;
        }
        isLeftHand = !isLeftHand;
    }


    private void positionHandForAttack(SmartHandViewObject hand, int punchDistance, double a, double v, long startTime, long endTime) {
        long t = ViewTime.getInstance().getCurrentTimeMilli();

        if (t <= endTime) {
            hand.setOffset((int)(a/2*Math.pow(t- startTime, 2) + v*(t-startTime)));
            ViewTime.getInstance().registerAlert(0, () -> positionHandForAttack(hand, punchDistance, a, v, startTime, endTime));
        } else {
            hand.setOffset(punchDistance);
        }
    }
    private void retractHand(SmartHandViewObject hand, int punchDistance, long startTime, long endTime) {
        long duration = endTime - startTime;

        long t = ViewTime.getInstance().getCurrentTimeMilli();

        if (t <= endTime) {
            hand.setOffset((int) (punchDistance * (double)(endTime - t) / duration));
            ViewTime.getInstance().registerAlert(0, () ->retractHand(hand, punchDistance, startTime, endTime));
        } else {
            hand.setOffset(0);
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
    public void setCustomYOffset(int offset) {
        leftHand.setCustomYOffset(offset);
        rightHand.setCustomYOffset(offset);
    }
}
