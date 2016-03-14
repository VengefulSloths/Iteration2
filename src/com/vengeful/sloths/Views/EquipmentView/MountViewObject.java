package com.vengeful.sloths.Views.EquipmentView;

import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Mount;
import com.vengeful.sloths.Views.ViewFactory.MountImageFactory;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by echristiansen on 3/12/2016.
 */
public class MountViewObject extends EquipmentViewObject {
    private Mount mount;
    private MountImageFactory imageFactory = new MountImageFactory();

    public Mount getMount() {
        return mount;
    }
    public void setMount(Mount mount) {
        this.mount = mount;
    }
    @Override
    public MountImageFactory getImageFactory() {
        return imageFactory;
    }
    public void setImageFactory(MountImageFactory imageFactory) {
        this.imageFactory = imageFactory;
    }

    public MountViewObject(Mount viewItem) {
        this.setViewItem(viewItem);
        this.setMount(viewItem);
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
        String itemName = this.getMount().getName();
        String itemStats = "Movespeed: " + this.getMount().getMoveSpeed();
        String itemDescription = "Increases movespeed by " + this.getMount().getMoveSpeed();
        int nameStringWidth = g.getFontMetrics().stringWidth(itemName);
        int statsStringWidth = g.getFontMetrics().stringWidth(itemStats);
        int descriptionStringWidth = g.getFontMetrics().stringWidth(itemDescription);
        int stringHeight = g.getFontMetrics().getHeight();
        int stringXCoord = x+imageWidth+15;

        if(stringXCoord + descriptionStringWidth > x + containerWidth) {
            itemDescription = "A lovely " + this.getMount().getName();
        }

        //if(this.getMount().getName()!=null) {
        if(this.getMount().getName().length()>0) {
            g.drawString(itemName, stringXCoord, y+stringHeight);
            g.drawString(itemStats, stringXCoord, y+2*stringHeight + containerHeight/10);
            g.drawString(itemDescription, stringXCoord, y+3*stringHeight + 2*containerHeight/10);
            if (this.isSelected()) {
                Border b = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GREEN, Color.GREEN);
                b.paintBorder(this, g, x, y, containerWidth, containerHeight);
            }
        } else {
            String notEquipped = new String("Not equipped");
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            int notEquippedStringWidth = g.getFontMetrics().stringWidth(notEquipped);
            int notEquippedStringHeight = g.getFontMetrics().getHeight();
            g.drawString(notEquipped, x + containerWidth/2 - notEquippedStringWidth/2, y + containerHeight/2);
        }

    }


}
