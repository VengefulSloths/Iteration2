package com.vengeful.sloths.Views.HUDView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by John on 3/12/2016.
 */
public class IconView extends JComponent{
    private Image occIcon = (new ImageIcon("resources/smasher_icon_64.png")).getImage();
    private int level = 1;

    public IconView(){
        this.setPreferredSize(new Dimension(96,116));
        //this.setBackground(new Color(0f,0f,0f,0f));
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        int offset = 15;
        g2d.drawImage(occIcon,8,offset, this);
        offset += occIcon.getHeight(this);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Helvetica", 1, 20));
        g2d.drawString("Level: " + level, 0, offset + 20);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
