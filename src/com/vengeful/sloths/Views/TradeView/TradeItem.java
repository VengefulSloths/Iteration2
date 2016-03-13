package com.vengeful.sloths.Views.TradeView;

import com.vengeful.sloths.Views.InventoryView.ItemViewObject;
import com.vengeful.sloths.Views.ViewFactory.ViewItem;

import java.awt.*;

/**
 * Created by harrison on 3/13/16.
 */
public class TradeItem extends ItemViewObject {
    private int value;

    public TradeItem(ViewItem viewItem, int multiplier) {
        this.setViewItem(viewItem);
        this.setItemImage(this.getImageFactory().handleUnscaledItemImageGeneration(viewItem));
        this.setIsDisplayed(false);
        this.value = multiplier;
    }

    @Override
    public void paintComponent(Graphics g, int x, int y, int width, int height) {
        super.paintComponent(g, x, y, width, height);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0f,0f,0f,.4f));
        g2d.fillRect(x + 10,y + height/2,width - 20,height/2);
        g2d.setColor(Color.WHITE);
        g2d.drawString("" + value, x +10, y + height);
    }
}
