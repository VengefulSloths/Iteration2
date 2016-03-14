package com.vengeful.sloths.Views.InventoryView;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Views.ViewFactory.ItemImageFactory;
import com.vengeful.sloths.Views.ViewFactory.ViewItem;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2/29/2016.
 */
public class ItemViewObject extends JPanel {

    private boolean isDisplayed;
    private Image itemImage;
    protected ItemImageFactory imageFactory = new ItemImageFactory(); //edit
    private ViewItem viewItem;
    private boolean isSelected;
    private String name;

    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Image getItemImage() {
        return itemImage;
    }
    public void setItemImage(Image itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return this.name;
    }
    public void setItemName(String name) {
        this.name = name;
    }

    public boolean isDisplayed() {
        return isDisplayed;
    }
    public void setIsDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    public ViewItem getViewItem() {
        return viewItem;
    }
    public void setViewItem(ViewItem viewItem) {
        this.viewItem = viewItem;
    }

    public ItemImageFactory getImageFactory() {
        return imageFactory;
    }

    public ItemViewObject() { }

    public ItemViewObject(ViewItem viewItem) {
        this.setViewItem(viewItem);
        this.setItemImage(this.getImageFactory().handleUnscaledItemImageGeneration(viewItem));
        this.setIsDisplayed(false);
    }

    public ItemViewObject(ViewItem viewItem, int width, int height) {
        this.setViewItem(viewItem);
        this.setItemImage(this.getImageFactory().handleScaledItemImageGeneration(viewItem,width,height));
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
