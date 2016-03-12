package com.vengeful.sloths.Models.Inventory;


import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.MapItems.Gold;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

import com.vengeful.sloths.Models.ViewObservable;

import com.vengeful.sloths.Models.Observers.InventoryObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by qianwen on 1/30/16.
 */
public class Inventory implements ModelVisitable, ViewObservable {

    //leave it as an arrayList so that items can move over when previous one removed from inventory
    private ArrayList<InventoryItem> inventory;
    private int gold;
    private int currentSize;
    private int maxSize;

    private ArrayList<InventoryObserver> inventoryObservers;

    public Inventory() {
        this(50); //default size 50
    }

    public Inventory(int maxSize) {
        inventory = new ArrayList<>();
        this.inventoryObservers = new ArrayList<>();
        this.currentSize = 0;
        this.maxSize = maxSize;
        this.gold = 0;
    }


    public InventoryItem getItem(int index){
        if(index < 0 || index >= this.currentSize)
            return null;

        return inventory.get(index);
    }

    public void clearInventory() {
        this.inventory = new ArrayList<>();
        this.setCurrentSize(0);
    }

    public boolean hasItem(InventoryItem item){
        return this.inventory.contains(item);
    }


    /*
    public void alertObserversFromLoad() {
        Iterator<InventoryObserver> iter = this.inventoryObservers.iterator();
        while (iter.hasNext()) {
            InventoryObserver io = iter.next();
            for (MainMenuItem i : inventory)
            io.alertItemAdded(i);
        }
    }*/


    public boolean addItem(InventoryItem item) {
        if(currentSize >= maxSize)
            return false;
        else{
            ++this.currentSize;


            //@TODO: REMOVE REMOVE REMOVE
            Iterator<InventoryObserver> iter = this.inventoryObservers.iterator();
            while (iter.hasNext()) {
                InventoryObserver io = iter.next();
                io.alertItemAdded(item);
            }

            inventory.add(item);
        }
        return true;
    }

    public boolean removeItem(InventoryItem item) {

        if(inventory.remove(item)){
            --this.currentSize;

            if (this.currentSize <= 0){
                System.out.println("Inventory size went below 0");
                this.currentSize = 0;
            }

        Iterator<InventoryObserver> iter = this.inventoryObservers.iterator();
        while (iter.hasNext()) {
            InventoryObserver io = iter.next();
            io.alertItemDropped(item);
        }
            return true;
        }else{
            return false;
        }


    }


    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public int getCurrentSize() {
        return this.currentSize;
    }

    public void setMaxSize(int maxSize){
        this.maxSize = maxSize;
    }

    public int getMaxSize(){
        return this.maxSize;
    }

    public Iterator<InventoryItem> iterator() { return this.inventory.iterator(); }


    public void registerObserver(ModelObserver modelObserver) {
        this.inventoryObservers.add((InventoryObserver) modelObserver);
    }

    public void deregisterObserver(ModelObserver modelObserver) { this.inventoryObservers.remove(modelObserver);}


    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitInventory(this);
    }

    public InventoryItem[] getArrayofItems(){
        InventoryItem[] arrItem = new InventoryItem[getCurrentSize()];
        int i = 0;
        for(InventoryItem ii : inventory){
            arrItem[i++] = ii;
        }
        return arrItem;
    }
    public boolean addGold(Gold gold){
        this.gold += gold.getValue();
        return true;
    }
    public Gold dropAllGold(){
        Gold g = new Gold();
        g.setValue(this.getGold());
        g.setItemName("gold");
        this.setGold(0);
        return g;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
