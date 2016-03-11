package com.vengeful.sloths.AreaView.ViewObjects;


import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alexs on 2/20/2016.
 */
public abstract class ViewObject extends JComponent {
    private int xPixels;
    private int yPixels;

    private int customYOffset = 0;

    private int r;
    private int s;

    private CoordinateStrategy coordinateStrategy;
    private LocationStrategy locationStrategy;

    public ViewObject(int r, int  s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy) {
        this.r = r;
        this.s = s;
        this.coordinateStrategy = coordinateStrategy;
        this.locationStrategy = locationStrategy;
        this.coordinateStrategy.adjustXY(this.r, this.s, this);
    }

    public int getLocationXOffset() {
        return this.locationStrategy.offsetXPixels();
    }

    public int getLocationYOffset() {
        return this.locationStrategy.offsetYPixels();
    }

    public int getXPixels() {
        return xPixels;
    }

    public int getYPixels() {
        return yPixels + customYOffset;
    }

    public void setCustomYOffset(int offset) {
        this.customYOffset = offset;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
        coordinateStrategy.adjustXY(this.r, this.s, this);
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
        coordinateStrategy.adjustXY(this.r, this.s, this);
    }

    public void setXPixels(int xPixels) {
        this.xPixels = xPixels;
    }

    public void setYPixels(int yPixels) {
        this.yPixels = yPixels;
    }

    public abstract void paintComponent(Graphics2D g);

    public abstract void accept(VOVisitor v);

}
