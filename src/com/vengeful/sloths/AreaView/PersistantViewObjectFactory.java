package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.GroundLevelTerrainViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.MountainLevelTerrainViewObject;
import com.vengeful.sloths.Models.Map.Terrains.Terrain;

/**
 * Created by alexs on 2/22/2016.
 */

public abstract class PersistantViewObjectFactory {
    private CoordinateStrategy cs;
    private LocationStrategy ls;

    public PersistantViewObjectFactory(CoordinateStrategy cs, LocationStrategy ls) {
        this.cs = cs;
        this.ls = ls;
    }

    public abstract GroundLevelTerrainViewObject createGrassViewObject(int r, int s);
    public abstract GroundLevelTerrainViewObject createRoadViewObject(int r, int s);
    public abstract MountainLevelTerrainViewObject createMountainTerrainViewObject(int r, int s);

    public LocationStrategy getLocationStrategy() {
        return ls;
    }

    public CoordinateStrategy getCoordinateStrategy() {
        return cs;
    }

}
