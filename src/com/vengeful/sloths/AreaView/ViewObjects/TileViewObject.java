package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 2/23/2016.
 */

//TODO: Give tile a clean operation that will clear any VOs on it that wont persist
public class TileViewObject extends ViewObject {
    private ArrayList<ViewObject> children;

    public TileViewObject(int r, int s, CoordinateStrategy coordinateStrategy, LocationStrategy locationStrategy) {
        super(r, s, coordinateStrategy, locationStrategy);
        children = new ArrayList<>();
    }

    public void addChild(ViewObject child) {
        children.add(child);
    }

    public void removeChild(ViewObject child) {
        children.remove(child);
    }

    @Override
    public void paintComponent(Graphics2D g) {
        for (ViewObject child: children) {
            child.paintComponent(g);
        }
    }
}
