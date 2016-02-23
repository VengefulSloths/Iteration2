package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.TileViewObject;
import com.vengeful.sloths.Models.Map.MapArea;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by alexs on 2/23/2016.
 */
public abstract class CameraView{
    private TileViewObject[][] tiles;
    private int maxX;
    private int maxY;

    private PersistentViewObjectFactory factory;

    public CameraView(PersistentViewObjectFactory factory) {
        this.factory = factory;
    }


    public void init(MapArea mapArea) {
        PersistentVOCreationVisitor creator = new PersistentVOCreationVisitor(factory);

        maxX = findX(mapArea.getMaxR(), mapArea.getMaxS());
        maxY = findY(mapArea.getMaxR(), mapArea.getMaxS());


        //Populate tiles array with empty tiles to avoid null pointer garbage
        tiles = new TileViewObject[maxX][maxY];
        for (int i=0; i<maxX; i++) {
            for (int j=0; j<maxY; j++) {
                tiles[i][j] = factory.createTileViewObject(
                        findR(i,j),
                        findS(i,j)
                );
            }
        }

        //This will populate creator with all the tiles from hte map area
        creator.visitMapArea(mapArea);

        //add all the tiles correctly to the array
        Iterator<TileViewObject> iter = creator.getTiles();
        while (iter.hasNext()) {
            TileViewObject current = iter.next();
            int i = findX(current.getR(), current.getS());
            int j = findY(current.getR(), current.getS());
            tiles[i][j] = current;
        }
    }

    public void paintComponent(Graphics2D g) {
        for (int i=0; i<maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                tiles[i][j].paintComponent(g);
            }
        }
    }

    private int findX(int r, int s) {
        return r;
    }
    private int findY(int r, int s) {
        return 2*r+s;
    }
    private int findR(int x, int y) {
        return x;
    }
    private int findS(int x, int y) {
        return (y-x)/2;
    }
}
