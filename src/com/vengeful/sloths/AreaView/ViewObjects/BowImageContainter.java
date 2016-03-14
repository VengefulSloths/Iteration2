package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.Utility.Direction;

/**
 * Created by Alex on 3/13/2016.
 */
public class BowImageContainter extends WeaponImageContainer {

    private int yPlus;
    private int yMinus;
    private int x;

    public int getyPlus() {
        return yPlus;
    }

    public void setyPlus(int yPlus) {
        this.yPlus = yPlus;
    }

    public int getyMinus() {
        return yMinus;
    }

    public void setyMinus(int yMinus) {
        this.yMinus = yMinus;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public BowImageContainter(String resourcePath, Direction dir, int yPlus, int yMinus, int x) {

        super(resourcePath, dir);
        this.yPlus = yPlus;
        this.yMinus = yMinus;
        this.x = x;
    }
}
