package com.vengeful.sloths.AreaView;

import java.util.ArrayList;

/**
 * Created by alexs on 2/23/2016.
 */
public class CameraViewManager {
    ArrayList<CameraView> cameraViews = new ArrayList<>();
    public void addCameraView(CameraView cv) {
        cameraViews.add(cv);
    }
    public CameraView getCurrentCameraView() {
        return cameraViews.get(0);
    }
}
