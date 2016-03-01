package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.GrassViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.MountainViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.OneShotViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.WaterViewObject;

/**
 * Created by alexs on 2/22/2016.
 */
public class PlainsPersistantViewObjectFactory extends ViewObjectFactory {
    public PlainsPersistantViewObjectFactory(CoordinateStrategy cs, LocationStrategy ls) {
        super(cs, ls);
    }

    @Override
    public GrassViewObject createGrassViewObject(int r, int s) {
        return new GrassViewObject(r, s, getCoordinateStrategy(), getLocationStrategy(), "resources/terrain/grass.xml");
    }

    @Override
    public GrassViewObject createRoadViewObject(int r, int s) {
        return new GrassViewObject(r, s, getCoordinateStrategy(), getLocationStrategy(), "resources/terrain/cracked_sand.xml");

    }

    @Override
    public MountainViewObject createMountainTerrainViewObject(int r, int s) {
        return new MountainViewObject(r, s, getCoordinateStrategy(), getLocationStrategy(), "resources/terrain/grass_mountain.xml");
    }

    @Override
    public WaterViewObject createWaterTerrainViewObject(int r, int s) {
        return new WaterViewObject(r, s, getCoordinateStrategy(), getLocationStrategy(), "resources/terrain/water.xml");
    }

    @Override
    public OneShotViewObject createOneShotViewObject(int r, int s) {
        return new OneShotViewObject(r, s, getCoordinateStrategy(), getLocationStrategy(), "resources/items/box/box_full.xml", "resources/audio/break.wav");
    }
}
