package com.vengeful.sloths.Views.PickPocketView;

import com.sun.prism.Graphics;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.ObserverManager;
import com.vengeful.sloths.Models.Observers.ProxyInventoryObserver;
import com.vengeful.sloths.Models.Observers.ProxyObserver;
import com.vengeful.sloths.Utility.CalculateBuySellPickPocket;
import com.vengeful.sloths.Views.InventoryView.GridInventoryView;
import com.vengeful.sloths.Views.InventoryView.InventoryView;
import com.vengeful.sloths.Views.InventoryView.ItemViewObject;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by harrison on 3/12/16.
 */
public class PickPocketView extends GridInventoryView {
    private Entity target;
    private int pickPocketSkill;
    private String entityName;

    public PickPocketView(Inventory inventory) {
        super(inventory);
    }

    public PickPocketView(Inventory inventory, Entity e, int skill){
        //this.itemList = new ArrayList<AbilityViewObject>();
        this.setItemList(new ArrayList<ItemViewObject>());
        this.setInventory(inventory);
        //Create a proxy for the observer, regester the proxy w/ entity, add proxy to manager
        ProxyObserver pio = new ProxyInventoryObserver(this, inventory);
        ObserverManager.getInstance().addProxyObserver(pio);
        target = e;
        entityName = target.getName();
        pickPocketSkill = skill;
        this.setNumRows(5); //default numRows
        this.setNumCols(4); //default numCols

        initWithInventory(this.getInventory());
        initDefaultUI();
    }

    @Override
    public void initWithInventory(Inventory inventory){
        for (int i = 0; i < inventory.getCurrentSize(); ++i) {
            //InventoryItem item = inventory.getItem(i);
            //this.itemList.add(new AbilityViewObject(inventory.getItem(i)));
            //this.itemList.add(new AbilityViewObject(inventory.getItem(i)));
            System.out.println("THIS IS THE ITEM " + inventory.getItem(i).getItemName() );
            int value = inventory.getItem(i).getValue();
            int chanceToPickPocket = CalculateBuySellPickPocket.CalculatePickpocketChance(value, pickPocketSkill);
            this.getItemList().add(new PickPocketItems(inventory.getItem(i), chanceToPickPocket));
            //this.getFromItemList(0).setSelected(true);
            //if(i==0) {
            //  this.getFromItemList(i).setSelected(true);
            //}
            //this.getItemList().add(new AbilityViewObject(inventory.getItem(i), this.getWidth(),this.getHeight()));
            //ivoFactory.generateItemViewObject(inventory.getItem(i), this.getWidth(), this.getHeight(), )
        }
    }

    @Override
    protected String getInvLabel(){
        return entityName;
    }
}
