package com.vengeful.sloths.Views.InventoryView;

import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Observers.InventoryObserver;


import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by lenovo on 3/1/2016.
 */
public class ListInventoryView extends InventoryView implements InventoryObserver {
    public ListInventoryItemViewObjectManager manager = new ListInventoryItemViewObjectManager();


    public ListInventoryView(Inventory inventory) {
        super(inventory);
        manager.initWithInventory(this.getInventory());
        System.out.println("THIS IS THE INVENTORY SIZE!!:" + this.getInventory().getCurrentSize());
        System.out.println("THIS IS THE FIRST ITEM IN INVENTORY " + this.getInventory().getItem(0).getItemName());
        System.out.println("THIS IS THE FIRST ITEM IN INVENTORY " + this.getInventory().getItem(1).getItemName());

        Iterator<ItemViewObject> iter = manager.iterator();
        int count = 0;
        while (iter.hasNext()) {
            ItemViewObject current = iter.next();
            current.setIsDisplayed(true);
            this.itemPanel.add(current);
            count++;
        }
        /*
        for(int i=0; i<manager.getItemListSize(); i++) {
            this.itemPanel.add(manager.getFromItemList(i));
            manager.getFromItemList(i).setIsDisplayed(true);
        }
        */
    }

    public void alertItemRemoved(InventoryItem item) { //woudld really need to set isDisplayed to false and then not add false one
        manager.removeInventoryItemViewObject(item);
        Iterator<ItemViewObject> iter = manager.iterator();
        while (iter.hasNext()) { //would have to first somehow remove all of the itemviewobjects
            ItemViewObject current = iter.next();
                this.itemPanel.add(current);
                current.setIsDisplayed(true);
                this.revalidate();
                this.repaint();
            }
        }

    /* The idea is to have an isDisplayed attribute of ItemViewObjects.
    *  When we iterate through the ArrayList of ItemViewObjects, we only add those that are not already displayed.
    *  */
    public void alertItemAdded(ListItemViewObject item) {
        manager.addListInventoryItemViewObject(item);
        Iterator<ItemViewObject> iter = manager.iterator();
        while (iter.hasNext()) {
            ItemViewObject current = iter.next();
            if (!current.isDisplayed()) {
                this.itemPanel.add(item);
                current.setIsDisplayed(true);
                this.revalidate();
                this.repaint();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //this.itemPanel.add(manager.getFromItemList(0));
        System.out.println("THE MOTHERLOVING ITEMNAME: " + manager.getFromItemList(0).getViewItem().getItemName());
        //test1.paintComponent(g2d);
        //test2.paintComponent(g2d);
        int horOffset = 5;
        int vertOffset = 10;
        for (int i =0; i<3; i++) {
            g.draw3DRect(horOffset, vertOffset, 50,50, true);
            vertOffset +=20;
        }
        /*
        Iterator<AbilityViewObject> iter = manager.iterator();
        while (iter.hasNext()) {
            ListItemViewObject current = (ListItemViewObject) iter.next();
            //current.paintComponent(g2d);
            current.paintComponent(g);
            //this.add(current);
            System.out.println("PRINTING FROM THE LIST INVENTORYVIEW" + current.getInventoryItem().getItemName());
            System.out.println("THIS IS THE ITEMLIST SIZE" + manager.getItemListSize());
            System.out.println("Printing itemname HOPEFULLY: " + manager.getFromItemList(0).getInventoryItem().getItemName());
            System.out.println("Printing itemname HOPEFULLY: " + manager.getFromItemList(1).getInventoryItem().getItemName());

        }
        */
    }
}
