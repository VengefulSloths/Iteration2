package com.vengeful.sloths.GameLaunching;

import com.vengeful.sloths.AreaView.CameraViewManager;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by alexs on 2/28/2016.
 */
public interface LaunchGameHelper {
    Map createMap();
    Coord spawnPoint();
    CameraViewManager createCameras();
    Avatar createAvatar();


}
