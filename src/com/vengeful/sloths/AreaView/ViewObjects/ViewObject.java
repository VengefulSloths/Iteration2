package ViewObjects;

import ViewObjects.CoordinateStrategies.CoordinateStrategy;
import ViewObjects.LocationStrategies.LocationStrategy;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alexs on 2/20/2016.
 */
public abstract class ViewObject extends JComponent{
    private int xPixels;
    private int yPixels;

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

    public int getXPixels() {
        return locationStrategy.offsetXPixels(xPixels);
    }

    public int getYPixels() {
        return locationStrategy.offsetYPixels(yPixels);
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
        coordinateStrategy.adjustXY(this.s, this.r, this);
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
        coordinateStrategy.adjustXY(this.s, this.r, this);
    }

    public void setXPixels(int xPixels) {
        this.xPixels = xPixels;
    }

    public void setYPixels(int yPixels) {
        this.yPixels = yPixels;
    }

    public abstract void paintComponent(Graphics2D g);

}
