package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.AreaView.ViewObjects.MovingViewObject;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alexs on 2/25/2016.
 */
public class ParallaxBackground extends JComponent implements MovingVOObserver{
    private DynamicImage background;
    private int speed = 5;

    private int xOld=0;
    private int yOld=0;

    private int xNew;
    private int yNew;

    private long startTime =0;
    private long duration = 0;

    public ParallaxBackground(String resourcePath, MovingViewObject subject) {
        subject.registerObserver(this);
        xNew = convertX(subject.getR());
        yNew = convertY((subject.getR() + subject.getS()*2));
        background = DynamicImageFactory.getInstance().loadDynamicImage(resourcePath);
    }
    public void paintComponent(Graphics2D g) {
        long time = ViewTime.getInstance().getCurrentTimeMilli();
        if (time < startTime + duration) {
            double ratio = (double)(time - startTime)/(double)duration;
            g.drawImage(background.getImage(),
                    -200 + (int)(ratio*xNew + (1-ratio)*xOld),
                    -200 + (int)(ratio*yNew + (1-ratio)*yOld),
                    this);
        } else {
            g.drawImage(background.getImage(),
                    -200 + xNew,
                    -200 + yNew,
                    this);
        }
    }

    private int convertX(int x) {
        return -x*10;
    }

    private int convertY(int y) {
        return -y*7;
    }
    @Override
    public void alertMove(int srcR, int srcS, int destR, int destS, long duration, MovingViewObject subject) {
        xOld = xNew;
        yOld = yNew;
        xNew = convertX(destR);
        yNew = convertY((destR + destS*2));
        this.duration = duration;
        startTime = ViewTime.getInstance().getCurrentTimeMilli();
    }
}
