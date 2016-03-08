package com.vengeful.sloths.GameLaunching;

import com.vengeful.sloths.AreaView.CameraView;
import com.vengeful.sloths.AreaView.CameraViewManager;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.ConsumableItems;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.Potion;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.*;
import com.vengeful.sloths.Models.Map.MapItems.OneShotItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Terrains.Water;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.Stats.StatAddables.BaseStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;
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

    public Coord getSpawnPoint() {
        return spawnPoint;
    }

    private Coord spawnPoint;
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

    public void populate(String levelName){
        switch (levelName) {
            case "test":
                populateTestMap();
        }
    }

    public void createTestMap() {
        this.cameras = new CameraViewManager();


        //Area 1
        int max = 20;
        MapArea area1 = new MapArea(max,max);
        area1.setName("area1");
        for (int i=0;i<max;i++) {
            for (int j=0;j<max;j++) {
                boolean mountainFlag = false;
                boolean waterFlag = false;
                Coord c = new Coord(i, j);
                Iterator<Coord> iter = HexMath.ring(new Coord(3,4), 2);
                while (iter.hasNext()) {
                    if (iter.next().equals(c)) {
                        mountainFlag = true;
                        break;
                    }
                }
                Iterator<Coord> iter2 = HexMath.ring(new Coord(3,3), 1);
                while (iter2.hasNext()) {
                    if (iter2.next().equals(c)) {
                        waterFlag = true;
                        break;
                    }
                }
                if (waterFlag) {
                    area1.addTile(new Coord(i,j), new Tile(new Water()));

                } else {
                    area1.addTile(new Coord(i, j), mountainFlag ? new Tile(new Mountain()) : new Tile(new Grass()));
                }
            }
        }

        //Area 2
        MapArea area2 = new MapArea(10,10);
        area2.setName("area2");
        for (int i=0;i<10;i++) {
            for (int j = 0; j < 10; j++) {
                area2.addTile(new Coord(i,j), new  Tile( j > 6 ? new Water() : new Grass()));
            }
        }

        TeleportDestinationTile d1 = new TeleportDestinationTile(new Coord(1,2));
        TeleportSenderTile s1 = new TeleportSenderTile(area1, d1);
        area1.addTile(d1.getLocation(), d1);
        area2.addTile(new Coord(0,0), s1);

        TeleportDestinationTile d2 = new TeleportDestinationTile(new Coord(2,1));
        TeleportSenderTile s2 = new TeleportSenderTile(area2, d2);
        area2.addTile(d2.getLocation(), d2);
        area1.addTile(new Coord(0,0), s2);


        MapArea[] areas = new MapArea[2];
        areas[0] = area1;
        areas[1] = area2;

        this.map = Map.getInstance();
        this.map.setMapAreas(areas);
        this.map.setRespawnPoint(new Location(area1, new Coord(3,3)));
        this.map.setActiveMapArea(area2);

        this.spawnPoint = new Coord(2,1);
    }

    public void populateTestMap() {
        MapArea[] areas = Map.getInstance().getMapAreas();
        MapArea area1 = areas[0];
        MapArea area2 = areas[1];
        area1.getTile(new Coord(5,5)).addMapItem(new OneShotItem(new Coord(5,5)));
        area1.getTile(new Coord(6,4)).addMapItem(new OneShotItem(new Coord(6,4)));
        area1.getTile(new Coord(7,3)).addMapItem(new OneShotItem(new Coord(7,3)));
        area1.getTile(new Coord(8,2)).addMapItem(new OneShotItem(new Coord(8,2)));
        area1.getTile(new Coord(9,1)).addMapItem(new OneShotItem(new Coord(9,1)));
        area1.getTile(new Coord(11,1)).addMapItem(new OneShotItem(new Coord(11,1)));


        area1.getTile(new Coord(2,2)).addMapItem(new TakeableItem("redPotion", new Potion("redPotion",new BaseStatsAddable(5,0,0,0,0)), new Coord(1,2)));
        area1.getTile(new Coord(11,10)).addMapItem(new TakeableItem("bluePotion", new Potion("bluePotion",new BaseStatsAddable(0,0,5,0,0)), new Coord(11,10)));
        CameraView camera2 = new PlainsCameraView();
        CameraView camera1 = new PlainsCameraView();


        camera2.init(area2);
        camera1.init(area1);


        cameras.addCameraView(area2, camera2);
        cameras.addCameraView(area1, camera1);
    }
}
