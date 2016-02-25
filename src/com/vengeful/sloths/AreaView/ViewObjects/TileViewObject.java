package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by alexs on 2/23/2016.
 */

//TODO: Give tile a clean operation that will clear any VOs on it that wont persist
public class TileViewObject {
    private ArrayList<ViewObject> children;
    private int r;
    private int s;
    public TileViewObject(int r, int s) {
        this.r = r;
        this.s = s;
        children = new ArrayList<>();
    }

    public void addChild(ViewObject child) {
        children.add(child);
        System.out.println("got a child");
        children.sort(new Comparator<ViewObject>() {
            VOSorter sorter = new VOSorter();
            @Override
            public int compare(ViewObject o1, ViewObject o2) {
                o1.accept(sorter);
                int z1 = sorter.getZLevel();
                o2.accept(sorter);
                int z2 = sorter.getZLevel();

                return z1 - z2;
            }
        });
    }

    public void removeChild(ViewObject child) {
        children.remove(child);
    }


    public void paintComponent(Graphics2D g) {
        for (ViewObject child: children) {
            child.paintComponent(g);
        }
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }
}
