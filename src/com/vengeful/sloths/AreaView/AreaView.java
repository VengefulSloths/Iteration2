package com.vengeful.sloths.AreaView;



import com.vengeful.sloths.AreaView.ViewObjects.AvatarViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.SimpleHexCoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.EntityViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.CenterAvatarLocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.PiggyViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.ViewObject;
import com.vengeful.sloths.GameLaunching.LevelFactory;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;

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
    ArrayList<ViewObject> testVOs;
    AvatarViewObject testAvatar;
    PiggyViewObject testPiggy;
    CameraView testCamera;


    public AreaView() {
        this.setBackground(Color.GRAY);
        setPreferredSize(new Dimension(600,600));
        setDoubleBuffered(true);


        CoordinateStrategy cs = new SimpleHexCoordinateStrategy();
        LocationStrategy ls = new CenterAvatarLocationStrategy();

        testVOs = new ArrayList<>();

        LevelFactory lf = new LevelFactory();
        lf.init("test");
        testCamera = lf.getCameras().getCurrentCameraView();
        TemporaryVOCreationVisitor.getInstance().setActiveCameraView(testCamera);


        testAvatar = new AvatarViewObject(2, 1, cs, ls, "resources/entities/smasher/");
        testPiggy = new PiggyViewObject(2, 1, cs, ls, "resources/entities/piggy/");

        testCamera.addViewObject(testPiggy);
        testCamera.addAvatar(testAvatar);
        testPiggy.registerObserver(testCamera);
        testAvatar.registerObserver(testCamera);

        AvatarViewFollower.getInstance().bindToViewObject(testAvatar);

        final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

        int count = 0;
        Iterator<Coord> iter = HexMath.sortedRing(new Coord(3,4),3);
        while (iter.hasNext()) {
            final Coord current = iter.next();
            final int sample = count;
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    if (sample == 0) testAvatar.alertDirectionChange(Direction.SE);
                    if (sample == 4) testAvatar.alertDirectionChange(Direction.S);
                    if (sample == 7) testAvatar.alertDirectionChange(Direction.SW);
                    if (sample == 10) testAvatar.alertDirectionChange(Direction.NW);
                    if (sample == 13) testAvatar.alertDirectionChange(Direction.N);
                    if (sample == 16) testAvatar.alertDirectionChange(Direction.NE);
                    testAvatar.alertMove(current.getR(),current.getS(),500);
                }
            }, (++count), TimeUnit.SECONDS);
        }

        int countOffset = count;
        count = 0;
        Iterator<Coord> iter2 = HexMath.sortedRing(new Coord(3,4),3);
        while (iter2.hasNext()) {
            final Coord current = iter2.next();
            final int sample = count;
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    if (sample == 0) testAvatar.alertDirectionChange(Direction.SE);
                    if (sample == 4) testAvatar.alertDirectionChange(Direction.S);
                    if (sample == 7) testAvatar.alertDirectionChange(Direction.SW);
                    if (sample == 10) testAvatar.alertDirectionChange(Direction.NW);
                    if (sample == 13) testAvatar.alertDirectionChange(Direction.N);
                    if (sample == 16) testAvatar.alertDirectionChange(Direction.NE);
                    testAvatar.alertMove(current.getR(),current.getS(),500);
                }
            }, (++count) + countOffset , TimeUnit.SECONDS);
        }


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        testCamera.paintComponent(g2d);
        //testEntity.paintComponent(g2d);
        g2d.drawString("Hello World!!!", 50, 50);

        //Toolkit.getDefaultToolkit().sync();

    }
}
