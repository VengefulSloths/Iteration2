package com.vengeful.sloths.GameLaunching;

import com.vengeful.sloths.AreaView.CameraView;
import com.vengeful.sloths.AreaView.CameraViewManager;
import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.SimpleHexCoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.DecalViewObject;
import com.vengeful.sloths.Controllers.ControllerManagers.AggressiveNPCControllerManager;
import com.vengeful.sloths.Controllers.ControllerManagers.NonAggressiveNPCControllerManager;
import com.vengeful.sloths.Controllers.ControllerManagers.PiggyControllerManager;
import com.vengeful.sloths.Models.Ability.Abilities.MeleeAttackAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SelfBuffAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities.FireBallAbility;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityFactory;
import com.vengeful.sloths.Models.DialogueTrade.DialogContainer;
import com.vengeful.sloths.Models.DialogueTrade.TerminalDialogContainer;
import com.vengeful.sloths.Models.Entity.AggressiveNPC;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.NonAggressiveNPC;
import com.vengeful.sloths.Models.Entity.Piggy;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.DropAllGoldCommand;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.Potion;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Bow;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.TwoHandedWeapon;

import com.vengeful.sloths.Models.InventoryItems.EquippableItems.*;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.InventoryItems.UsableItems.PiggyTotem;
import com.vengeful.sloths.Models.Map.AreaEffects.HealDamageAE;
import com.vengeful.sloths.Models.Map.AreaEffects.InstantDeathAE;
import com.vengeful.sloths.Models.Map.AreaEffects.LevelUpAE;
import com.vengeful.sloths.Models.Map.AreaEffects.TakeDamageAE;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.*;
import com.vengeful.sloths.Models.Map.MapItems.AbilityItem;
import com.vengeful.sloths.Models.Map.MapItems.Gold;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.InteractiveItem;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.DoDestroyObstacleQuest;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.HasItemQuest;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.Quest;
import com.vengeful.sloths.Models.Map.MapItems.Obstacle;
import com.vengeful.sloths.Models.Map.MapItems.OneShotItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Map.MapItems.Trap;
import com.vengeful.sloths.Models.Map.Terrains.DummyTerrain;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Terrains.Water;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.Stats.StatAddables.*;
import com.vengeful.sloths.AreaView.PlainsCameraView;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.*;

import java.awt.geom.Area;
import java.util.Iterator;

/**
 * Created by alexs on 2/23/2016.
 */
