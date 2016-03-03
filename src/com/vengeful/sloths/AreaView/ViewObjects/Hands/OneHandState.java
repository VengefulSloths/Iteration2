package com.vengeful.sloths.AreaView.ViewObjects.Hands;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Utility.Direction;

import java.awt.*;

/**
 * Created by alexs on 3/3/2016.
 */
public class OneHandState implements HandState{

    private HandViewObject leftHand;
    private HandViewObject rightHand;

    private final int radius = 27;
    private final int height = 18;

    private Direction direction;

    public OneHandState(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy,
                        String resourcePath, Direction direction) {

        this.direction = direction;
        //- radius
        leftHand = new HandViewObject(r, s, coordinateStrategy, locationStrategy,
                resourcePath, -radius, height, direction );

        rightHand = new HandViewObject(r, s, coordinateStrategy, locationStrategy,
                resourcePath, radius, height, direction );
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
    }

    @Override
    public void alertMove(int r, int s, long duration) {
        leftHand.alertMove(r, s, duration);
        rightHand.alertMove(r, s, duration);
    }

    @Override
    public void paintFront(Graphics2D g) {
        switch (this.direction) {
            case N:
                break;
            case S:
                leftHand.paintComponent(g);
                rightHand.paintComponent(g);
                break;
            case SW:
                leftHand.paintComponent(g);
                break;
            case SE:
                rightHand.paintComponent(g);
                break;
            case NW:
                leftHand.paintComponent(g);
                break;
            case NE:
                rightHand.paintComponent(g);
                break;
        }
    }

    @Override
    public void paintBack(Graphics2D g) {
        switch (this.direction) {
            case N:
                leftHand.paintComponent(g);
                rightHand.paintComponent(g);
                break;
            case S:
                break;
            case SW:
                rightHand.paintComponent(g);
                break;
            case SE:
                leftHand.paintComponent(g);
                break;
            case NW:
                rightHand.paintComponent(g);
                break;
            case NE:
                leftHand.paintComponent(g);
                break;
        }
    }
}
