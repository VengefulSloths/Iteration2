package com.vengeful.sloths.Views.EquipmentView;


import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Views.InventoryView.ItemViewObject;
import com.vengeful.sloths.Views.ViewFactory.ItemImageFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class EquipmentViewObject extends JPanel {

    private EquippableItems equipmentItem;
    private Image itemImage;
    protected ItemImageFactory imageFactory = new ItemImageFactory(); //edit
    private boolean isSelected;

    public EquippableItems getEquipmentItem() {
        return equipmentItem;
    }
    public void setEquipmentItem(EquippableItems equipmentItem) {
        this.equipmentItem = equipmentItem;
    }
    public Image getItemImage() {
        return itemImage;
    }
    public void setItemImage(Image itemImage) {
        this.itemImage = itemImage;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public ItemImageFactory getImageFactory() {
        return imageFactory;
    }
    public void setImageFactory(ItemImageFactory imageFactory) {
        this.imageFactory = imageFactory;
    }

    public EquipmentViewObject() { }

    public EquipmentViewObject(EquippableItems equipmentItem) {
        this.setEquipmentItem(equipmentItem);
        this.setItemImage(this.getImageFactory().handleUnscaledItemImageGeneration(equipmentItem));
    }

    public EquipmentViewObject(EquippableItems equipmentItem, int width, int height) {
        this.setEquipmentItem(equipmentItem);
        this.setItemImage(this.getImageFactory().handleScaledItemImageGeneration(equipmentItem,width,height));
    }

    public void initDisplay() {

    }


    public void paintComponent(Graphics g, int x, int y, int containerWidth, int containerHeight) {
        super.paintComponent(g);
        int imageWidth=containerWidth/4;
        int imageHeight=containerHeight;

        g.drawImage(this.getItemImage(), x, y, imageWidth, imageHeight, this);
        g.setColor(Color.RED);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        String itemName = "Item: " + this.getEquipmentItem().getItemName();
        String itemStats = "Stats: " + this.getEquipmentItem().getItemStats();
        String itemDescription = "Description: " ;
        int nameStringWidth = g.getFontMetrics().stringWidth(itemName);
        int statsStringWidth = g.getFontMetrics().stringWidth(itemStats);
        int descriptionStringWidth = g.getFontMetrics().stringWidth(itemDescription);
        int stringHeight = g.getFontMetrics().getHeight();
        g.drawString(itemName, x+imageWidth+5, stringHeight);
        g.drawString(itemStats, x+imageWidth+5, 2*stringHeight);
        g.drawString(itemDescription, x+imageWidth+5, 3*stringHeight);



    }

}
