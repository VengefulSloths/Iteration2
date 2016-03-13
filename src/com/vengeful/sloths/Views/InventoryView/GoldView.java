package com.vengeful.sloths.Views.InventoryView;

import com.vengeful.sloths.Models.Inventory.Inventory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by John on 3/13/2016.
 */
public class GoldView extends JComponent {

    private Inventory inventory;

    public GoldView(Inventory inventory){
        this.inventory = inventory;
    }

    private String text;
    @Override
    public void paintComponent(Graphics g){

        g.setFont(new Font("Helvetica",1,20));
        g.setColor(Color.orange);
        FontMetrics metrics = g.getFontMetrics();
        g.drawString("Gold: " + this.inventory.getGold(),this.getWidth()/2 - metrics.stringWidth(this.getText())/2,20);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
