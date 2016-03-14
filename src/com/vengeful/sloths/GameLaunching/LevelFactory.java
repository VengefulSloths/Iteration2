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
import com.vengeful.sloths.Models.DialogueTrade.TradeDialogContainer;
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



    public void createDemoMap(){
        this.cameras = new CameraViewManager();
        //call create each area
        MapArea town = createAreaTown();

        MapArea summonerArea = createSummonerArea();

        MapArea rescue = createArea2();
        MapArea area3 = createArea3();

        //link areas with teleport tiles
//        TeleportSenderTile tst = new TeleportSenderTile(area3, )
//        town.addTile(new Coord(2,3), tst);

        TeleportDestinationTile rescueDstTile = new TeleportDestinationTile(new Coord(1,1));
        rescue.addTile(new Coord(3,3), rescueDstTile);
        TeleportSenderTile tst2 = new TeleportSenderTile(rescue, rescueDstTile);
        town.addTile(new Coord(12,4), tst2);

        TeleportDestinationTile townDstFromRescue = new TeleportDestinationTile(new Coord(10,4));
        town.addTile(townDstFromRescue.getLocation(), townDstFromRescue);
        TeleportSenderTile tst3 = new TeleportSenderTile(town, townDstFromRescue);
        rescue.addTile(new Coord(1,1), tst3);

        TeleportDestinationTile areaThreeFromTown = new TeleportDestinationTile(new Coord(2,1));
        area3.addTile(new Coord(2,1), areaThreeFromTown);
        TeleportSenderTile toArea3FromTown = new TeleportSenderTile(area3, areaThreeFromTown);
        town.addTile(new Coord(2,3), toArea3FromTown);

        TeleportDestinationTile townFromAreaThree = new TeleportDestinationTile(new Coord(4,1));
        town.addTile(new Coord(4,1), townFromAreaThree);
        TeleportSenderTile areaThreeToTown = new TeleportSenderTile(town, townFromAreaThree);
        area3.addTile(new Coord(1,1), areaThreeToTown);

        TeleportDestinationTile summonerAreaFromTown = new TeleportDestinationTile(new Coord(3,3));
        summonerArea.addTile(new Coord(3,3), summonerAreaFromTown);
        TeleportSenderTile townToSummonerArea = new TeleportSenderTile(summonerArea, summonerAreaFromTown);
        town.addTile(new Coord(10,1), townToSummonerArea);

        TeleportSenderTile toTownFromSummonerArea = new TeleportSenderTile(town, townDstFromRescue);
        summonerArea.addTile(new Coord(1,5), toTownFromSummonerArea);



        MapArea[] areas = new MapArea[4];
        areas[0] = town;
        areas[1] = rescue;
        areas[2] = area3;
        areas[3] = summonerArea;

        this.map = Map.getInstance();
        this.map.setMapAreas(areas);
        this.map.setRespawnPoint(new Location(town, new Coord(9,1)));
        this.map.setRespawnPoint(new Location(rescue, new Coord(3,3)));
        this.map.setRespawnPoint(new Location(area3, new Coord(2,4))); //smasher
        this.map.setRespawnPoint(new Location(summonerArea, new Coord(3,3)));
        this.map.setActiveMapArea(town);

        this.spawnPoint = new Coord(9,1);
    }


    public MapArea createAreaTown(){
        MapArea town = new MapArea(14,12);
        town.setName("town");
        for (int i=2;i<13;i++) {
            for (int j = 1; j < 11; j++) {
                town.addTile(new Coord(i,j), new  Tile( j > 8 ? new Water() : new Grass()));
            }
        }
        for(int j = 1; j != 10; ++j){
            town.addTile(new Coord(1,j), new Tile(new Mountain()));
        }
        for(int j = 1; j != 10; ++j){
            town.addTile(new Coord(11,j), new Tile(new DummyTerrain()));
            town.addTile(new Coord(12,j), new Tile(new DummyTerrain()));
        }
//        for(int i = 1; i != 13; ++i){
//            town.addTile(new Coord(i,1), new Tile(i< 7 ? new Mountain() : new Grass()));
//        }
        town.getTile(new Coord(2,2)).setTerrain(new Mountain());
        town.getTile(new Coord(2,4)).setTerrain(new Mountain());
        town.getTile(new Coord(2,5)).setTerrain(new Mountain());
        town.getTile(new Coord(1,5)).setTerrain(new DummyTerrain());
        town.getTile(new Coord(1,4)).setTerrain(new DummyTerrain());
        town.getTile(new Coord(11,4)).setTerrain(new Grass());
//        town.getTile(new Coord(12,4)).setTerrain(new Grass());
        for(int i = 3; i != 6; ++i){
            for(int j = 3; j !=6; ++j ){
                town.getTile(new Coord(i,j)).setTerrain(new DummyTerrain());
            }
        }
        return town;
    }



    public MapArea createArea2() {

        int rows = 19;
        int cols = 19;

        MapArea rescue = new MapArea(rows, cols);
        rescue.setName("Rescue Mission");

        for(int i = 1; i<rows; i++) {
            for (int j=1; j<cols; j++) {
                rescue.addTile(new Coord(i,j), new Tile(new Grass()));
            }
        }

        for(int i=7; i<14; i++) {
            for(int j=7; j<14;j++){
                rescue.getTile(new Coord(i,j)).setTerrain(new DummyTerrain());
            }
        }

        for(int i=9; i<11; i++) {
            rescue.getTile(new Coord(10,i)).setTerrain(new Grass());
            rescue.getTile(new Coord(11,i)).setTerrain(new Grass());
        }

        rescue.getTile(new Coord(9,9)).setTerrain(new Mountain());
        rescue.getTile(new Coord(2,6)).setTerrain(new Mountain());
        rescue.getTile(new Coord(7,2)).setTerrain(new Mountain());
        rescue.getTile(new Coord(8,4)).setTerrain(new Mountain());
        rescue.getTile(new Coord(14,4)).setTerrain(new Mountain());
        rescue.getTile(new Coord(12,5)).setTerrain(new Mountain());
        rescue.getTile(new Coord(17,7)).setTerrain(new Mountain());
        rescue.getTile(new Coord(14,1)).setTerrain(new Mountain());
        rescue.getTile(new Coord(12,1)).setTerrain(new Mountain());
        rescue.getTile(new Coord(9,5)).setTerrain(new Mountain());
        rescue.getTile(new Coord(1,7)).setTerrain(new Water());
        rescue.getTile(new Coord(2,7)).setTerrain(new Water());
        rescue.getTile(new Coord(3,7)).setTerrain(new Water());
        rescue.getTile(new Coord(2,6)).setTerrain(new Water());
        rescue.getTile(new Coord(3,6)).setTerrain(new Water());
        rescue.getTile(new Coord(3,14)).setTerrain(new Water());
        rescue.getTile(new Coord(3,15)).setTerrain(new Water());
        rescue.getTile(new Coord(3,16)).setTerrain(new Water());
        rescue.getTile(new Coord(4,15)).setTerrain(new Water());
        rescue.getTile(new Coord(4,16)).setTerrain(new Water());
        rescue.getTile(new Coord(16,8)).setTerrain(new Water());
        rescue.getTile(new Coord(16,7)).setTerrain(new Water());
        rescue.getTile(new Coord(16,6)).setTerrain(new Water());
        rescue.getTile(new Coord(15,7)).setTerrain(new Water());
        rescue.getTile(new Coord(15,6)).setTerrain(new Water());
        rescue.getTile(new Coord(5,17)).setTerrain(new Mountain());
        rescue.getTile(new Coord(3,8)).setTerrain(new Mountain());
        rescue.getTile(new Coord(4,12)).setTerrain(new Mountain());
        rescue.getTile(new Coord(2,9)).setTerrain(new Mountain());
        rescue.getTile(new Coord(14,14)).setTerrain(new Mountain());
        rescue.getTile(new Coord(15,12)).setTerrain(new Mountain());
        rescue.getTile(new Coord(11,16)).setTerrain(new Mountain());


        //rescue.getTile(new Coord(10,10)).setTerrain(new Grass());
        //rescue.getTile(new Coord(10,11)).setTerrain(new Grass());


        return rescue;
    }

    public MapArea createArea3() {
        int rows = 12;
        int cols = 12;

        MapArea area3 = new MapArea(rows, cols);
        area3.setName("Area 3");

        for(int i = 1; i<rows; i++) {
            for (int j=1; j<cols; j++) {
                area3.addTile(new Coord(i,j), new Tile(new Grass()));
            }
        }

        for(int i=4; i<6; i++) {
            for(int j=4; j<6;j++){
                area3.getTile(new Coord(i,j)).setTerrain(new DummyTerrain());
            }
        }


        area3.getTile(new Coord(3,2)).setTerrain(new Mountain());
        area3.getTile(new Coord(7,2)).setTerrain(new Mountain());
        area3.getTile(new Coord(9,9)).setTerrain(new Mountain());
        area3.getTile(new Coord(11,10)).setTerrain(new Mountain());
        area3.getTile(new Coord(9,2)).setTerrain(new Water());
        area3.getTile(new Coord(9,3)).setTerrain(new Water());
        area3.getTile(new Coord(9,4)).setTerrain(new Water());
        area3.getTile(new Coord(8,3)).setTerrain(new Water());
        area3.getTile(new Coord(8,2)).setTerrain(new Water());

        area3.getTile(new Coord(3,8)).setTerrain(new Water());
        area3.getTile(new Coord(3,9)).setTerrain(new Water());
        area3.getTile(new Coord(3,10)).setTerrain(new Water());
        area3.getTile(new Coord(4,9)).setTerrain(new Water());


        return area3;

    }


    public void populateDemoMap(){
        MapArea[] areas = Map.getInstance().getMapAreas();
        MapArea town = areas[0];
        MapArea rescue = areas[1];
        MapArea area3 = areas[2];
        MapArea summonerArea = areas[3];

        populateSummonerArea(summonerArea);
        populateAreaTown(town);
        populateRescueMap(rescue);
        populateArea3Map(area3);


        //CAMERAS
        CameraView camera1 = new PlainsCameraView();
        CameraView camera2 = new PlainsCameraView();
        CameraView camera3 = new PlainsCameraView();
        CameraView camera4 = new PlainsCameraView();
        camera1.init(town);
        camera2.init(rescue);
        camera3.init(area3);
        camera4.init(summonerArea);


        cameras.addCameraView(town, camera1);
        cameras.addCameraView(rescue, camera2);
        cameras.addCameraView(area3, camera3);
        cameras.addCameraView(summonerArea, camera4);




    }

    public void populateArea3Map(MapArea area3) {
        area3.getTile(new Coord(5,2)).addObstacle(new Obstacle(new Coord(5,2)));

        AggressiveNPC agroNPC3 = new AggressiveNPC("Mons", new Stats(new BaseStatsAddable(2,2,2,2,2)));
        area3.getTile(new Coord(3,4)).addEntity(agroNPC3);
        agroNPC3.setLocation(new Coord(3,4));
        new AggressiveNPCControllerManager(area3, agroNPC3);

        AggressiveNPC agroNPC1 = new AggressiveNPC("Mons", new Stats(new BaseStatsAddable(2,2,2,2,2)));
        area3.getTile(new Coord(10,10)).addEntity(agroNPC1);
        agroNPC1.setLocation(new Coord(10,10));
        new AggressiveNPCControllerManager(area3, agroNPC1);

        AggressiveNPC agroNPC2 = new AggressiveNPC("Mons", new Stats(new BaseStatsAddable(2,2,2,2,2)));
        area3.getTile(new Coord(11,3)).addEntity(agroNPC2);
        agroNPC2.setLocation(new Coord(11,3));
        new AggressiveNPCControllerManager(area3, agroNPC2);

        agroNPC3.getInventory().addItem(new TwoHandedWeapon("Dragon 2H", new StrengthAddable(5), 5));
        agroNPC2.getInventory().addItem(new Knuckle("Katar", new StrengthAddable(5), 5));

//        CameraView camera1 = new PlainsCameraView();
//        camera1.init(area3);
//        cameras.addCameraView(area3, camera1);

    }

    public void populateRescueMap(MapArea rescue) {
        rescue.getTile(new Coord(5,2)).addObstacle(new Obstacle(new Coord(5,2)));
        Trap t = new Trap(new Coord(5,1), 2);
        t.setItemName("trap");
        rescue.getTile(new Coord(5,1)).addTrap(t);


        NonAggressiveNPC Jana = new NonAggressiveNPC("Jana", new Stats( new BaseStatsAddable(0,0,0,10,20)));
        rescue.getTile(new Coord(16,16)).addEntity(Jana);
        Jana.setLocation(new Coord(16,16));
        Jana.setShirt("pink_shirt");
        new NonAggressiveNPCControllerManager(rescue, Jana, Direction.S,1);
        TradeDialogContainer rescueFriend = new TradeDialogContainer(Jana);
        rescueFriend.appendDialog("Thank you thank you! You saved me from these monsters");
        rescueFriend.appendDialog("Here, have a tasty blue strength potion!");
        Potion p = new Potion("Blue Potion", new StrengthAddable(5));
        p.setValue(0);
        Jana.getInventory().addItem(p);

        Jana.setDialogContainer(rescueFriend);

        AggressiveNPC agroNPC1 = new AggressiveNPC("Mons", new Stats(new BaseStatsAddable(2,2,2,2,2)));
        rescue.getTile(new Coord(10,10)).addEntity(agroNPC1);
        agroNPC1.setLocation(new Coord(10,10));
        new AggressiveNPCControllerManager(rescue, agroNPC1);

        AggressiveNPC agroNPC2 = new AggressiveNPC("Mons", new Stats(new BaseStatsAddable(2,2,2,2,2)));
        rescue.getTile(new Coord(13,5)).addEntity(agroNPC2);
        agroNPC2.setLocation(new Coord(13,5));
        new AggressiveNPCControllerManager(rescue, agroNPC2);

        AggressiveNPC agroNPC3 = new AggressiveNPC("Mons", new Stats(new BaseStatsAddable(2,2,2,2,2)));
        rescue.getTile(new Coord(16,12)).addEntity(agroNPC3);
        agroNPC3.setLocation(new Coord(16,12));
        new AggressiveNPCControllerManager(rescue, agroNPC3);


        AggressiveNPC agroNPC4 = new AggressiveNPC("Mons", new Stats(new BaseStatsAddable(2,2,2,2,2)));
        rescue.getTile(new Coord(3,18)).addEntity(agroNPC4);
        agroNPC4.setLocation(new Coord(3,18));
        new AggressiveNPCControllerManager(rescue, agroNPC4);

//        CameraView camera1 = new PlainsCameraView();
//        camera1.init(rescue);
//        cameras.addCameraView(rescue, camera1);

    }

    public void populateAreaTown(MapArea town){
        //ITEMS AND QUESTS
        town.getTile(new Coord(3,2)).addObstacle(new Obstacle(new Coord(3,2)));
        Quest quest1_b = new DoDestroyObstacleQuest(new Coord(3,2));
        Quest quest1_a = new HasItemQuest(quest1_b, "Blue Potion");
        town.getTile(new Coord(4,2)).addInteractiveItem(new InteractiveItem(quest1_a, new Coord(4,2)));

        //CAMERAS
//        CameraView camera1 = new PlainsCameraView();
//        camera1.init(town);
//        cameras.addCameraView(town, camera1);

        //NPCS
        NonAggressiveNPC Dan = new NonAggressiveNPC("Dan", new Stats( new BaseStatsAddable(0,0,0,10,20)));
        town.getTile(new Coord(6,4)).addEntity(Dan);
        Dan.setLocation(new Coord(6,4));
        new NonAggressiveNPCControllerManager(town, Dan, Direction.S, 1);
        TerminalDialogContainer saveFriend = new TerminalDialogContainer(Dan.getName());
        saveFriend.appendDialog("Cyclop's have kidnapped my beautiful wife! Please go rescue her!");
        saveFriend.appendDialog("Those cyclops' are tough though. You should probably go get some gear");
        Dan.setDialogContainer(saveFriend);


        NonAggressiveNPC Richie = new NonAggressiveNPC("Richie", new Stats( new BaseStatsAddable(0,0,0,10,20)));
        town.getTile(new Coord(2,1)).addEntity(Richie);
        Richie.setLocation(new Coord(2,1));
        Inventory RichieInventory = Richie.getInventory();
        RichieInventory.addGold(new Gold(1000));
        //@ // TODO: 3/14/16 ADD LOTS OF GOOD ITMES HERE
        new NonAggressiveNPCControllerManager(town, Richie, Direction.SE, 1);

        NonAggressiveNPC BobSmith = new NonAggressiveNPC("BobSmith", new Stats( new BaseStatsAddable(0,0,0,10,20)));
        Inventory BobSmithInventoryInv = BobSmith.getInventory();
        BobSmithInventoryInv.addItem(new Bow("Bow", new AgilityAddable(1),2));
        BobSmithInventoryInv.addItem(new OneHandedWeapon("Dagger", new StrengthAddable(1),2));
        BobSmithInventoryInv.addItem(new TwoHandedWeapon("Mystical 2H", new StrengthAddable(1),3));
        BobSmithInventoryInv.addGold(new Gold(100));
        town.getTile(new Coord(9,7)).addEntity(BobSmith);
        BobSmith.setLocation(new Coord(9,7));
        new NonAggressiveNPCControllerManager(town, BobSmith, Direction.NW, 1);
        TradeDialogContainer buyEquipment = new TradeDialogContainer(BobSmith);
        buyEquipment.appendDialog("I'm the finest blacksmith in town.");
        buyEquipment.appendDialog("Have a look at my wares.");
        BobSmith.setDialogContainer(buyEquipment);

        Piggy piggy = new Piggy("Perro The Pig", new Stats( new BaseStatsAddable(1,0,0,10,20) ) );
        piggy.setDead(true);
        new PiggyControllerManager(Map.getInstance().getActiveMapArea(), piggy);
        Avatar.getInstance().initialSetPet(piggy);

        NonAggressiveNPC Pete = new NonAggressiveNPC("Pete", new Stats( new BaseStatsAddable(0,0,0,10,20)));
        Inventory PeteInventory = Pete.getInventory();
        PiggyTotem pt = new PiggyTotem("Piggy Totem", piggy);
        pt.setValue(1);
        PeteInventory.addItem(pt);
        PeteInventory.addGold(new Gold(30));
        town.getTile(new Coord(3,7)).addEntity(Pete);
        Pete.setLocation(new Coord(3,7));
        new NonAggressiveNPCControllerManager(town, Pete, Direction.NW, 1);
        TradeDialogContainer buyPet = new TradeDialogContainer(Pete);
        buyPet.appendDialog("It's always good to have a pet");
        Pete.setDialogContainer(buyPet);

//        NonAggressiveNPC testNPC = new NonAggressiveNPC("greg", new Stats( new BaseStatsAddable(0,0,0,10,20)));
//        town.getTile(new Coord(8,3)).addEntity(testNPC);
//        testNPC.setLocation(new Coord(8,3));
//        new NonAggressiveNPCControllerManager(town, testNPC, Direction.S,2);
//
//        NonAggressiveNPC testNPC2 = new NonAggressiveNPC("Bob", new Stats( new BaseStatsAddable(0,0,0,10,20)));
//        town.getTile(new Coord(2,1)).addEntity(testNPC2);
//        testNPC2.setLocation(new Coord(2,1));
//        new NonAggressiveNPCControllerManager(town, testNPC, Direction.S,2);


//        town.getTile(new Coord(6,2)).addAreaEffect(new TakeDamageAE(2));
//        Ability hot = AbilityFactory.getInstance().createHealOverTime(Avatar.getInstance());
//        town.getTile(new Coord(5,2)).addTakeableItem(new TakeableItem("Rejuvination", new AbilityItem(hot), new Coord(5,2)));
    }


    public MapArea createSummonerArea(){

        MapArea summonerArea = new MapArea(20, 20);
        summonerArea.setName("entity field");

        for(int i = 1; i < 19; i++){
            for(int j = 1; j < 19; j++){
                summonerArea.addTile(new Coord(i, j), new Tile(new Grass()));
            }
        }

        Iterator<Coord> lake = HexMath.hexagon(new Coord(12, 11), 2);
        while(lake.hasNext()){
            summonerArea.getTile(lake.next()).setTerrain(new Water());
        }

        summonerArea.getTile(new Coord(1,1)).setTerrain(new Mountain());
        summonerArea.getTile(new Coord(1,2)).setTerrain(new Mountain());
        summonerArea.getTile(new Coord(2,1)).setTerrain(new Mountain());
        summonerArea.getTile(new Coord(2,2)).setTerrain(new Mountain());
        summonerArea.getTile(new Coord(2,3)).setTerrain(new Mountain());
        summonerArea.getTile(new Coord(1,3)).setTerrain(new Mountain());
        summonerArea.getTile(new Coord(1,4)).setTerrain(new Mountain());
        summonerArea.getTile(new Coord(3,1)).setTerrain(new Mountain());


        return summonerArea;
    }

    public void populateSummonerArea(MapArea summonerArea){
        //AE
        summonerArea.getTile(new Coord(3,2)).addAreaEffect(new LevelUpAE(5));
        summonerArea.getTile(new Coord(4,1)).addAreaEffect(new LevelUpAE(5));
        summonerArea.getTile(new Coord(4,2)).addAreaEffect(new LevelUpAE(5));
        summonerArea.getTile(new Coord(5,1)).addAreaEffect(new LevelUpAE(5));


        //ITEMS
        summonerArea.getTile(new Coord(4,3)).addTakeableItem(new TakeableItem("Blue Potion", new Potion("Blue Potion",new BaseStatsAddable(10,0,0,0,0)), new Coord(4,3)));
        summonerArea.getTile(new Coord(12,1)).addTakeableItem(new TakeableItem("Blue Potion", new Potion("Blue Potion",new BaseStatsAddable(10,0,0,0,0)), new Coord(12,1)));
        summonerArea.getTile(new Coord(16,8)).addTakeableItem(new TakeableItem("Blue Potion", new Potion("Blue Potion",new BaseStatsAddable(10,0,0,0,0)), new Coord(16,8)));


        summonerArea.getTile(new Coord(11,3)).addTakeableItem(new TakeableItem("Water Staff", new Staff("Water Staff", new IntellectAddable(5), 10), new Coord(11,3)));
        summonerArea.getTile(new Coord(8,14)).addTakeableItem(new TakeableItem("Obsidian Staff", new Staff("Obsidian Staff", new IntellectAddable(5), 10), new Coord(8,14)));
        summonerArea.getTile(new Coord(11,9)).addTakeableItem(new TakeableItem("Dragon Staff", new Staff("Dragon Staff", new IntellectAddable(5), 10), new Coord(11,9)));



        //NPCS
        AggressiveNPC npc1 = new AggressiveNPC("npc1", new Stats( new BaseStatsAddable(5,0,0,0,30)));
        summonerArea.getTile(new Coord(18,1)).addEntity(npc1);
        npc1.setLocation(new Coord(18,1));
        npc1.equip(new TwoHandedWeapon("Iron 2H", new StrengthAddable(1), 1));
        new AggressiveNPCControllerManager(summonerArea, npc1);

        NonAggressiveNPC npc2 = new NonAggressiveNPC("npc2", new Stats( new BaseStatsAddable(5,0,0,0,30)));
        summonerArea.getTile(new Coord(4,12)).addEntity(npc2);
        npc2.setLocation(new Coord(4,12));
        new NonAggressiveNPCControllerManager(summonerArea, npc2, Direction.S, 1);


        NonAggressiveNPC npc3 = new NonAggressiveNPC("npc3", new Stats( new BaseStatsAddable(5,0,0,0,30)));
        summonerArea.getTile(new Coord(3,16)).addEntity(npc3);
        npc3.setLocation(new Coord(3,16));
        new NonAggressiveNPCControllerManager(summonerArea, npc3, Direction.N, 1);


        AggressiveNPC npc4 = new AggressiveNPC("npc4", new Stats( new BaseStatsAddable(5,0,0,0,30)));
        summonerArea.getTile(new Coord(17,17)).addEntity(npc4);
        npc4.setLocation(new Coord(17,17));
        npc4.equip(new TwoHandedWeapon("dagger", new StrengthAddable(1), 1));
        new AggressiveNPCControllerManager(summonerArea, npc4);


        NonAggressiveNPC testNPC = new NonAggressiveNPC("greg", new Stats( new BaseStatsAddable(0,0,0,10,20)));
        summonerArea.getTile(new Coord(8,3)).addEntity(testNPC);
        testNPC.setLocation(new Coord(8,3));
        new NonAggressiveNPCControllerManager(summonerArea, testNPC, Direction.S, 1);

        /*
        NonAggressiveNPC testNPC2 = new NonAggressiveNPC("Bob", new Stats( new BaseStatsAddable(0,0,0,10,20)));
        summonerArea.getTile(new Coord(2,1)).addEntity(testNPC2);
        testNPC2.setLocation(new Coord(2,1));
        new NonAggressiveNPCControllerManager(summonerArea, testNPC, Direction.S);
        */


        //ABILITIES TODO
        //NPC fall asleep, flame thrower, explosion
        //summonerArea.getTile(new Coord(8,5)).addTakeableItem(new TakeableItem("Fire Ball", new AbilityItem(fireBallAbility), new Coord(3,3)));
        



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


        area2.getTile(new Coord(6,5)).addTakeableItem(new TakeableItem("Water Staff", new Staff("Water Staff", new IntellectAddable(5), 10), new Coord(6,5)));
        area2.getTile(new Coord(6,7)).addTakeableItem(new TakeableItem("Obsidian Staff", new Staff("Obsidian Staff", new IntellectAddable(5), 10), new Coord(6,3)));
        area2.getTile(new Coord(7,6)).addTakeableItem(new TakeableItem("Dragon Staff", new Staff("Dragon Staff", new IntellectAddable(5), 10), new Coord(7,6)));
        area2.getTile(new Coord(3,3)).addTakeableItem(new TakeableItem("Bow", new Bow("Bow", new IntellectAddable(5), 10), new Coord(3,3)));


        area1.getTile(new Coord(2,2)).addTakeableItem(new TakeableItem("Red Potion", new Potion("Red Potion",new BaseStatsAddable(5,0,0,0,0)), new Coord(2,2)));
        area1.getTile(new Coord(11,10)).addTakeableItem(new TakeableItem("Blue Potion", new Potion("Blue Potion",new BaseStatsAddable(0,0,5,0,0)), new Coord(11,10)));
//        area2.getTile(new Coord(2,2)).addTakeableItem(new TakeableItem("redPotion", new Potion("redPotion",new BaseStatsAddable(5,0,0,0,0)), new Coord(2,2)));


        area1.getTile(new Coord(9, 2)).addTakeableItem(new TakeableItem("Bow", new Bow("Bow", new AgilityAddable(3), 3), new Coord(9,2)));

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
        new NonAggressiveNPCControllerManager(area2, testNPC, Direction.S, 2);
//
//        NonAggressiveNPC testNPC = new NonAggressiveNPC("greg", new Stats( new BaseStatsAddable(0,0,0,10,20)));
//        area2.getTile(new Coord(5,5)).addEntity(testNPC);
//        testNPC.setLocation(new Coord(5,5));
//        new NonAggressiveNPCControllerManager(area2, testNPC, Direction.S);



        //stuff to test enemy controllers

//        AggressiveNPC testEnemy =  new AggressiveNPC("xXOG_SwaG_LorD_BlazE_MasteR_420_Xx", new Stats(new BaseStatsAddable(0,0,0,15,30)));
////        testEnemy.getInventory().addItem(p);
////        testEnemy.getInventory().addItem(p);
////        testEnemy.getInventory().addItem(p);
////        testEnemy.getInventory().addItem(p);
////        testEnemy.getInventory().addItem(p);
//        area2.getTile(new Coord(3,3)).addEntity(testEnemy);
//        testEnemy.setLocation(new Coord(3,3));
//        testEnemy.equip(new TwoHandedWeapon("Iron 2H", new StrengthAddable(1), 1));
//
//        //testEnemy.accept(TemporaryVOCreationVisitor.getInstance());
//        new AggressiveNPCControllerManager(area2, testEnemy);
//        testEnemy.setShirt("pink_shirt");
//        testEnemy.getStats().subtract(new CurrentHealthAddable(1));
        camera1.addDecal(new Coord(2,2), "resources/terrain/cracked_sand.xml" );
        camera2.addDecal(new Coord(2,2), "resources/terrain/cracked_sand.xml" );

//        map.getActiveMapArea().getTile(spawnPoint).addEntity(Avatar.getInstance());
        cameras.addCameraView(area2, camera2);
        cameras.addCameraView(area1, camera1);
    }
}
