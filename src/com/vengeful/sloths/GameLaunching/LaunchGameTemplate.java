package com.vengeful.sloths.GameLaunching;

import com.vengeful.sloths.AreaView.*;
import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.EntityMapInteractionFactory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.ModelEngine;

/**
 * Created by alexs on 2/28/2016.
 */
public class LaunchGameTemplate {
    private Map map;
    private LaunchGameHelper helper;
    private CameraViewManager cameras;
    private Avatar avatar;

    public LaunchGameTemplate(LaunchGameHelper helper) {
        this.helper = helper;
    }


    public void launch() {
        map = helper.createMap();
        cameras = helper.createCameras();
        avatar = helper.createAvatar();
        map.addEntity(helper.spawnPoint(), avatar);

        AreaView areaView = new AreaView(cameras);
        initSingletons();

        //ViewEngine viewEngine = new ViewEngine(areaView);
        ViewEngine viewEngine = ViewEngine.getInstance();
        viewEngine.registerView(areaView);
        //ModelEngine modelEngine = new ModelEngine();
        ModelEngine modelEngine = ModelEngine.getInstance();
        MainController controller = MainController.getInstance();

        modelEngine.start();
        viewEngine.start();

    }

    public void initSingletons() {
        //Make sure that area view is created before this

        //TemporaryVOCreationVisitor.getInstance().setActiveCameraView(cameras.getCurrentCameraView());

        //This got moved to MapAReasetActiveCameraView
        //TemporaryVOCreationVisitor.getInstance().visitAvatar(avatar);

        EntityMapInteractionFactory.getInstance().init(map);
    }


}
