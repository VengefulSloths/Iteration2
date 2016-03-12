package com.vengeful.sloths.Views.HUDView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by John on 3/12/2016.
 */


public class HealthBarView extends JComponent {
    private Image livesIcon = (new ImageIcon("resources/lives_icon_32.png")).getImage();
    private int numLives = 3;

    private CustomProgressBar healthBar;
    private CustomProgressBar manaBar;
    private CustomProgressBar xpBar;

    public HealthBarView() {
        this.setPreferredSize(new Dimension(160, 116));

        this.healthBar = new CustomProgressBar(140, 20);
        healthBar.setMaxProgress(100);
        healthBar.setCurrentProgress(50);
        healthBar.setBorderWidth(5);
        healthBar.setDepletedColor(Color.LIGHT_GRAY);

        //this.add(healthBar);


        this.manaBar = new CustomProgressBar(140, 20);
        manaBar.setMaxProgress(100);
        manaBar.setCurrentProgress(50);
        manaBar.setBorderWidth(5);
        manaBar.setCurrentColor(Color.CYAN);
        manaBar.setDepletedColor(Color.LIGHT_GRAY);
        //this.add(manaBar);

        this.xpBar = new CustomProgressBar(140, 20);
        xpBar.setMaxProgress(100);
        xpBar.setCurrentProgress(50);
        xpBar.setBorderWidth(5);
        xpBar.setCurrentColor(Color.ORANGE);
        xpBar.setDepletedColor(Color.LIGHT_GRAY);
        // xpPanel.add(xpLabel);
        //this.add(xpBar);

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int offset = 20;

        healthBar.paintComponent(g2d, 10, offset);
        offset += 28;
        manaBar.paintComponent(g2d,10,offset);
        offset += 28;
        xpBar.paintComponent(g2d,10,offset);
        offset += 28;


    }

    public int getNumLives() {
        return numLives;
    }

    public void setNumLives(int numLives) {
        this.numLives = numLives;
    }

    public CustomProgressBar getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(CustomProgressBar healthBar) {
        this.healthBar = healthBar;
    }

    public CustomProgressBar getManaBar() {
        return manaBar;
    }

    public void setManaBar(CustomProgressBar manaBar) {
        this.manaBar = manaBar;
    }

    public CustomProgressBar getXpBar() {
        return xpBar;
    }

    public void setXpBar(CustomProgressBar xpBar) {
        this.xpBar = xpBar;
    }
}


