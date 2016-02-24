package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.GrassViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.MountainViewObject;

/**
 * Created by alexs on 2/22/2016.
 */
public class PlainsPersistantViewObjectFactory extends PersistentViewObjectFactory {
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
}
