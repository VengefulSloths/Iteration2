package com.vengeful.sloths.Views.InventoryView;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Views.ViewFactory.ItemImageFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2/29/2016.
 */
public class ItemViewObject extends JPanel {

    private boolean isDisplayed;
    private Image itemImage;
    protected ItemImageFactory imageFactory = new ItemImageFactory(); //edit
    private InventoryItem inventoryItem;

    public Image getItemImage() {
        return itemImage;
    }
    public void setItemImage(Image itemImage) {
        this.itemImage = itemImage;
    }

    public boolean isDisplayed() {
        return isDisplayed;
    }
    public void setIsDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }
    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public ItemImageFactory getImageFactory() {
        return imageFactory;
    }

    public ItemViewObject() { }

    public ItemViewObject(InventoryItem inventoryItem) {
        this.setInventoryItem(inventoryItem);
        //this.setItemImage(this.imageFactory.handleUnscaledItemImageGeneration(inventoryItem));
        this.setItemImage(this.getImageFactory().handleUnscaledItemImageGeneration(inventoryItem));
        this.setIsDisplayed(false);
    }

    public ItemViewObject(InventoryItem inventoryItem, int width, int height) {
        this.setInventoryItem(inventoryItem);
        //this.setItemImage(this.imageFactory.handleScaledItemImageGeneration(inventoryItem, width, height));
        this.setItemImage(this.getImageFactory().handleScaledItemImageGeneration(inventoryItem,width,height));
        this.setIsDisplayed(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    public void paintComponent(Graphics g, int x, int y, int width, int height) {
        super.paintComponent(g);
        g.drawImage(this.getItemImage(), x, y, width, height, this);
    }


}
