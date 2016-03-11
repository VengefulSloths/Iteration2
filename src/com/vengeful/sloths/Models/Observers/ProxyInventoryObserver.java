package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.AreaView.ViewTime;
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
        return  target;
    }

    @Override
    public void alertItemAdded(InventoryItem item) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertItemAdded(item));
        }
    }

    @Override
    public void alertItemDropped(InventoryItem item) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertItemDropped(item));
        }
    }
}
