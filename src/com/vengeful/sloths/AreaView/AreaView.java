package com.vengeful.sloths.AreaView;



import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.SimpleHexCoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.EntityViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.CenterAvatarLocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.ViewObject;
import com.vengeful.sloths.GameLaunching.LevelFactory;
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

        testEntity = new EntityViewObject(2, 0, cs, ls, "resources/entities/smasher/");
        final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

        int count = 0;
        Iterator<Coord> iter = HexMath.sortedRing(new Coord(3,3),3);
        while (iter.hasNext()) {
            final Coord current = iter.next();
            final int sample = count;
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    if (sample == 0) testEntity.alertDirectionChange(Direction.SE);
                    if (sample == 4) testEntity.alertDirectionChange(Direction.S);
                    if (sample == 7) testEntity.alertDirectionChange(Direction.SW);
                    if (sample == 10) testEntity.alertDirectionChange(Direction.NW);
                    if (sample == 13) testEntity.alertDirectionChange(Direction.N);
                    if (sample == 16) testEntity.alertDirectionChange(Direction.NE);
                    testEntity.alertMove(current.getR(),current.getS(),500);
                }
            }, (++count), TimeUnit.SECONDS);
        }


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
