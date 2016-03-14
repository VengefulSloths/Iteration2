package com.vengeful.sloths.Views.EquippedAbilitiesView;


import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.EquippableItems;
import com.vengeful.sloths.Utility.StringUtils;
import com.vengeful.sloths.Views.ViewFactory.AbilityImageFactory;
import com.vengeful.sloths.Views.ViewFactory.ItemImageFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        int imageWidth=containerWidth/6;
        int imageHeight=containerHeight;

        g.drawImage(this.getItemImage(), x + 5, y, imageWidth, imageHeight, this);
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        String itemName = "Item: " + this.getEquippedAbility().getItemName();

        ArrayList<String> itemDescription = StringUtils.wrap("Description: " + this.getEquippedAbility().getDescription(), g.getFontMetrics(), containerWidth);

        int nameStringWidth = g.getFontMetrics().stringWidth(itemName);
        //int descriptionStringWidth = g.getFontMetrics().stringWidth(itemDescription);
        int stringHeight = g.getFontMetrics().getHeight();
        g.drawString(itemName, x+imageWidth+15, y+stringHeight);
        int i = 0;
        for (String text: itemDescription) {
            g.drawString(text, x+imageWidth+15, y+(3+ i++)*(stringHeight+3));
        }

    }

}
