package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Utility.Direction;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 3/3/2016.
 */
public class HatViewObject extends MovingViewObject{
    private DynamicImage hatN;
    private DynamicImage hatNE;
    private DynamicImage hatNW;
    private DynamicImage hatS;
    private DynamicImage hatSE;
    private DynamicImage hatSW;

    private DynamicImage currentHat;

    private String defaultHatPath;

    private Direction direction;

    public HatViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, Direction d) {
        this(r, s, coordinateStrategy, locationStrategy, "resources/equipment/no_hat/", d);
    }

    public HatViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, Direction d) {
        super(r, s, coordinateStrategy, locationStrategy);

        this.defaultHatPath = resourcePath;
        this.direction = d;
        equip(resourcePath);
        changeDirection(d);

    }

    public void setDefaultHatPath(String hatPath) {
        System.out.println(hatPath);
        this.defaultHatPath = hatPath;
        equip(defaultHatPath);
    }

    public void unequip() {
        this.equip(defaultHatPath);
    }

    public void equip(String resourcePath) {
        this.hatN = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "hat_n.xml");
        this.hatNE = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "hat_ne.xml");
        this.hatNW = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "hat_nw.xml");
        this.hatS = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "hat_s.xml");
        this.hatSE = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "hat_se.xml");
        this.hatSW = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "hat_sw.xml");

        this.changeDirection(direction);
    }

    public void changeDirection(Direction d) {
        switch (d) {
            case N:
                this.currentHat = hatN;
                break;
            case NE:
                this.currentHat = hatNE;
                break;
            case NW:
                this.currentHat = hatNW;
                break;
            case S:
                this.currentHat = hatS;
                break;
            case SE:
                this.currentHat = hatSE;
                break;
            case SW:
                this.currentHat = hatSW;
                break;
        }
        this.direction = d;
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(currentHat.getImage(),
                this.getXPixels() + currentHat.getXOffset() + this.getLocationXOffset(),
                this.getYPixels() + currentHat.getYOffset() + this.getLocationYOffset(), this);
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        visibleImages.add(currentHat);
        return new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
    }

    @Override
    public void accept(VOVisitor v) {
        System.out.println("You just visited a hat");
    }
}
