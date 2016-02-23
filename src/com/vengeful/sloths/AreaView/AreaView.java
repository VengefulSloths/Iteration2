package com.vengeful.sloths.AreaView;


import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.SimpleHexCoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.EntityViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.CenterAvatarLocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.ViewObject;
import com.vengeful.sloths.GameLaunching.LevelFactory;
import com.vengeful.sloths.Utility.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexs on 2/20/2016.
 */
public class AreaView extends JPanel {
    ArrayList<ViewObject> testVOs;
    EntityViewObject testEntity;
    CameraView testCamera;


    public AreaView() {
        this.setBackground(Color.GRAY);
        setPreferredSize(new Dimension(400,400));
        setDoubleBuffered(true);

        CoordinateStrategy cs = new SimpleHexCoordinateStrategy();
        LocationStrategy ls = new CenterAvatarLocationStrategy();

        PersistentViewObjectFactory voFactory = new PlainsPersistantViewObjectFactory(cs, ls);

        testVOs = new ArrayList<>();

        LevelFactory lf = new LevelFactory();
        lf.init("test");
        testCamera = lf.getCameras().getCurrentCameraView();

        testEntity = new EntityViewObject(0, 4, cs, ls, "resources/entities/smasher/");

        final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.NE);
                testEntity.alertMove(1,3,500);
            }
        }, 3, TimeUnit.SECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.NE);
                testEntity.alertMove(2,2,500);
            }
        }, 5, TimeUnit.SECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.NE);
                testEntity.alertMove(3,1,500);
            }
        }, 7, TimeUnit.SECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.NE);
                testEntity.alertMove(4,0,500);
            }
        }, 9, TimeUnit.SECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.SW);
                testEntity.alertMove(1,3,500);
            }
        }, 18, TimeUnit.SECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.SW);
                testEntity.alertMove(2,2,500);
            }
        }, 15, TimeUnit.SECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.SW);
                testEntity.alertMove(3,1,500);
            }
        }, 13, TimeUnit.SECONDS);


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        testCamera.paintComponent(g2d);
        testEntity.paintComponent(g2d);
        g2d.drawString("Hello World!!!", 50, 50);

        Toolkit.getDefaultToolkit().sync();

    }
}
