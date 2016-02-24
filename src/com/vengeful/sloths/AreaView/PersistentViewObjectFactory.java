package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.EntityViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.GrassViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.MountainViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.TileViewObject;

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
        return new TileViewObject(r, s);
    }

    public EntityViewObject createEnitityViewObject(int r, int s, String resourcePath) {
        return new EntityViewObject(r, s, cs, ls, resourcePath);
    }

    public abstract GrassViewObject createGrassViewObject(int r, int s);
    public abstract GrassViewObject createRoadViewObject(int r, int s);
    public abstract MountainViewObject createMountainTerrainViewObject(int r, int s);

    public LocationStrategy getLocationStrategy() {
        return ls;
    }

    public CoordinateStrategy getCoordinateStrategy() {
        return cs;
    }

}
