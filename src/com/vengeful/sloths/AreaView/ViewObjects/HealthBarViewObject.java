package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.vAlertable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Observers.StatsObserver;

import java.awt.*;

/**
 * Created by John on 3/3/2016.
 */
public class HealthBarViewObject extends MovingViewObject implements StatsObserver {
    private int currentHealth = 50;
    private int maxHealth = 100;

    public HealthBarViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy){
        super(r,s,coordinateStrategy,locationStrategy);
    }
    @Override
    public void paintComponent(Graphics2D g) {
        double offset = ((double)currentHealth/(double)maxHealth) * 32;
        g.setColor(Color.red);
        g.fillRect(this.getXPixels() + getLocationXOffset() - 16,this.getYPixels() + getLocationYOffset() + - 100, 32, 6);
        g.setColor(Color.green);
        g.fillRect(this.getXPixels() + getLocationXOffset() - 16,this.getYPixels() + getLocationYOffset() + - 100,(int)offset,6);
        g.setColor(Color.BLACK);
        g.drawRect(this.getXPixels() + getLocationXOffset() - 16,this.getYPixels() + getLocationYOffset() + - 100,32,6);
    }

    @Override
    public void accept(VOVisitor v) {

    }

    @Override
    public void alertStatChanged(Stats stat) {
        this.currentHealth = stat.getCurrentHealth();
        this.maxHealth = stat.getMaxHealth();
    }
}
