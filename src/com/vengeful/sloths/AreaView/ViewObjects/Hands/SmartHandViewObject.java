package com.vengeful.sloths.AreaView.ViewObjects.Hands;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.MovingViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.NonVisibleViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.VOVisitor;
import com.vengeful.sloths.AreaView.ViewObjects.WeaponImageContainer;
import com.vengeful.sloths.Utility.Direction;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 3/4/2016.
 */
public class SmartHandViewObject extends MovingViewObject{

    private final double CAMERA_TILT_FACTOR = Math.cos(Math.PI/4)*Math.sin(Math.toRadians(34));

    private WeaponImageContainer weapon = null;

    private int radius;
    private int height;
    private double angle0;
    private double angle;
    private double directional_angle;
    private int offset;

    private int xPixelOffset;
    private int yPixelOffset;

    private DynamicImage handImage;

    public SmartHandViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String handPath, int radius, int height, double angle0, int offset, Direction direction) {
        super(r, s, coordinateStrategy, locationStrategy);

        System.out.println(CAMERA_TILT_FACTOR);
        this.handImage = DynamicImageFactory.getInstance().loadDynamicImage(handPath + "hand.xml");

        this.radius = radius;
        this.height = height;
        this.angle0 = angle0;
        this.angle = 0;
        this.offset = offset;
        changeDirection(direction);
        calculatePixelOffsets();
    }

    public void changeDirection(Direction direction) {
        switch (direction) {
            case NE: directional_angle = Math.toRadians(34);
                break;
            case N: directional_angle = Math.toRadians(90);
                break;
            case NW: directional_angle = Math.toRadians(146);
                break;
            case SW: directional_angle = Math.toRadians(214);
                break;
            case S: directional_angle = Math.toRadians(270);
                break;
            case SE: directional_angle = Math.toRadians(326);
                break;
        }
        calculatePixelOffsets();

        if (this.weapon != null) {
            weapon.alertDirectionChange(direction);
        }
    }

    public void hold(WeaponImageContainer weapon) {
        this.weapon = weapon;
    }
    public void drop() {
        this.weapon = null;
    }

    public void calculatePixelOffsets() {
        xPixelOffset = (int)(radius*Math.cos(angle+angle0+directional_angle) + offset*Math.cos(angle+angle0+directional_angle + Math.PI/2));
        yPixelOffset = -(int)((radius*Math.sin(angle + angle0+directional_angle) + offset*Math.sin(angle+ angle0+directional_angle + Math.PI/2)+ height)*(CAMERA_TILT_FACTOR) );
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(handImage.getImage(),
                getXPixels() + xPixelOffset + handImage.getXOffset() + getLocationXOffset(),
                getYPixels() + yPixelOffset + handImage.getYOffset() + getLocationYOffset(),
                this);
        if (weapon != null) {
            g.drawImage(weapon.getCurrentDynamicImage().getImage(),
                    getXPixels() + xPixelOffset + weapon.getCurrentDynamicImage().getXOffset() + getLocationXOffset(),
                    getYPixels() + yPixelOffset + weapon.getCurrentDynamicImage().getYOffset() + getLocationYOffset(),
                    this);
        }
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        NonVisibleViewObject output = new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
        output.addFromVisibleImage(handImage, xPixelOffset, yPixelOffset);
        return output;
    }


    @Override
    public void accept(VOVisitor v) {
        System.out.println("you probably shouldn't be visiting hands");
    }

    @Override
    protected void movementHook(int r, int s, long duration) {
        super.movementHook(r, s, duration);
    }

    public void setOffset(int offset) {
        this.offset = offset;
        calculatePixelOffsets();
    }

    public void setAngle(double angle) {
        this.angle = angle;
        calculatePixelOffsets();
    }

    public void setHeight(int height) {
        this.height = height;
        calculatePixelOffsets();
    }

    public void setRadius(int radius) {
        this.radius = radius;
        calculatePixelOffsets();
    }
}
