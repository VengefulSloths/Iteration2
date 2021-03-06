package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.Controllers.ControllerManagers.AggressiveNPCControllerManager;
import com.vengeful.sloths.Controllers.ControllerManagers.PiggyControllerManager;
import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.GameLaunching.LaunchGameTemplate;
import com.vengeful.sloths.GameLaunching.LaunchNewGame;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Entity.AggressiveNPC;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Piggy;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Knuckle;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.OneHandedWeapon;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.TwoHandedWeapon;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.SaveLoad.SaveManager;
import com.vengeful.sloths.Models.Stats.StatAddables.CurrentHealthAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.MovementAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.*;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexs on 2/20/2016.
 */
public class driver {
    public static void main(String ... args) {
        System.out.println("Starting driver");

        LaunchGameTemplate launcher = new LaunchGameTemplate(new LaunchNewGame());
       // launcher.launch();

        //Below here is test code


       // final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        Avatar testAvatar = Avatar.getInstance();
        //MainController cat = MainController.getInstance();
       // Stats moveQuickly = new Stats();

        //moveQuickly.setMovement(45);

        //Avatar.getInstance().setStats(moveQuickly);

        int count = 0;

//        new java.util.Timer().schedule(
//                new java.util.TimerTask() {
//                    @Override
//                    public void run() {
//                        testPiggy.move(Direction.S);
//                        testPiggy.move(Direction.S);
//                        testPiggy.move(Direction.S);
//                        testPiggy.move(Direction.S);
//                        testPiggy.move(Direction.S);
//                        testPiggy.move(Direction.S);
//                    }
//                },
//                4000
//        );



        Piggy testPiggy = new Piggy("Bart", new Stats(new MovementAddable(30)));
        testPiggy.setFacingDirection(Direction.S);
        testPiggy.getStats().add(new BonusHealthAddable(100));
        testPiggy.getStats().setCurrentHealth(100);
        Map.getInstance().addEntity(new Coord(2,2), testPiggy);
        testPiggy.accept(TemporaryVOCreationVisitor.getInstance());
        new PiggyControllerManager(Map.getInstance().getActiveMapArea(), testPiggy);
        Avatar.getInstance().setPet(testPiggy);


        testAvatar.setFacingDirection(Direction.SE);
        testAvatar.getStats().subtract(new CurrentHealthAddable(2));
        //stuff to test enemy controllers
        AggressiveNPC testEnemy =  new AggressiveNPC("xXOG_SwaG_LorD_BlazE_MasteR_420_Xx", new Stats(new BaseStatsAddable(5,5,5,5,30)));
        testEnemy.setTimeToRespawn(150);
        Map.getInstance().addEntity(new Coord(3, 3), testEnemy);
        testEnemy.accept(TemporaryVOCreationVisitor.getInstance());
        new AggressiveNPCControllerManager(Map.getInstance().getActiveMapArea(), testEnemy);

       //dd testEnemy.getStats().subtract(new CurrentHealthAddable(1));
//        SaveManager sm = new SaveManager(Map.getInstance());
//        sm.save("bigSave");

        ViewTime.getInstance().registerAlert(3000, () -> Avatar.getInstance().equip(new OneHandedWeapon("dagger", new StrengthAddable(5), 10)));
        //ViewTime.getInstance().registerAlert(6000, () -> testAvatar.unequip(null));

//
//        int countOffset = count;
//        count = 0;
//        Iterator<Coord> iter2 = HexMath.sortedRing(new Coord(3,4),3);
//        while (iter2.hasNext()) {
//            final Coord current = iter2.next();
//            final int sample = count;
//            executor.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    if (sample == 0) testAvatar.setFacingDirection(Direction.SE);
//                    if (sample == 4) testAvatar.setFacingDirection(Direction.S);
//                    if (sample == 7) testAvatar.setFacingDirection(Direction.SW);
//                    if (sample == 10) testAvatar.setFacingDirection(Direction.NW);
//                    if (sample == 13) testAvatar.setFacingDirection(Direction.N);
//                    if (sample == 16) testAvatar.setFacingDirection(Direction.NE);
//                    testAvatar.move(testAvatar.getFacingDirection());
//                }
//            }, (++count) + countOffset , TimeUnit.SECONDS);
//        }

        System.out.println("Finished with driver");
    }
}
