package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.GroundLevelTerrainViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.MountainLevelTerrainViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.TileViewObject;
import com.vengeful.sloths.Models.Map.Terrains.Terrain;

/**
 * Created by alexs on 2/22/2016.
 */

public abstract class PersistentViewObjectFactory {
    private CoordinateStrategy cs;
    private LocationStrategy ls;

    public PersistentViewObjectFactory(CoordinateStrategy cs, LocationStrategy ls) {
        this.cs = cs;
        this.ls = ls;
    }

    public TileViewObject createTileViewObject(int r, int s) {
        return new TileViewObject(r, s, cs, ls);
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
