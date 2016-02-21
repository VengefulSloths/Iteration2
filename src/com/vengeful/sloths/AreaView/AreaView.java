package com.vengeful.sloths.AreaView;


import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.SimpleHexCoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.EntityViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.GrassViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.CenterAvatarLocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.ViewObject;
import com.vengeful.sloths.Model.Entity.Entity;
import com.vengeful.sloths.Utility.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexs on 2/20/2016.
 */
public class AreaView extends JPanel {
    ArrayList<ViewObject> testVOs;
    EntityViewObject testEntity;
    public AreaView() {
        this.setBackground(Color.GRAY);
        setPreferredSize(new Dimension(400,400));
        setDoubleBuffered(true);

        CoordinateStrategy cs = new SimpleHexCoordinateStrategy();
        LocationStrategy ls = new CenterAvatarLocationStrategy();

        testVOs = new ArrayList<>();

        for (int i = 0;i<10; i++) {
            for (int j=0; j<10; j++) {
                testVOs.add(new GrassViewObject(i,j, cs, ls));
            }
        }
        testEntity = new EntityViewObject(1, 3, cs, ls, "resources/entities/smasher/");

        final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.SE);
                testEntity.alertMove(2,3,1000);
            }
        }, 3, TimeUnit.SECONDS);

        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.S);
                testEntity.alertMove(2,4,1000);
            }
        }, 4, TimeUnit.SECONDS);

        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.SW);
                testEntity.alertMove(1,5,1000);
            }
        }, 5, TimeUnit.SECONDS);

        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.N);
                testEntity.alertMove(1,4,1000);
            }
        }, 6, TimeUnit.SECONDS);

        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.NW);
                testEntity.alertMove(0,4,1000);
            }
        }, 7, TimeUnit.SECONDS);

        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.NE);
                testEntity.alertMove(1,3,1000);
            }
        }, 8, TimeUnit.SECONDS);

        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.S);
                testEntity.alertMove(1,4,300);
            }
        }, 9000, TimeUnit.MILLISECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.S);
                testEntity.alertMove(1,5,300);
            }
        }, 9300, TimeUnit.MILLISECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.S);
                testEntity.alertMove(1,6,300);
            }
        }, 9600, TimeUnit.MILLISECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.S);
                testEntity.alertMove(1,7,300);
            }
        }, 9900, TimeUnit.MILLISECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertDirectionChange(Direction.NE);
                testEntity.alertMove(2,6,300);
            }
        }, 10200, TimeUnit.MILLISECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertMove(3,5,300);
            }
        }, 10500, TimeUnit.MILLISECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertMove(4,4,300);
            }
        }, 10800, TimeUnit.MILLISECONDS);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                testEntity.alertMove(5,3,300);
            }
        }, 11100, TimeUnit.MILLISECONDS);
    }






    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (ViewObject vo: testVOs) {
            vo.paintComponent(g2d);
        }
        testEntity.paintComponent(g2d);
        g2d.drawString("Hello World!!!", 50, 50);

        Toolkit.getDefaultToolkit().sync();

    }
}
