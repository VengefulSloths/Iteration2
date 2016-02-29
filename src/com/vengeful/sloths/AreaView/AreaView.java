package com.vengeful.sloths.AreaView;



import com.vengeful.sloths.AreaView.ViewObjects.*;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.SimpleHexCoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.CenterAvatarLocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.GameLaunching.LevelFactory;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.EntityMapInteractionFactory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;
import com.vengeful.sloths.View.Observers.ProxyEntityObserver;
import com.vengeful.sloths.View.Observers.ProxyObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexs on 2/20/2016.
 */
public class AreaView extends JPanel {
    private CameraView activeCamera;
    private CameraViewManager cameraViewManager;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    private void setActiveCamera(CameraView cameraView) {
        TemporaryVOCreationVisitor.getInstance().setActiveCameraView(cameraView);
        this.activeCamera = cameraView;
    }

    public AreaView(CameraViewManager cvm) {
        this.setBackground(Color.GRAY);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setDoubleBuffered(true);

        this.cameraViewManager = cvm;
        setActiveCamera(cvm.getCurrentCameraView());



        //CoordinateStrategy cs = new SimpleHexCoordinateStrategy();
        //LocationStrategy ls = new CenterAvatarLocationStrategy();

        //testVOs = new ArrayList<>();

        //LevelFactory lf = new LevelFactory();
       // lf.init("test");
        //testCamera = lf.getCameras().getCurrentCameraView();
        //testMap = lf.getMap();
        //TemporaryVOCreationVisitor.getInstance().setActiveCameraView(testCamera);



        //testAvatar = new AvatarViewObject(2, 1, cs, ls, "resources/entities/smasher/");
        //testPiggy = new PiggyViewObject(2, 1, cs, ls, "resources/entities/piggy/");


        //testCamera.addViewObject(testPiggy);
        //testCamera.addAvatar(testAvatar);
        //testPiggy.registerObserver(testCamera);
        //testAvatar.registerObserver(testCamera);

        //EntityViewObject testEntity = new EntityViewObject(6,6,cs,ls,"resources/entities/smasher/");
        //testEntity.registerObserver(testCamera);
        //testCamera.addViewObject(testEntity);

        //AvatarViewFollower.getInstance().bindToViewObject(testAvatar);

        //Avatar avatar = Avatar.getInstance();
        //avatar.setStats(new Stats());
        //testMap.addEntity(new Coord(2,1), avatar);

        //avatar.registerObserver(new ProxyEntityObserver(testAvatar, avatar));

        //EntityMapInteractionFactory.getInstance().init(testMap);

        //ViewTime.getInstance().registerAlert(1000, () -> avatar.move(Direction.S));
        //ViewTime.getInstance().registerAlert(5000, () -> avatar.move(Direction.N));




//        int count = 0;
//        Iterator<Coord> iter = HexMath.sortedRing(new Coord(3,4),3);
//        while (iter.hasNext()) {
//            final Coord current = iter.next();
//            final int sample = count;
//            executor.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    if (sample == 0) testAvatar.alertDirectionChange(Direction.SE);
//                    if (sample == 4) testAvatar.alertDirectionChange(Direction.S);
//                    if (sample == 7) testAvatar.alertDirectionChange(Direction.SW);
//                    if (sample == 10) testAvatar.alertDirectionChange(Direction.NW);
//                    if (sample == 13) testAvatar.alertDirectionChange(Direction.N);
//                    if (sample == 16) testAvatar.alertDirectionChange(Direction.NE);
//                    testAvatar.alertMove(current.getR(),current.getS(),500);
//                }
//            }, (++count), TimeUnit.SECONDS);
//        }
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
//                    if (sample == 0) testAvatar.alertDirectionChange(Direction.SE);
//                    if (sample == 4) testAvatar.alertDirectionChange(Direction.S);
//                    if (sample == 7) testAvatar.alertDirectionChange(Direction.SW);
//                    if (sample == 10) testAvatar.alertDirectionChange(Direction.NW);
//                    if (sample == 13) testAvatar.alertDirectionChange(Direction.N);
//                    if (sample == 16) testAvatar.alertDirectionChange(Direction.NE);
//                    testAvatar.alertMove(current.getR(),current.getS(),500);
//                }
//            }, (++count) + countOffset , TimeUnit.SECONDS);
//        }
//
//        count = 0;
//        Iterator<Coord> iter3 = HexMath.sortedRing(new Coord(4,6),3);
//        while (iter3.hasNext()) {
//            final Coord current = iter3.next();
//            final int sample = count;
//            executor.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    if (sample == 0) testEntity.alertDirectionChange(Direction.SE);
//                    if (sample == 4) testEntity.alertDirectionChange(Direction.S);
//                    if (sample == 7) testEntity.alertDirectionChange(Direction.SW);
//                    if (sample == 10) testEntity.alertDirectionChange(Direction.NW);
//                    if (sample == 13) testEntity.alertDirectionChange(Direction.N);
//                    if (sample == 16) testEntity.alertDirectionChange(Direction.NE);
//                    testEntity.alertMove(current.getR(),current.getS(),500);
//                }
//            }, (++count) , TimeUnit.SECONDS);
//        }


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        activeCamera.paintComponent(g2d);
        //testEntity.paintComponent(g2d);
        g2d.drawString("Hello World!!!", 50, 50);

        //Toolkit.getDefaultToolkit().sync();

    }
}
