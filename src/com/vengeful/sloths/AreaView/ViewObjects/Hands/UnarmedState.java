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

    private HandViewObject leftHand;
    private HandViewObject rightHand;

    private ArrayList<HandViewObject> foreground = new ArrayList<>();
    private ArrayList<HandViewObject> background = new ArrayList<>();

    private final int radius = 27;
    private final int height = 18;

    private Direction direction;

    public UnarmedState(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy,
                        String resourcePath, Direction direction) {

        //- radius
        leftHand = new HandViewObject(r, s, coordinateStrategy, locationStrategy,
                resourcePath, -radius, height, direction );

        rightHand = new HandViewObject(r, s, coordinateStrategy, locationStrategy,
                resourcePath, radius, height, direction );

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
    }

    @Override
    public void attack(int r, int s, long duration) {
        rightHand.forward(5, duration/2);
        leftHand.in(5, duration/2);
        ViewTime.getInstance().registerAlert(duration*8/9, () -> {
            rightHand.reset();
            leftHand.reset();
        });

        AttackViewObject attack = TemporaryVOCreationVisitor.getInstance().createAttack(r, s, "resources/effects/punch/punch.xml", duration);
        ViewTime.getInstance().registerAlert(duration*3/7, () ->attack.start());


    }

    @Override
    public void paintFront(Graphics2D g) {
        for (HandViewObject hand: foreground) {
            hand.paintComponent(g);
        }
    }

    @Override
    public void paintBack(Graphics2D g) {
        for (HandViewObject hand: background) {
            hand.paintComponent(g);
        }
    }
}
