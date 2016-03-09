package com.vengeful.sloths.Views.InventoryView;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;

import java.awt.*;

/**
 * Created by lenovo on 3/8/2016.
 */
public class GridItemViewObject extends ItemViewObject {

    public GridItemViewObject() {
        super();
    }

    public GridItemViewObject(InventoryItem inventoryItem, int width, int height) {
        super(inventoryItem, width, height);
    }

    public void paintComponent(Graphics g, int x, int y, int width, int height) {
        //super.paintComponent(g);
        super.paintComponent(g,x,y,width,height);
        //g.drawImage(this.getItemImage(), x, y, width, height, this);
    }

}
