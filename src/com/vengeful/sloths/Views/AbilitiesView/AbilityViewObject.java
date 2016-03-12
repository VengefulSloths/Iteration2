package com.vengeful.sloths.Views.AbilitiesView;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Views.ViewFactory.ItemImageFactory;
import com.vengeful.sloths.Views.ViewFactory.ViewItem;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2/29/2016.
 */
public class AbilityViewObject extends JPanel {

    private boolean isDisplayed;
    private Image itemImage;
    protected ItemImageFactory imageFactory = new ItemImageFactory(); //edit
    private Ability ability;
    private ViewItem viewItem;
    private boolean isSelected;

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

    public boolean isDisplayed() {
        return isDisplayed;
    }
    public void setIsDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    public Ability getAbility() {
        return this.ability;
    }
    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public ItemImageFactory getImageFactory() {
        return imageFactory;
    }

    public ViewItem getViewItem() {
        return viewItem;
    }
    public void setViewItem(ViewItem viewItem) {
        this.viewItem = viewItem;
    }

    public AbilityViewObject() { }

    public AbilityViewObject(Ability ability) {
        this.setAbility(ability);
        //this.setItemImage(this.imageFactory.handleUnscaledItemImageGeneration(inventoryItem));
        this.setItemImage(this.getImageFactory().handleUnscaledItemImageGeneration(ability));
        this.setIsDisplayed(false);
    }

    public AbilityViewObject(Ability ability, int width, int height) {
        this.setAbility(ability);
        //this.setItemImage(this.imageFactory.handleScaledItemImageGeneration(inventoryItem, width, height));
        this.setItemImage(this.getImageFactory().handleScaledItemImageGeneration(ability,width,height));
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
