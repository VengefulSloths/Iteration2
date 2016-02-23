package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.GroundLevelTerrainViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;

/**
 * Created by alexs on 2/22/2016.
 */
public class PlainsPersistantViewObjectFactory extends PersistantViewObjectFactory {
    public PlainsPersistantViewObjectFactory(CoordinateStrategy cs, LocationStrategy ls) {
        super(cs, ls);
    }

    @Override
    public GroundLevelTerrainViewObject createGrassViewObject(int r, int s) {
        return new GroundLevelTerrainViewObject(r, s, getCoordinateStrategy(), getLocationStrategy(), "resources/terrain/grass.xml");
    }

    @Override
    public GroundLevelTerrainViewObject createRoadViewObject(int r, int s) {
        return new GroundLevelTerrainViewObject(r, s, getCoordinateStrategy(), getLocationStrategy(), "resources/terrain/cracked_sand.xml");

    }
}
