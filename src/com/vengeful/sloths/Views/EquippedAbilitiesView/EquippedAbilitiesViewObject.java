package com.vengeful.sloths.Views.EquippedAbilitiesView;


import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Views.ViewFactory.AbilityImageFactory;
import com.vengeful.sloths.Views.ViewFactory.ItemImageFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zschultz on 2/21/2016.
 */
public class EquippedAbilitiesViewObject extends JPanel {

    private Ability equippedAbility;
    private Image itemImage;
    protected ItemImageFactory imageFactory = new AbilityImageFactory();
    private boolean isSelected;

    public Ability getEquippedAbility() {
        return equippedAbility;
    }

    public void setEquippedAbility(Ability equippedAbility) {
        this.equippedAbility = equippedAbility;
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

    public EquippedAbilitiesViewObject() { }

    public EquippedAbilitiesViewObject(Ability equipmentItem) {
        this.setEquippedAbility(equipmentItem);
        this.setItemImage(this.getImageFactory().handleUnscaledItemImageGeneration(equipmentItem));
    }

    public EquippedAbilitiesViewObject(Ability equipmentItem, int width, int height) {
        this.setEquippedAbility(equipmentItem);
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
        String itemName = "Item: " + this.getEquippedAbility().getItemName();
        String itemDescription = "Description: " ;
        int nameStringWidth = g.getFontMetrics().stringWidth(itemName);
        int descriptionStringWidth = g.getFontMetrics().stringWidth(itemDescription);
        int stringHeight = g.getFontMetrics().getHeight();
        g.drawString(itemName, x+imageWidth+5, y+stringHeight);
        g.drawString(itemDescription, x+imageWidth+5, y+3*stringHeight);
    }

}
