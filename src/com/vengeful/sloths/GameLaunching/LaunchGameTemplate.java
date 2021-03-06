package com.vengeful.sloths.GameLaunching;

import com.vengeful.sloths.AreaView.*;
import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.PickPocketAbility;
import com.vengeful.sloths.Models.Ability.AbilityFactory;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.EntityMapInteractionFactory;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.Potion;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.*;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.Gold;
import com.vengeful.sloths.Models.ModelEngine;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StrengthAddable;
import com.vengeful.sloths.Utility.Config;
import com.vengeful.sloths.Utility.WeaponClass;
import com.vengeful.sloths.Views.AbilitiesSkillsView.AbilitiesSkillView;
import com.vengeful.sloths.Views.AbilitiesView.GridAbilitiesView;
import com.vengeful.sloths.Views.CharacterView.CharacterView;
import com.vengeful.sloths.Views.EquipmentView.EquipmentView;
import com.vengeful.sloths.Views.EquippedAbilitiesView.EquippedAbilitiesView;
import com.vengeful.sloths.Views.HUDView.HUDView;
import com.vengeful.sloths.Views.InventoryView.GridInventoryView;
import com.vengeful.sloths.Views.InventoryView.InventoryView;
import com.vengeful.sloths.Views.InventoryView.ListInventoryView;
import com.vengeful.sloths.Views.SkillsView.SkillsView;
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


    public void launch(String avatarOccupation) {
        map = helper.createMap();
        cameras = helper.createCameras();
        helper.populateMap();
        avatar = helper.createAvatar(avatarOccupation);
        // Set avatar time to respawn (150/30 --> 5 seconds)
        avatar.setTimeToRespawn(150);
        avatar.getStats().setMovement(45);
//        avatar.getInstance().getStats().setHardiness(10);
//        Avatar.getInstance().getStats().setCurrentHealth(60);
        map.addEntity(helper.spawnPoint(), avatar);
        //below line doesnt work for some reason despite adding the avatar to the map
//        map.getActiveMapArea().getTile(helper.spawnPoint()).addEntity(Avatar.getInstance());

        initSingletons();
        /**
         * AVATAR STARTING INVENTORY
         */
        avatar.getInventory().addGold(new Gold(100));
        Potion p = new Potion("Blue Potion", new StrengthAddable(5));
//        avatar.getInventory().addItem(p);
        avatar.equip(new Mount("Hover", 10));

        //************************END AVATAR STARING INVENTORY


        AreaView areaView = new AreaView(cameras);
        //ViewManager vm = new ViewManager();
        HUDView hv = new HUDView(Config.instance().getHUDViewWidth(), Config.instance().getHUDViewHeight());
        GridInventoryView giv = new GridInventoryView(avatar.getInventory());

        EquipmentView ev = new EquipmentView(avatar.getEquipped());
        StatsView sv = new StatsView(avatar.getStats());
        //ViewManager vm = new ViewManager(areaView, hv);
        CharacterView cv = new CharacterView(giv, ev, sv);

        GridAbilitiesView gav = new GridAbilitiesView(avatar.getAbilityManager());
        EquippedAbilitiesView equippedAbilitiesView = new EquippedAbilitiesView(avatar.getAbilityManager());
        SkillsView skillsView = new SkillsView(avatar.getSkillManager());
        AbilitiesSkillView abilitiesSkillView = new AbilitiesSkillView(gav, equippedAbilitiesView, skillsView);

        ViewManager vm = new ViewManager(areaView, hv, cv);
        vm.setAbilitiesSkillView(abilitiesSkillView);

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

//        ViewTime.getInstance().registerAlert(100, () -> {
//            vm.getDialogView().alertDialogChange("I sexually Identify as an Attack Helicopter. Ever since I was a boy I dreamed of soaring over the oilfields dropping hot sticky loads on disgusting foreigners. People say to me that a person being a helicopter is Impossible and I’m fucking retarded but I don’t care, I’m beautiful. I’m having a plastic surgeon install rotary blades, 30 mm cannons and AMG-114 Hellfire missiles on my body. From now on I want you guys to call me “Apache” and respect my right to kill from above and kill needlessly.");
//            vm.openDialogView();
//        });


        modelEngine.start();

       //viewEngine.start();

    }

    public void launchLoaded(){
        map = helper.createMap();
        cameras = helper.createCameras();
        helper.populateMap();
        avatar = Avatar.getInstance();
        initSingletons();
//        AreaView areaView = new AreaView(cameras);
//        //ViewManager vm = new ViewManager();
//        HUDView hv = new HUDView(Config.instance().getHUDViewWidth(), Config.instance().getHUDViewHeight());
//        GridInventoryView giv = new GridInventoryView(avatar.getInventory());
//        GridAbilitiesView gridAbilitiesView = new GridAbilitiesView(avatar.getAbilityManager());
//        EquipmentView ev = new EquipmentView(avatar.getEquipped());
//        StatsView sv = new StatsView(avatar.getStats());
//        //ViewManager vm = new ViewManager(areaView, hv);
//        CharacterView cv = new CharacterView(giv, ev, sv);
//        EquippedAbilitiesView equippedAbilitiesView = new EquippedAbilitiesView(avatar.getAbilityManager());
//        SkillsView skillsView = new SkillsView(avatar.getSkillManager());
//        AbilitiesSkillView abilitiesSkillView = new AbilitiesSkillView(gridAbilitiesView, equippedAbilitiesView, skillsView);
//        ViewManager vm = new ViewManager(areaView, hv, cv);
//        vm.setAbilitiesSkillView(abilitiesSkillView);
//
//        //ViewEngine viewEngine = new ViewEngine(areaView);
//        ViewEngine viewEngine = ViewEngine.getInstance();
//        viewEngine.registerView(vm);
//
//
//        //viewEngine.registerView(cv);
//
//
//        //ViewEngine viewEngine = new ViewEngine(areaView);
//        //ViewEngine viewEngine = ViewEngine.getInstance();
//        //viewEngine.killOldView();
//        //viewEngine.registerView(areaView);
//
//        //ModelEngine modelEngine = new ModelEngine();
//        ModelEngine modelEngine = ModelEngine.getInstance();
//        MainController controller = MainController.getInstance();
//        controller.init(vm);
//        controller.setAvatarControllerState();
//
//        modelEngine.start();

        AreaView areaView = new AreaView(cameras);
        //above line is what fails to find xmls..doesn't do this when starting a new game...
        HUDView hv = new HUDView(Config.instance().getHUDViewWidth(), Config.instance().getHUDViewHeight());
        GridInventoryView giv = new GridInventoryView(avatar.getInventory());

        EquipmentView ev = new EquipmentView(avatar.getEquipped());
        StatsView sv = new StatsView(avatar.getStats());
        CharacterView cv = new CharacterView(giv, ev, sv);
        GridAbilitiesView gav = new GridAbilitiesView(avatar.getAbilityManager());
        EquippedAbilitiesView equippedAbilitiesView = new EquippedAbilitiesView(avatar.getAbilityManager());
        SkillsView skillsView = new SkillsView(avatar.getSkillManager());
        AbilitiesSkillView abilitiesSkillView = new AbilitiesSkillView(gav, equippedAbilitiesView, skillsView);

        ViewManager vm = new ViewManager(areaView, hv, cv);
        vm.setAbilitiesSkillView(abilitiesSkillView);
        ViewEngine viewEngine = ViewEngine.getInstance();
        viewEngine.registerView(vm);
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
