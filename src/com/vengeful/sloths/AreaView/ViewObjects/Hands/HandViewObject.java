package com.vengeful.sloths.AreaView.ViewObjects.Hands;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.MovingViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.VOVisitor;
import com.vengeful.sloths.AreaView.ViewObjects.WeaponImageContainer;
import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.AreaView.vAlertable;
import com.vengeful.sloths.Utility.Direction;

import java.awt.*;

/**
 * Created by Alex on 2/22/2016.
 */
public class HandViewObject extends MovingViewObject implements vAlertable {
    private DynamicImage handImage;

    private WeaponImageContainer weapon = null;


    //Constant trig functions pre-calculated to ease the processor
    private static double COS_34 = 0.829;
    private static double SIN_34 = 0.559;
    private static final double COS_45 = 0.707;
    private static final double SIN_34_x_COS_45 = SIN_34 * COS_45;

    //Height is how far above the ground the hand rests
    private int height;

    //Radius is how far from the body center the hand extends
    //Positive radii are for left hands
    private int radius;

    //the offset that comes from the direction the avatar is facing
    private int xd;
    private int yd;

    //the offset that comes from the avatar moving
    private int xPixelOffset;
    private int yPixelOffset;

    //how far the hand will move (absolute top down cartesian distance) when walking
    private final int handMovingRadius;

    //The adjusted (non-cartesian) distance the hand will move when walking
    private int handMovingXOffset = 0;
    private int handMovingYOffset = 0;

    //Where the avatar is in the 5 step walking motion
    private int step = 0;

    //How long a walking motion will take
    private long duration = 0;

    private Direction direction;


    //Left hands have a positive radius, right hands have a negative radius
    public HandViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, int radius, int height, Direction dir) {
        super(r, s, coordinateStrategy, locationStrategy);
        this.handImage = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "hand.xml");
        this.radius = radius;
        this.height = height;

        //This is signOf(radius)*portionOf(radius)
        this.handMovingRadius = (int)(SIN_34/2*Math.abs(radius)*radius/Math.abs(radius));

        changeDirection(dir);
    }

    //Change all the direction offsets when direction changes
    public void changeDirection(Direction dir) {
        this.direction = dir;
        switch (dir) {
            case N:
                xd = radius;
                yd = -height;

                handMovingXOffset = 0;
                handMovingYOffset = (int) (COS_45 * handMovingRadius);
                break;
            case S:
                xd = -radius;
                yd = -height;

                handMovingXOffset = 0;
                handMovingYOffset = -((int)(COS_45 * handMovingRadius));
                break;
            case NW:
                xd = (int)(COS_34 * radius);
                yd = -(int)(SIN_34 * radius + height);

                handMovingXOffset = (int)(COS_34 * handMovingRadius);
                handMovingYOffset = (int)(SIN_34_x_COS_45 * handMovingRadius);
                break;
            case NE:
                xd = (int)(COS_34 * radius);
                yd = -(int)(-SIN_34 * radius + height);

                handMovingXOffset = -(int)(COS_34 * handMovingRadius);
                handMovingYOffset = (int)(SIN_34_x_COS_45 * handMovingRadius);
                break;
            case SW:
                xd = (int)(-COS_34 * radius);
                yd = -(int)(SIN_34 * radius + height);

                handMovingXOffset = (int)(COS_34 * handMovingRadius);
                handMovingYOffset = -(int)(SIN_34_x_COS_45 * handMovingRadius);
                break;
            case SE:
                xd = (int)(-COS_34 * radius);
                yd = -(int)(-SIN_34 * radius + height);

                handMovingXOffset = -(int)(COS_34 * handMovingRadius);
                handMovingYOffset = -(int)(SIN_34_x_COS_45 * handMovingRadius);
                break;
        }

        if (weapon != null) {
            weapon.alertDirectionChange(dir);
        }
    }

    public void hold(WeaponImageContainer weapon) {
        this.weapon = weapon;
    }

    public void drop() {
        this.weapon = null;
    }

    @Override
    protected void movementHook(int r, int s, long duration) {
        this.duration = duration;
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
        if(weapon != null) {
            g.drawImage(weapon.getWeaponBack().getImage(),
                    getXPixels() + +xd +xPixelOffset + handImage.getXOffset() + weapon.getWeaponBack().getXOffset() + this.getLocationXOffset(),
                    getYPixels() + +yd +yPixelOffset + handImage.getYOffset() + weapon.getWeaponBack().getYOffset() + this.getLocationYOffset(),
                    this);
        }
        g.drawImage(handImage.getImage(),
                getXPixels() + +xd +xPixelOffset + handImage.getXOffset() + this.getLocationXOffset(),
                getYPixels() + +yd +yPixelOffset + handImage.getYOffset() + this.getLocationYOffset(),
                this);
        if(weapon != null) {
            g.drawImage(weapon.getWeaponFront().getImage(),
                    getXPixels() + +xd +xPixelOffset + handImage.getXOffset() + weapon.getWeaponFront().getXOffset() + this.getLocationXOffset(),
                    getYPixels() + +yd +yPixelOffset + handImage.getYOffset() + weapon.getWeaponFront().getYOffset() + this.getLocationYOffset(),
                    this);
        }
    }

    @Override
    public void accept(VOVisitor v) {
        //TODO: put something here
    }

}
