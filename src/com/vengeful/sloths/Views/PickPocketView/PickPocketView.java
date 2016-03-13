package com.vengeful.sloths.Views.PickPocketView;

import com.sun.prism.Graphics;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Views.InventoryView.GridInventoryView;
import com.vengeful.sloths.Views.InventoryView.InventoryView;
import com.vengeful.sloths.Views.InventoryView.ItemViewObject;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by harrison on 3/12/16.
 */
public class PickPocketView extends GridInventoryView {
    private Entity target;
    private int pickPocketSkill;

    public PickPocketView(Inventory inventory) {
        super(inventory);
    }

    public PickPocketView(Inventory inventory, Entity e, int skill){
        this(inventory);
        target = e;
        pickPocketSkill = skill;
    }

    @Override
    public void initWithInventory(Inventory inventory){
        for (int i = 0; i < inventory.getCurrentSize(); ++i) {
            //InventoryItem item = inventory.getItem(i);
            //this.itemList.add(new AbilityViewObject(inventory.getItem(i)));
            //this.itemList.add(new AbilityViewObject(inventory.getItem(i)));
            System.out.println("THIS IS THE ITEM " + inventory.getItem(i).getItemName() );
            int value = inventory.getItem(i).getValue();
            int chanceToPickPocket = 100 - value / (pickPocketSkill + 1);
            this.getItemList().add(new PickPocketItems(inventory.getItem(i), chanceToPickPocket));
            //this.getFromItemList(0).setSelected(true);
            //if(i==0) {
            //  this.getFromItemList(i).setSelected(true);
            //}
            //this.getItemList().add(new AbilityViewObject(inventory.getItem(i), this.getWidth(),this.getHeight()));
            //ivoFactory.generateItemViewObject(inventory.getItem(i), this.getWidth(), this.getHeight(), )
        }
    }
}
