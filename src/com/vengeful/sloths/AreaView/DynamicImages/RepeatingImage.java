package com.vengeful.sloths.AreaView.DynamicImages;

import com.vengeful.sloths.AreaView.ViewTime;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 2/27/2016.
 */
public class RepeatingImage extends DynamicImage {
    private long duration;
    private ArrayList<Image> animatedImages;
    private ArrayList<String> paths  = new ArrayList<>();
    public RepeatingImage(int width, int height, PositioningStrategy positioningStrategy, long duration, ArrayList<String> imagePaths) {
        super(width, height, positioningStrategy);
        this.duration = duration;
        animatedImages = new ArrayList<>();
        for (String imagePath: imagePaths) {
            paths.add(imagePath);
            animatedImages.add((new ImageIcon(imagePath)).getImage());
        }
    }
    @Override
    public Image getImage() {
        float offsetTime = ViewTime.getInstance().getCurrentTimeMilli() % duration;
        return animatedImages.get((int)((offsetTime/duration)*animatedImages.size()));
    }

    @Override
    public String getCurrentImagePath() {
        float offsetTime = ViewTime.getInstance().getCurrentTimeMilli() % duration;
        return paths.get((int)((offsetTime/duration)*animatedImages.size()));
    }


}
