package com.vengeful.sloths.Views.HUDView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by John on 3/12/2016.
 */
public class LivesView extends JComponent {
    private Image livesIcon = (new ImageIcon("resources/lives_icon_32.png")).getImage();
    private int numLives = 3;

    public LivesView(){
        this.setPreferredSize(new Dimension(32,116));
        //this.setBackground(new Color(0f,0f,0f,0f));
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        int offset = 15;
        for(int i = 0; i < numLives; ++i){
            //System.out.println("pringint lives " + i);
            g2d.drawImage(livesIcon,0,offset, this);
            offset += 28;
        }
    }

    public int getNumLives() {
        return numLives;
    }

    public void setNumLives(int numLives) {
        this.numLives = numLives;
    }
}
