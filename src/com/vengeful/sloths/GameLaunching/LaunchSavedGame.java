package com.vengeful.sloths.GameLaunching;

import com.vengeful.sloths.AreaView.CameraViewManager;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by Ian on 3/7/2016.
 */
public class LaunchSavedGame implements LaunchGameHelper {
    private LevelFactory levelFactory;
    public LaunchSavedGame() {
        this.levelFactory = new LevelFactory();
        levelFactory.init("test");
    }
    @Override
    public Map createMap() {
        return levelFactory.getMap();
    }
    @Override
    public Coord spawnPoint() {
        return levelFactory.getSpawnPoint();
    }
    @Override
    public CameraViewManager createCameras() {
        return levelFactory.getCameras();
    }
    @Override
    public Avatar createAvatar() {
        return null;
    }
}