public class LevelFactory {
    private Map map;
    private CameraViewManager cameras;
    private String levelName;

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
        this.levelName = levelName;
        switch (levelName) {
            case "test":
                createTestMap();
                break;
            case "demo":
                createDemoMap();
                break;
        }
    }

    public void populate() {
        switch (levelName) {
            case "test":
                populateTestMap();
                break;
            case "demo":
                populateDemoMap();
                break;
        }
    }


    public MapArea createArea2() {

        int rows = 14;
        int cols = 14;
        int numWaterSides = 14;
        int numGrass = cols - 2 * numWaterSides;

        MapArea rescue = new MapArea(rows, cols);
        rescue.setName("Rescue Mission");

        int row;
        int col;
        /*
        for (col = 0; col < cols; col++) {
            for (row = 0; row < rows - (rows - row); row++) {
                rescue.addTile(new Coord(row, col), new Tile(new Grass()));
            }
            rescue.addTile(new Coord(row, col), new Tile(new Water()));
        }
        */

        /*
        for (int i = 0; i < cols; i++) {
            for (int j = 0; i < rows; j++) {
                if (j < 5 && i > 6) {
                    rescue.addTile(new Coord(i, j), new Tile(new Grass()));
                } else {
                    rescue.addTile(new Coord(i, j), new Tile(new Water()));
                }
            }

        }
        */
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                rescue.addTile(new Coord(i, j), new Tile(new Grass()));
            }
        }
        return rescue;
    }



    public void createDemoMap(){
        this.cameras = new CameraViewManager();
        //call create each area
        MapArea town = createAreaTown();
        //link areas with teleport tiles

        MapArea rescue = createArea2();

        //SETTING MAPAREAS
        MapArea[] areas = new MapArea[2];
        areas[0] = town;
        areas[1] = rescue;

        this.map = Map.getInstance();
        this.map.setMapAreas(areas);
        //this.map.setRespawnPoint(new Location(town, new Coord(3,3)));
        //this.map.setActiveMapArea(town);
        //this.spawnPoint = new Coord(9,1);

        this.map.setRespawnPoint(new Location(rescue, new Coord(3,3)));
        this.map.setActiveMapArea(rescue);
        this.spawnPoint = new Coord(9,1);

    }



    public MapArea createAreaTown(){
        MapArea town = new MapArea(11,11);
        town.setName("town");
        for (int i=2;i<10;i++) {
            for (int j = 1; j < 10; j++) {
                town.addTile(new Coord(i,j), new  Tile( j > 7 ? new Water() : new Grass()));
            }
        }
        for(int j = 1; j != 10; ++j){
            town.addTile(new Coord(1,j), new Tile(new Mountain()));
        }
        town.getTile(new Coord(2,2)).setTerrain(new Mountain());
        for(int i = 3; i != 6; ++i){
            for(int j = 3; j !=6; ++j ){
                town.getTile(new Coord(i,j)).setTerrain(new DummyTerrain());
            }
        }
        return town;
    }

    public void populateDemoMap(){
        MapArea[] areas = Map.getInstance().getMapAreas();
        MapArea town = areas[0];
        MapArea rescue = areas[1];
        populateAreaTown(town);

        //CAMERAS
        CameraView camera1 = new PlainsCameraView();
        //camera1.init(town);
        camera1.init(rescue);
        //cameras.addCameraView(town, camera1);
        cameras.addCameraView(rescue, camera1);


    }

    public void populateAreaTown(MapArea town){
        //ITEMS AND QUESTS
        town.getTile(new Coord(3,2)).addObstacle(new Obstacle(new Coord(3,2)));
        Quest quest1_b = new DoDestroyObstacleQuest(new Coord(3,2));
        Quest quest1_a = new HasItemQuest(quest1_b, "Blue Potion");
        town.getTile(new Coord(4,2)).addInteractiveItem(new InteractiveItem(quest1_a, new Coord(4,2)));

        //NPCS
        NonAggressiveNPC testNPC = new NonAggressiveNPC("greg", new Stats( new BaseStatsAddable(0,0,0,10,20)));
        town.getTile(new Coord(8,3)).addEntity(testNPC);
        testNPC.setLocation(new Coord(8,3));
        new NonAggressiveNPCControllerManager(town, testNPC, Direction.S);

        NonAggressiveNPC testNPC2 = new NonAggressiveNPC("Bob", new Stats( new BaseStatsAddable(0,0,0,10,20)));
        town.getTile(new Coord(2,1)).addEntity(testNPC2);
        testNPC2.setLocation(new Coord(2,1));
        new NonAggressiveNPCControllerManager(town, testNPC, Direction.S);

        //CAMERAS
        CameraView camera1 = new PlainsCameraView();
        camera1.init(town);
        cameras.addCameraView(town, camera1);
        //***********************************END TOWN********************************************************88




    }
    public void createTestMap() {
        this.cameras = new CameraViewManager();


        //Area 1
        int max = 20;
        MapArea area1 = new MapArea(max,max);
        area1.setName("area1");
        for (int i=1;i<max-1;i++) {
            for (int j=1;j<max-1;j++) {
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
                    area1.addTile(new Coord(i,j), new Tile(new Grass()));

                } else {
                    area1.addTile(new Coord(i, j), mountainFlag ? new Tile(new Mountain()) : new Tile(new Grass()));
                }
            }
        }



        //Area 2
        MapArea area2 = new MapArea(10,10);
        area2.setName("area2");
        for (int i=1;i<9;i++) {
            for (int j = 1; j < 9; j++) {
                area2.addTile(new Coord(i,j), new  Tile( j > 6 ? new Water() : new Grass()));
            }
        }

        TeleportDestinationTile d1 = new TeleportDestinationTile(new Coord(1,2));
        TeleportSenderTile s1 = new TeleportSenderTile(area1, d1);
        area1.addTile(d1.getLocation(), d1);
        area2.addTile(new Coord(1,1), s1);

        TeleportDestinationTile d2 = new TeleportDestinationTile(new Coord(2,1));
        TeleportSenderTile s2 = new TeleportSenderTile(area2, d2);
        area2.addTile(d2.getLocation(), d2);

        area1.getTile(new Coord(2,3)).addObstacle(new Obstacle(new Coord(2,3)));

        area1.getTile(new Coord(6,6)).addAreaEffect(new TakeDamageAE(1));
        area1.getTile(new Coord(6,7)).addAreaEffect(new HealDamageAE(1));
        area1.getTile(new Coord(6,8)).addAreaEffect(new LevelUpAE(1));
        area1.getTile(new Coord(6,9)).addAreaEffect(new InstantDeathAE());

        //Test Trap:
        area1.getTile(new Coord(6,5)).addTrap(new Trap(new Coord(6,5)));


        area1.addTile(new Coord(1,1), s2);


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
        area1.getTile(new Coord(5,5)).addOneShotItem(new OneShotItem(new Coord(5,5)));
        area1.getTile(new Coord(5,5)).addGold(new Gold(1000000000,new Coord(5,5)));
        area1.getTile(new Coord(5,6)).addGold(new Gold(10, new Coord(5,6)));
        area1.getTile(new Coord(6,4)).addOneShotItem(new OneShotItem(new Coord(6,4)));
        area1.getTile(new Coord(7,3)).addOneShotItem(new OneShotItem(new Coord(7,3)));
        area1.getTile(new Coord(8,2)).addOneShotItem(new OneShotItem(new Coord(8,2)));
        area1.getTile(new Coord(9,1)).addOneShotItem(new OneShotItem(new Coord(9,1)));
        area1.getTile(new Coord(11,1)).addOneShotItem(new OneShotItem(new Coord(11,1)));


        area1.getTile(new Coord(2,2)).addTakeableItem(new TakeableItem("Red Potion", new Potion("Red Potion",new BaseStatsAddable(5,0,0,0,0)), new Coord(2,2)));
        area1.getTile(new Coord(11,10)).addTakeableItem(new TakeableItem("Blue Potion", new Potion("Blue Potion",new BaseStatsAddable(0,0,5,0,0)), new Coord(11,10)));
