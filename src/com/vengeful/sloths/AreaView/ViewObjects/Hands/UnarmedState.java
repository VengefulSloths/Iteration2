package com.vengeful.sloths.AreaView.ViewObjects.Hands;

import com.vengeful.sloths.AreaView.PlainsPersistantViewObjectFactory;
import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
import com.vengeful.sloths.AreaView.ViewObjects.AttackViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
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

    private WalkingStrategy  walkingStrategy;

    private final int radius = 27;
    private final int height = 42;

    private int punchDistance = 25;

    private Direction direction;

    public UnarmedState(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy,
                        String resourcePath, Direction direction) {

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
        leftHand.hold(weapon);
    }

    @Override
    public void alertMove(int r, int s, long duration) {
        leftHand.alertMove(r, s, duration);
        rightHand.alertMove(r, s, duration);
        walkingStrategy.startWalking(leftHand, duration);
        walkingStrategy.startWalking(rightHand, duration);
    }

    @Override
    public void attack(int r, int s, long windUpTime, long coolDownTime) {
        double v = -0.1;
        double a = (2d*(punchDistance-v*windUpTime)/Math.pow(windUpTime, 2));
        System.out.println("Starting attack: " + a);
        long startTime = ViewTime.getInstance().getCurrentTimeMilli();
        positionHandForAttack(rightHand, a, v, startTime, startTime + windUpTime);


        AttackViewObject attack = TemporaryVOCreationVisitor.getInstance().createAttack(r, s, "resources/effects/punch/punch.xml", windUpTime);
        ViewTime.getInstance().registerAlert(windUpTime, () ->attack.start());

        ViewTime.getInstance().registerAlert(coolDownTime, ()->rightHand.setOffset(0));


    }

    private void positionHandForAttack(SmartHandViewObject hand, double a, double v, long startTime, long endTime) {
        long t = ViewTime.getInstance().getCurrentTimeMilli();
        if (t <= endTime) {
            hand.setOffset((int)(a/2*Math.pow(t- startTime, 2) + v*(t-startTime)));
            ViewTime.getInstance().registerAlert(0, () -> positionHandForAttack(hand, a, v, startTime, endTime));
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
}
