package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicTimedImage;
import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewTime;

import java.awt.*;

/**
 * Created by alexs on 3/3/2016.
 */
public class AttackViewObject extends ViewObject {
    private DynamicImage animation;

    private long duration;

    public AttackViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, String resourcePath, long duration) {
        super(r, s, coordinateStrategy, locationStrategy);
        this.duration = duration;
        this.animation = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath);
    }

    public void start() {
        ((DynamicTimedImage) animation).start(duration);
    }


    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(animation.getImage(),
                this.getXPixels() + animation.getXOffset() + this.getLocationXOffset(),
                this.getYPixels() + animation.getYOffset() + this.getLocationYOffset(),
                this);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitAttack(this);
    }
}
