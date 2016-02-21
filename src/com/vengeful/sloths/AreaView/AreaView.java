package com.vengeful.sloths.AreaView;


import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.SimpleHexCoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.EntityViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.GrassViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.CenterAvatarLocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.ViewObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 2/20/2016.
 */
public class AreaView extends JPanel {
    ArrayList<ViewObject> testVOs;
    ViewObject testEntity;
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
        testEntity = new EntityViewObject(0, 0, cs, ls);
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