//        area2.getTile(new Coord(2,2)).addTakeableItem(new TakeableItem("redPotion", new Potion("redPotion",new BaseStatsAddable(5,0,0,0,0)), new Coord(2,2)));


        area1.getTile(new Coord(9, 2)).addTakeableItem(new TakeableItem("Bow", new Bow("Bow", new AgilityAddable(3), 3, WeaponClass.BOW), new Coord(9,2)));

        Quest quest1_b = new DoDestroyObstacleQuest(new Coord(2,3));
        Quest quest1_a = new HasItemQuest(quest1_b, "Blue Potion");
        area1.getTile(new Coord(2,2)).addInteractiveItem(new InteractiveItem(quest1_a, new Coord(2,2)));

        CameraView camera2 = new PlainsCameraView();
        CameraView camera1 = new PlainsCameraView();
//
        Potion p = new Potion("Red Potion", new CurrentHealthAddable(20));
        p.setValue(100000);
//        Piggy testPiggy = new Piggy("Bart", new Stats(new MovementAddable(30)));
//        testPiggy.getInventory().addItem(p);
//        testPiggy.getInventory().addItem(p);
//        testPiggy.getInventory().addItem(p);
//        testPiggy.getInventory().addItem(p);
//        testPiggy.setFacingDirection(Direction.S);
//        testPiggy.getStats().add(new BonusHealthAddable(100));
////        testPiggy.getStats().setCurrentHealth(0);
//        Map.getInstance().addEntity(new Coord(3,5), testPiggy);
//        testPiggy.baddOOReceiveGoldForTesting(new Gold(100, new Coord()));
//        new PiggyControllerManager(Map.getInstance().getActiveMapArea(), testPiggy);

