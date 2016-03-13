package com.vengeful.sloths.Views.PickPocketView;

import com.sun.prism.*;
import com.vengeful.sloths.Views.InventoryView.InventoryView;
import com.vengeful.sloths.Views.InventoryView.ItemViewObject;
import com.vengeful.sloths.Views.ViewFactory.ViewItem;

import java.awt.*;
import java.awt.Graphics;

/**
 * Created by harrison on 3/12/16.
 */
public class PickPocketItems extends ItemViewObject {

    private int chanceToSucceed;

    public PickPocketItems(ViewItem viewItem, int chanceToSucceed) {
        this.setViewItem(viewItem);
        this.setItemImage(this.getImageFactory().handleUnscaledItemImageGeneration(viewItem));
        this.setIsDisplayed(false);
        this.chanceToSucceed = chanceToSucceed;
    }

    @Override
    public void paintComponent(Graphics g, int x, int y, int width, int height) {
        super.paintComponent(g, x, y, width, height);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0f,0f,0f,.4f));
        g2d.fillRect(x + 10,y + height/2,width - 20,height/2);
        g2d.setColor(Color.WHITE);
        g2d.drawString("" +chanceToSucceed, x +10, y + height);
    }


}
