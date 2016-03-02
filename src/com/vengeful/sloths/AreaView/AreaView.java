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
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;
import com.vengeful.sloths.View.Observers.MapObserver;
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
public class AreaView extends JPanel implements MapObserver{
    private CameraView activeCamera;
    private CameraViewManager cameraViewManager;

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 1000;

    private void setActiveCamera(CameraView cameraView) {
        TemporaryVOCreationVisitor.getInstance().setActiveCameraView(cameraView);
        TemporaryVOCreationVisitor.getInstance().visitAvatar(Avatar.getInstance());
        this.activeCamera = cameraView;
    }

    public AreaView(CameraViewManager cvm) {
        this.setBackground(Color.GRAY);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setDoubleBuffered(true);

        Map.getInstance().registerObserver(this);

        this.cameraViewManager = cvm;
        setActiveCamera(cvm.getCurrentCameraView());
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

    @Override
    public void alertMapAreaChange(MapArea m) {
        activeCamera.cleanUp();
        setActiveCamera(cameraViewManager.getCurrentCameraView());
    }
}
