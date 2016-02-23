package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.AreaView.vAlertable;
import com.vengeful.sloths.Utility.Direction;

import java.awt.*;

/**
 * Created by Alex on 2/22/2016.
 */
public class HandViewObject extends MovingViewObject implements vAlertable {
    private DynamicImage handImage;

    private final double COS_34 = 0.829;
    private final double SIN_34 = 0.559;
    private final double COS_45 = 0.707;
    private final double SIN_34_x_COS_45 = SIN_34 * COS_45;

    private int height;
    private int radius;
    private int lrmag;

    private int xd;
    private int yd;

    private int xPixelOffset;
    private int yPixelOffset;

    private int handMovingRadius = 0;
    private int handMovingXOffset = 0;
    private int handMovingYOffset = 0;


    private int step = 0;
    private long duration = 0;

    private Direction direction;


    //Left hands have a positive radius, right hands have a negative radius
    public HandViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, int radius, int height, Direction dir) {
        super(r, s, coordinateStrategy, locationStrategy);
        this.handImage = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "hand.xml");
        this.radius = radius;
        this.height = height;

        this.handMovingRadius = (int)(SIN_34/2*Math.abs(radius)*radius/Math.abs(radius));

        changeDirection(dir);
    }

    public void changeDirection(Direction dir) {
        this.direction = dir;
        switch (dir) {
            case N:
                xd = radius;
                yd = -height;
                break;
            case S:
                xd = -radius;
                yd = -height;
                break;
            case NW:
                xd = (int)(COS_34 * radius);
                yd = -(int)(SIN_34 * radius + height);
                break;
            case NE:
                xd = (int)(COS_34 * radius);
                yd = -(int)(-SIN_34 * radius + height);
                break;
            case SW:
                xd = (int)(-COS_34 * radius);
                yd = -(int)(SIN_34 * radius + height);
                break;
            case SE:
                xd = (int)(-COS_34 * radius);
                yd = -(int)(-SIN_34 * radius + height);
                break;
        }
    }

    public void updateMovingHandOffsets(Direction dir) {
        switch (dir) {
            case N:
                handMovingXOffset = 0;
                handMovingYOffset = (int) (COS_45 * handMovingRadius);
                break;
            case S:
                handMovingXOffset = 0;
                handMovingYOffset = -((int)(COS_45 * handMovingRadius));
                break;
            case NW:
                handMovingXOffset = (int)(COS_34 * handMovingRadius);
                handMovingYOffset = (int)(SIN_34_x_COS_45 * handMovingRadius);
                break;
            case SE:
                handMovingXOffset = -(int)(COS_34 * handMovingRadius);
                handMovingYOffset = -(int)(SIN_34_x_COS_45 * handMovingRadius);
                break;
            case NE:
                handMovingXOffset = (int)(COS_34 * handMovingRadius);
                handMovingYOffset = -(int)(SIN_34_x_COS_45 * handMovingRadius);
            case SW:
                handMovingXOffset = -(int)(COS_34 * handMovingRadius);
                handMovingYOffset = (int)(SIN_34_x_COS_45 * handMovingRadius);
        }

    }

    @Override
    protected void movementHook(int r, int s, long duration) {
        this.duration = duration;
        updateMovingHandOffsets(this.direction);
        ViewTime.getInstance().registerAlert(this, duration/5);
    }

    @Override
    public void vAlert() {
        switch (step++) {
            case 0:
                xPixelOffset = handMovingXOffset;
                yPixelOffset = handMovingYOffset;
                ViewTime.getInstance().registerAlert(this, duration/5);
                break;
            case 1:
                xPixelOffset = 0;
                yPixelOffset = 0;
                ViewTime.getInstance().registerAlert(this, duration/5);
                break;
            case 2:
                xPixelOffset = -handMovingXOffset;
                yPixelOffset = -handMovingYOffset;
                ViewTime.getInstance().registerAlert(this, duration/5);
                break;
            case 3:
                xPixelOffset = 0;
                yPixelOffset = 0;
                step = 0;
                break;
        }
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(handImage.getImage(), getXPixels() + +xd +xPixelOffset + handImage.getXOffset(), getYPixels() + +yd +yPixelOffset + handImage.getYOffset(), this);
    }



}
