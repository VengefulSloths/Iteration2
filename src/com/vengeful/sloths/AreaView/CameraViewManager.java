package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by alexs on 2/23/2016.
 */
public class CameraViewManager {
    HashMap<MapArea, CameraView> cameraViews = new HashMap<>();


    public void addCameraView(MapArea ma, CameraView cv) {
        cameraViews.put(ma, cv);
    }

    public CameraView getCameraView(MapArea ma) {
        return cameraViews.get(ma);
    }

    public CameraView getCurrentCameraView() {
        return cameraViews.get(Map.getInstance().getActiveMapArea());
    }
}
