package com.vengeful.sloths.AreaView.ViewObjects;


import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewTime;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alex on 3/7/2016.
 */
public class DamageNumberViewObject extends ViewObject {
    private static Font font = new Font("Futura", Font.BOLD, 18);
    private int height = 100;
    private float transparency = 1f;
    private boolean isDone = false;
    private String damage;

    public DamageNumberViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy, int damage) {
        super(r, s, coordinateStrategy, locationStrategy);
        this.damage = String.valueOf(damage);
        adjust();
    }

    private void adjust() {
        if (height < 150) {
            height = height + 3;
            transparency = transparency - 0.05f;
            ViewTime.getInstance().registerAlert(50, () -> adjust());
        } else {
            isDone = true;
        }
    }

    @Override
    public void paintComponent(Graphics2D g) {
        if (!isDone) {
            g.setColor(Color.yellow);
            try {
                Composite c = g.getComposite();
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
                g.setFont(font);
                FontMetrics metrics = g.getFontMetrics();
                g.drawString(damage, getLocationXOffset() + getXPixels() - metrics.stringWidth(damage)/2, getYPixels() + getLocationYOffset() - height);
                g.setComposite(c);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public NonVisibleViewObject getNonVisibleSnapShot() {
        ArrayList<DynamicImage> visibleImages = new ArrayList<>();
        return new NonVisibleViewObject(getR(), getS(), getCoordinateStrategy(), getLocationStrategy(), visibleImages);
    }

    @Override
    public void accept(VOVisitor v) {
        v.visitDamageNumber(this);
    }
}
