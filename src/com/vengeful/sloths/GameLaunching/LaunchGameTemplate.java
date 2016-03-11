package com.vengeful.sloths.GameLaunching;

import com.vengeful.sloths.AreaView.*;
import com.vengeful.sloths.Controllers.InputController.InputControllerStates.InventoryControllerState;
import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Piggy;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.EntityMapInteractionFactory;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.Potion;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Mount;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.ModelEngine;
import com.vengeful.sloths.Models.Stats.StatAddables.BaseStatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Utility.Config;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Views.CharacterView.CharacterView;
import com.vengeful.sloths.Views.EquipmentView.EquipmentView;
import com.vengeful.sloths.Views.HUDView.HUDView;
import com.vengeful.sloths.Views.InventoryView.GridInventoryView;
import com.vengeful.sloths.Views.InventoryView.InventoryView;
import com.vengeful.sloths.Views.InventoryView.ListInventoryView;
import com.vengeful.sloths.Views.StatsView.StatsView;
import com.vengeful.sloths.Views.ViewManager.ViewManager;

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
        helper.populateMap();
        avatar = helper.createAvatar();
        // Set avatar time to respawn (150/30 --> 5 seconds)
        avatar.setTimeToRespawn(150);
        avatar.getStats().setMovement(45);
        avatar.equip(new Mount("hex", 10));
//        avatar.getInstance().getStats().setHardiness(10);
//        Avatar.getInstance().getStats().setCurrentHealth(60);
        map.addEntity(helper.spawnPoint(), avatar);
        //below line doesnt work for some reason despite adding the avatar to the map
//        map.getActiveMapArea().getTile(helper.spawnPoint()).addEntity(Avatar.getInstance());

        //Coord newSpawn = helper.spawnPoint();
        //newSpawn.setR(newSpawn.getR()+1);
//        map.addEntity(newSpawn,new Piggy());

        initSingletons();

        /*****Test avatar drop******/
        avatar.getInventory().addItem(new Potion("redPotion", new HealthManaExperienceAddable(5,0,0,0,0)));
        avatar.getInventory().addItem(new Potion("bluePotion", new HealthManaExperienceAddable(0,0,5,0,0)));
        avatar.getInventory().addItem(new Potion("bluePotion", new HealthManaExperienceAddable(0,0,5,0,0)));
        avatar.getInventory().addItem(new Potion("bluePotion", new HealthManaExperienceAddable(0,0,5,0,0)));
        avatar.getInventory().addItem(new Potion("bluePotion", new HealthManaExperienceAddable(0,0,5,0,0)));
        avatar.getInventory().addItem(new Potion("bluePotion", new HealthManaExperienceAddable(0,0,5,0,0)));
        avatar.getInventory().addItem(new Potion("redPotion", new HealthManaExperienceAddable(5,0,0,0,0)));
        avatar.getInventory().addItem(new Potion("redPotion", new HealthManaExperienceAddable(5,0,0,0,0)));
        avatar.getInventory().addItem(new Potion("redPotion", new HealthManaExperienceAddable(5,0,0,0,0)));
        avatar.getInventory().addItem(new Potion("redPotion", new HealthManaExperienceAddable(5,0,0,0,0)));
        avatar.getInventory().addItem(new Potion("redPotion", new HealthManaExperienceAddable(5,0,0,0,0)));
        avatar.getInventory().addItem(new Potion("redPotion", new HealthManaExperienceAddable(5,0,0,0,0)));
        avatar.getInventory().addItem(new Potion("redPotion", new HealthManaExperienceAddable(5,0,0,0,0)));
        avatar.getInventory().addItem(new Potion("bluePotion", new HealthManaExperienceAddable(0,0,5,0,0)));
        avatar.getInventory().addItem(new Potion("bluePotion", new HealthManaExperienceAddable(0,0,5,0,0)));
        avatar.getInventory().addItem(new Potion("bluePotion", new HealthManaExperienceAddable(0,0,5,0,0)));
        avatar.getInventory().addItem(new Potion("bluePotion", new HealthManaExperienceAddable(0,0,5,0,0)));
        avatar.getInventory().addItem(new Potion("bluePotion", new HealthManaExperienceAddable(0,0,5,0,0)));
        avatar.getInventory().addItem(new Potion("bluePotion", new HealthManaExperienceAddable(0,0,5,0,0)));
        avatar.getInventory().addItem(new Potion("redPotion", new HealthManaExperienceAddable(5,0,0,0,0)));




        /**************************/

        AreaView areaView = new AreaView(cameras);
        //ViewManager vm = new ViewManager();
        HUDView hv = new HUDView(Config.instance().getHUDViewWidth(), Config.instance().getHUDViewHeight());
        GridInventoryView giv = new GridInventoryView(avatar.getInventory());
        EquipmentView ev = new EquipmentView(avatar.getEquipped());
        StatsView sv = new StatsView(avatar.getStats());
        //ViewManager vm = new ViewManager(areaView, hv);
        CharacterView cv = new CharacterView(giv, ev, sv);
        ViewManager vm = new ViewManager(areaView, hv, cv);

        //ViewEngine viewEngine = new ViewEngine(areaView);
        ViewEngine viewEngine = ViewEngine.getInstance();
        viewEngine.registerView(vm);


        //viewEngine.registerView(cv);


        //ViewEngine viewEngine = new ViewEngine(areaView);
        //ViewEngine viewEngine = ViewEngine.getInstance();
        //viewEngine.killOldView();
        //viewEngine.registerView(areaView);

        //ModelEngine modelEngine = new ModelEngine();
        ModelEngine modelEngine = ModelEngine.getInstance();
        MainController controller = MainController.getInstance();
        controller.init(vm);
        controller.setAvatarControllerState();


        modelEngine.start();

       //viewEngine.start();

    }

    public void launchLoaded(){
        map = helper.createMap();
        helper.populateMap();
        cameras = helper.createCameras();
        avatar = Avatar.getInstance();
        initSingletons();
        AreaView areaView = new AreaView(cameras);
        //ViewManager vm = new ViewManager();
        HUDView hv = new HUDView(Config.instance().getHUDViewWidth(), Config.instance().getHUDViewHeight());
        GridInventoryView giv = new GridInventoryView(avatar.getInventory());
        EquipmentView ev = new EquipmentView(avatar.getEquipped());
        StatsView sv = new StatsView(avatar.getStats());
        //ViewManager vm = new ViewManager(areaView, hv);
        CharacterView cv = new CharacterView(giv, ev, sv);
        ViewManager vm = new ViewManager(areaView, hv, cv);

        //ViewEngine viewEngine = new ViewEngine(areaView);
        ViewEngine viewEngine = ViewEngine.getInstance();
        viewEngine.registerView(vm);


        //viewEngine.registerView(cv);


        //ViewEngine viewEngine = new ViewEngine(areaView);
        //ViewEngine viewEngine = ViewEngine.getInstance();
        //viewEngine.killOldView();
        //viewEngine.registerView(areaView);

        //ModelEngine modelEngine = new ModelEngine();
        ModelEngine modelEngine = ModelEngine.getInstance();
        MainController controller = MainController.getInstance();
        controller.init(vm);
        controller.setAvatarControllerState();

        modelEngine.start();
    }

    public void initSingletons() {
        //Make sure that area view is created before this

        //TemporaryVOCreationVisitor.getInstance().setActiveCameraView(cameras.getCurrentCameraView());

        //This got moved to MapAReasetActiveCameraView
        //TemporaryVOCreationVisitor.getInstance().visitAvatar(avatar);

        EntityMapInteractionFactory.getInstance().init(map);
    }


}