//        Avatar.getInstance().setPet(testPiggy);

//        TakeableItem piggyTotem = new TakeableItem("Piggy Totem", new PiggyTotem("Piggy Totem", testPiggy), new Coord(2,2));

        FireBallAbility fireBallAbility = AbilityFactory.getInstance().createFireBallAbility(Avatar.getInstance());
        fireBallAbility.setItemName("Fire Ball");
        area1.getTile(new Coord(3,3)).addTakeableItem(new TakeableItem("Fire Ball", new AbilityItem(fireBallAbility), new Coord(3,3)));

        SelfBuffAbility hot = AbilityFactory.getInstance().createHealOverTime(Avatar.getInstance());

        SelfBuffAbility roids = AbilityFactory.getInstance().createDamageBoost(Avatar.getInstance());
        roids.setItemName("Roids");

        area1.getTile(new Coord(7,7)).addTakeableItem(new TakeableItem("Roids", new AbilityItem(roids), new Coord(7,7)));


//        testPiggy.setPiggyTotem(piggyTotem);

        TakeableItem katarTakeeable = new TakeableItem("Katar", new Knuckle("Katar", new StrengthAddable(5), 10), new Coord(8,8));

        area1.getTile(new Coord(8,8)).addTakeableItem(katarTakeeable);

        //dialog test on piggy


        camera2.init(area2);
        camera1.init(area1);


        camera1.addDecal(new Coord(3, 3), "resources/decals/Hydrangeas_fast.xml");
        camera2.addDecal(new Coord(3, 3), "resources/decals/Hydrangeas_fast.xml");
        camera2.addDecal(new Coord(4,4), "resources/decals/Hydrangeas_med.xml");
//        camera2.addDecal(new Coord(5,5), "resources/decals/Hydrangeas_slow.xml");
        camera2.addDecal(new Coord(5,5), "resources/decals/Roses_slow.xml");


        TemporaryVOCreationVisitor.getInstance().setActiveCameraView(camera2);

        NonAggressiveNPC testNPC = new NonAggressiveNPC("greg", new Stats( new BaseStatsAddable(0,0,0,10,20)));
        area2.getTile(new Coord(5,5)).addEntity(testNPC);
        testNPC.setLocation(new Coord(5,5));
        new NonAggressiveNPCControllerManager(area2, testNPC, Direction.S);



        //stuff to test enemy controllers
        AggressiveNPC testEnemy =  new AggressiveNPC("xXOG_SwaG_LorD_BlazE_MasteR_420_Xx", new Stats(new BaseStatsAddable(0,0,0,15,30)));
//        testEnemy.getInventory().addItem(p);
//        testEnemy.getInventory().addItem(p);
//        testEnemy.getInventory().addItem(p);
//        testEnemy.getInventory().addItem(p);
//        testEnemy.getInventory().addItem(p);
        area2.getTile(new Coord(3,3)).addEntity(testEnemy);
        testEnemy.setLocation(new Coord(3,3));
        testEnemy.equip(new TwoHandedWeapon("Iron 2H", new StrengthAddable(1), 1));

        //testEnemy.accept(TemporaryVOCreationVisitor.getInstance());
        new AggressiveNPCControllerManager(area2, testEnemy);
        testEnemy.setShirt("pink_shirt");
        testEnemy.getStats().subtract(new CurrentHealthAddable(1));
        camera1.addDecal(new Coord(2,2), "resources/terrain/cracked_sand.xml" );
        camera2.addDecal(new Coord(2,2), "resources/terrain/cracked_sand.xml" );

//        map.getActiveMapArea().getTile(spawnPoint).addEntity(Avatar.getInstance());
        cameras.addCameraView(area2, camera2);
        cameras.addCameraView(area1, camera1);
    }
}
