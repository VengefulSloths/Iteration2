package com.vengeful.sloths.Views.ViewFactory;

import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Views.InventoryView.ItemViewObject;

import javax.swing.*;

/**
 * Created by lenovo on 3/7/2016.
 */
public class ItemViewObjectFactory {


    public void ItemViewObjectFactory() {

    }


    public ItemViewObject generateItemViewObject(ViewItem item, int viewWidth, int viewHeight) {
        return new ItemViewObject(item, viewWidth, viewHeight);
    }






}
