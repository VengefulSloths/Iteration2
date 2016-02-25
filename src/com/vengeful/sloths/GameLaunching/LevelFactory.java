package com.vengeful.sloths.GameLaunching;

import com.vengeful.sloths.AreaView.CameraView;
import com.vengeful.sloths.AreaView.CameraViewManager;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.PlainsCameraView;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.HexMath;
import com.vengeful.sloths.Utility.Location;

import java.util.Iterator;

/**
 * Created by alexs on 2/23/2016.
 */
public class LevelFactory {
    private Map map;
    private CameraViewManager cameras;

    public Map getMap() {
        return map;
    }

    public CameraViewManager getCameras() {
        return cameras;
    }

    public void init(String levelName) {
        switch (levelName) {
            case "test":
                createTestMap();
        }
    }

    public void createTestMap() {
        this.cameras = new CameraViewManager();

        int max = 20;
        MapArea area1 = new MapArea(max,max);
        for (int i=0;i<max;i++) {
            for (int j=0;j<max;j++) {
                boolean mountainFlag = false;
                Coord c = new Coord(i, j);
                Iterator<Coord> iter = HexMath.ring(new Coord(3,4), 2);
                while (iter.hasNext()) {
                    if (iter.next().equals(c)) {
                        mountainFlag = true;
                        break;
                    }
                }
                area1.addTile(new Coord(i,j), mountainFlag ? new Tile(new Mountain()) : new Tile(new Grass()));
            }
        }
        CameraView camera1 = new PlainsCameraView();
        camera1.init(area1);

        cameras.addCameraView(camera1);

        MapArea[] areas = new MapArea[1];
        areas[0] = area1;
        this.map = new Map(new Location(area1, new Coord(3,3)),areas);
    }
}
