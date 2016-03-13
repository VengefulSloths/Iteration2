package com.vengeful.sloths.GameLaunching;

import com.vengeful.sloths.AreaView.CameraViewManager;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Stats.StatAddables.BonusHealthAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.GenericStatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by alexs on 2/28/2016.
 */
public class LaunchNewGame implements LaunchGameHelper {
    private LevelFactory levelFactory;
    public LaunchNewGame() {
        this.levelFactory = new LevelFactory();
        levelFactory.init("test");
    }
    @Override
    public void populateMap() {
        levelFactory.populate();
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
        Avatar.getInstance().avatarInit("Smasher", new Stats(new BonusHealthAddable(30)));
        return Avatar.getInstance();
    }
}
