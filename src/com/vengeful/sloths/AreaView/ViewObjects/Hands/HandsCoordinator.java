package com.vengeful.sloths.AreaView.ViewObjects.Hands;


import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Utility.Direction;

import java.awt.*;

/**
 * Created by alexs on 3/3/2016.
 */
public class HandsCoordinator {


    private HandState state;

    private int r;
    private int s;
    private CoordinateStrategy coordinateStrategy;
    private LocationStrategy locationStrategy;
    private String resourcePath;
    private Direction direction;

    public HandsCoordinator(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, Direction direction) {
        this.r = r;
        this.s = s;
        this.coordinateStrategy = coordinateStrategy;
        this.locationStrategy = locationStrategy;
        this.resourcePath = resourcePath;
        this.direction = direction;

        this.state = new UnarmedState(r, s, coordinateStrategy, locationStrategy, resourcePath, direction);
    }

    public void alertMove(int r, int s, long duration) {
        state.alertMove(r, s, duration);
    }

    public void attack(int r, int s, long duration) {
        state.attack(r, s, duration);
    }

    public void setLocation(int r, int s) {
        state.setLocation(r, s);
    }

    public void changeDirection(Direction d) {
        state.changeDirection(d);
    }

    public void paintFront(Graphics2D g) {
        state.paintFront(g);
    }

    public void paintBack(Graphics2D g) {
        state.paintBack(g);
    }



}
