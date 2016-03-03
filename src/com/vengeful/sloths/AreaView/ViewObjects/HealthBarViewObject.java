package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.vAlertable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.View.Observers.EntityObserver;
import com.vengeful.sloths.View.Observers.StatsObserver;

import java.awt.*;

/**
 * Created by John on 3/3/2016.
 */
public class HealthBarViewObject extends MovingViewObject implements StatsObserver {
    private int currentHealth = 0;
    private int maxHealth = 0;

    public HealthBarViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy){
        super(r,s,coordinateStrategy,locationStrategy);
    }
    @Override
    public void paintComponent(Graphics2D g) {
        int offset = (currentHealth/maxHealth) * 32;
        g.setColor(Color.green);
        g.drawRect(100,100, offset,4);
        g.setColor(Color.red);
        g.drawRect(100 + offset,100, 32-offset, 4);
    }

    @Override
    public void accept(VOVisitor v) {

    }

    @Override
    public void alertStatChanged(Stats stat) {
        this.currentHealth = stat.getCurrentHealth();
        this.maxHealth = stat.getCurrentHealth();
    }
}
