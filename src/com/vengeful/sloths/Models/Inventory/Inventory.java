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
    private int maxSize;

    private ArrayList<InventoryObserver> inventoryObservers;

    public Inventory() {
        this(50); //default size 50
    }

    public Inventory(int maxSize) {
        inventory = new ArrayList<>();
        this.inventoryObservers = new ArrayList<>();
        this.maxSize = maxSize;
        this.gold = 0;
    }

    public InventoryItem getItem(int index){
        if(index < 0 || index >= this.getCurrentSize())
            return null;

        return inventory.get(index);
    }

    public void clearInventory() {
        this.inventory = new ArrayList<>();
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
        if(this.getCurrentSize() >= maxSize)
            return false;
        else{
            inventory.add(item);

            Iterator<InventoryObserver> iter = this.inventoryObservers.iterator();
            while (iter.hasNext()) {
                InventoryObserver io = iter.next();
                io.alertItemAdded(item);
            }

        }
        return true;
    }

    public boolean removeItem(InventoryItem item) {

        if(inventory.remove(item)){

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

    public int getCurrentSize() {
        return this.inventory.size();
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
    public Gold dropSomeGold(int value){
        Gold g = new Gold();
        if(value > this.gold){
            g.setValue(this.gold);
            gold = 0;
        }
        else {
            g.setValue(value);
            gold = gold - value;
        }
        g.setItemName("gold");
        return g;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
