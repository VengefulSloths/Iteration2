package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.ViewObservable;

/**
 * Created by zach on 2/1/16.
 */
public class ProxyInventoryObserver extends ProxyObserver
        implements InventoryObserver {

    private InventoryObserver target;

    public ProxyInventoryObserver(InventoryObserver inventoryObserver, ViewObservable subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
        this.target = inventoryObserver;
    }

    @Override
    public ModelObserver getModelObserver() {
        return (ModelObserver) target;
    }

    @Override
    public void alertItemAdded(InventoryItem item) {

        ////System.out.Println("PROXY OBSERVER");
        ////System.out.Println("Item: " + item.getItemName() + " Added!");
        target.alertItemAdded(item);
    }

    @Override
    public void alertItemDropped(InventoryItem item) {
        ////System.out.Println("PROXY OBSERVER");
        ////System.out.Println("Item: " + item.getItemName() + " DROPPED!");
        target.alertItemDropped(item);
    }
}
