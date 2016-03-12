package com.vengeful.sloths.Views.HUDView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by John on 3/11/2016.
 */
public class CustomProgressBar extends JComponent {

    int maxProgress = 0;
    int currentProgress = 0;

    int progressBarWidth;
    int progressBarHeight;
    int borderWidth;

    Color currentColor = Color.GREEN;
    Color depletedColor = Color.RED;
    Color borderColor = Color.DARK_GRAY;

    public CustomProgressBar(int width, int height){
        this.progressBarWidth = width;
        this.progressBarHeight = height;
        this.setPreferredSize(new Dimension(width, height));
    }



    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        //paint rectangle to draw border
        g2d.setColor(borderColor);
        g2d.fillRect(0,0,progressBarWidth,progressBarHeight);
        //now add depleted color
        g2d.setColor(depletedColor);
        g2d.fillRect(borderWidth/2, borderWidth/2, progressBarWidth - borderWidth, progressBarHeight-borderWidth);
        //now paint current stuff
        g2d.setColor(currentColor);
        //calculate % to show
        int percentage = (int)(((float)currentProgress/(float)maxProgress) * progressBarWidth);
        g2d.fillRect(borderWidth/2, borderWidth/2, percentage - borderWidth, progressBarHeight-borderWidth);
    }

    public void paintComponent(Graphics g, int x, int y){
        Graphics2D g2d = (Graphics2D) g;
        //paint rectangle to draw border
        g2d.setColor(borderColor);
        g2d.fillRect(x,y,progressBarWidth,progressBarHeight);
        //now add depleted color
        g2d.setColor(depletedColor);
        g2d.fillRect(x + borderWidth/2, y + borderWidth/2, progressBarWidth - borderWidth, progressBarHeight-borderWidth);
        //now paint current stuff
        g2d.setColor(currentColor);
        //calculate % to show
        int percentage = (int)(((float)currentProgress/(float)maxProgress) * progressBarWidth);
        g2d.fillRect(x + borderWidth/2, y + borderWidth/2, percentage - borderWidth, progressBarHeight-borderWidth);
    }


    //////////////////////////////////////////////getters and setters//////////////////////////////////

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    public int getProgressBarWidth() {
        return progressBarWidth;
    }

    public void setProgressBarWidth(int progressBarWidth) {
        this.progressBarWidth = progressBarWidth;
    }

    public int getProgressBarHeight() {
        return progressBarHeight;
    }

    public void setProgressBarHeight(int progressBarHeight) {
        this.progressBarHeight = progressBarHeight;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public Color getDepletedColor() {
        return depletedColor;
    }

    public void setDepletedColor(Color depletedColor) {
        this.depletedColor = depletedColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

}
