package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DestroyVOObserver;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.Models.Observers.HitBoxObserver;
import com.vengeful.sloths.Utility.Direction;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by luluding on 3/10/16.
 */
public class MovableHitBoxViewObject extends MovingViewObject implements HitBoxObserver {


    //TODO: may need to separate into moving HB and blast HB

    private DynamicImage movingN;
    private DynamicImage movingNE;
    private DynamicImage movingNW;
    private DynamicImage movingS;
    private DynamicImage movingSW;
    private DynamicImage movingSE;
    private Direction direction;
    private DynamicImage currentDynamicImage;
    //private int height = -75; //TODO: pass in
    private int height = -75; //TODO: pass in


    public MovableHitBoxViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, Direction d) {
        super(r, s, coordinateStrategy, locationStrategy);
        System.out.println(resourcePath + "fireball_n.xml");
        this.movingN = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "fireball_n.xml");
        this.movingNE = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "fireball_ne.xml");
        this.movingNW = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "fireball_nw.xml");
        this.movingS = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "fireball_s.xml");
        this.movingSE = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "fireball_se.xml");
        this.movingSW = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath + "fireball_sw.xml");
        this.direction = d;
        this.changeDirection(this.direction);
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(currentDynamicImage.getImage(),
                this.getXPixels() + currentDynamicImage.getXOffset() + this.getLocationXOffset(),
                this.getYPixels() + height + currentDynamicImage.getYOffset() + this.getLocationYOffset(), this);
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        visibleImages.add(currentDynamicImage);
        return new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
    }

    //TODO: how to pass in the correct direction for this to fire
    public void changeDirection(Direction d) {
        this.direction = d;
        switch (d) {
            case N:
                currentDynamicImage = movingN;
                break;
            case NW:
                currentDynamicImage = movingNW;
                break;
            case NE:
                currentDynamicImage = movingNE;
                break;
            case S:
                currentDynamicImage = movingS;
                break;
            case SW:
                currentDynamicImage = movingSW;
                break;
            case SE:
                currentDynamicImage = movingSE;
                break;
        }

    }

    @Override
    public void movementHook(int r, int s, long duration) {
        //((DynamicTimedImage) currentDynamicImage).start(duration);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitMovableHitBoxViewObject(this);
    }

    @Override
    public void alertDestroyed() {
        //TODO: TELL TILE TO DESTROY VO
        //for(DestroyVOObserver dvoo : this.observers){
        //    dvoo.alertDestroyVO(this);
        //}
        ((DynamicTimedImage) currentDynamicImage).start(20);
    }

}
