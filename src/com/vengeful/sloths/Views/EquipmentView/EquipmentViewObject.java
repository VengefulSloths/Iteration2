package com.vengeful.sloths.Views.EquipmentView;


import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Views.InventoryView.ItemViewObject;
import com.vengeful.sloths.Views.ViewFactory.EquipmentImageFactory;
import com.vengeful.sloths.Views.ViewFactory.ItemImageFactory;
import com.vengeful.sloths.Views.ViewFactory.ViewItem;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class EquipmentViewObject extends ItemViewObject {

    private EquippableItems equipmentItem;
    private Image itemImage;
    private EquipmentImageFactory imageFactory = new EquipmentImageFactory();
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
    public void setImageFactory(EquipmentImageFactory imageFactory) {
        this.imageFactory = imageFactory;
    }

    public EquipmentViewObject() { }

    public EquipmentViewObject(EquippableItems viewItem) {
        this.setViewItem(viewItem);
        this.setEquipmentItem(viewItem);
        this.setItemImage(this.getImageFactory().handleUnscaledItemImageGeneration(viewItem));
        this.setIsDisplayed(false);
    }

    public void paintComponent(Graphics g, int x, int y, int containerWidth, int containerHeight) {
        super.paintComponent(g);
        int imageWidth=containerWidth/4;
        int imageHeight=containerHeight;

        g.drawImage(this.getItemImage(), x, y, imageWidth, imageHeight, this);
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        //String itemName = "Item: " + this.getEquipmentItem().getItemName();
        String itemName = this.getViewItem().getItemName();
        String itemStats = this.getEquipmentItem().getItemStats().toString();
        //String itemStats = "Stats: ";
        String itemDescription = "A lovely " + this.getViewItem().getItemName();
        int nameStringWidth = g.getFontMetrics().stringWidth(itemName);
        int statsStringWidth = g.getFontMetrics().stringWidth(itemStats);
        int descriptionStringWidth = g.getFontMetrics().stringWidth(itemDescription);
        int stringHeight = g.getFontMetrics().getHeight();
        int stringXCoord = x+imageWidth+15;
        if(stringXCoord + descriptionStringWidth > x+containerWidth) {
            itemDescription = "A lovely " + this.getViewItem().getItemName();
        }

        if(this.getViewItem().getItemName()!=null) {
            g.drawString(itemName, stringXCoord, y+stringHeight);
            if(this.isSelected()) {
                Border b = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GREEN, Color.GREEN);
                b.paintBorder(this, g, x, y, containerWidth, containerHeight);
            }
            if(this.getEquipmentItem().getItemStats().toString().length()>0) {
                g.drawString(itemStats, stringXCoord, y+2*stringHeight + containerHeight/10);
                g.drawString(itemDescription, stringXCoord, y+3*stringHeight + 2*containerHeight/10);
            } else {
                g.drawString(itemDescription, stringXCoord, y+2*stringHeight + containerHeight/10);
            }
        } else {
            String notEquipped = new String("Not equipped");
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            int notEquippedStringWidth = g.getFontMetrics().stringWidth(notEquipped);
            int notEquippedStringHeight = g.getFontMetrics().getHeight();
            g.drawString(notEquipped, x + containerWidth/2 - notEquippedStringWidth/2, y + containerHeight/2);
        }

    }

}
