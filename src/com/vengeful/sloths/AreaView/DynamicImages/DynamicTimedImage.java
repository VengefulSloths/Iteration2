package com.vengeful.sloths.AreaView.DynamicImages;

import com.vengeful.sloths.AreaView.ViewTime;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alex on 2/21/2016.
 */
public class DynamicTimedImage extends DynamicImage {
    private DynamicImage startImage;
    private DynamicImage endImage;
    private ArrayList<Image> animatedImages;
    private ArrayList<String> paths = new ArrayList<>();
    private enum state {PRE, ACTIVE, POST}
    private state currentState = state.PRE;
    private long startTime = Long.MAX_VALUE;
    private long endTime = Long.MAX_VALUE;




    public DynamicTimedImage(int width, int height, PositioningStrategy positioningStrategy, DynamicImage start, DynamicImage end, ArrayList<String> imagePaths) {
        super(width, height, positioningStrategy);
        this.startImage = start;
        this.endImage = end;
        animatedImages = new ArrayList<>();
        for (String imagePath: imagePaths) {
            paths.add(imagePath);
            animatedImages.add((new ImageIcon(imagePath)).getImage());
        }
    }

    public void start(long duration) {
        this.startTime = ViewTime.getInstance().getCurrentTimeMilli();
        this.endTime = this.startTime + duration;
        this.currentState = state.ACTIVE;
    }

    public void end() {
        this.currentState = state.POST;
    }

    private void resolveState() {
        long time = ViewTime.getInstance().getCurrentTimeMilli();
        if (time >= endTime) {
            currentState = state.POST;
        } else if (time < startTime) {
            currentState = state.PRE;
        }
    }

    @Override
    public Image getImage() {
        this.resolveState();
        if (currentState == state.PRE) {
            return startImage.getImage();
        } else if (currentState == state.POST) {
            return endImage.getImage();
        } else {
            //System.out.println((int)((ViewTime.getInstance().getCurrentTimeMilli() - startTime)*animatedImages.size()/(endTime - startTime)));
            return animatedImages.get( (int)((ViewTime.getInstance().getCurrentTimeMilli() - startTime)*animatedImages.size()/(endTime - startTime)));
        }
    }

    @Override
    public String doGetCurrentImagePath() {
        this.resolveState();
        if (currentState == state.PRE) {
            return startImage.getCurrentImagePath();
        } else if (currentState == state.POST) {
            return endImage.getCurrentImagePath();
        } else {
            //System.out.println((int)((ViewTime.getInstance().getCurrentTimeMilli() - startTime)*animatedImages.size()/(endTime - startTime)));
            return paths.get( (int)((ViewTime.getInstance().getCurrentTimeMilli() - startTime)*animatedImages.size()/(endTime - startTime)));
        }
    }

}
