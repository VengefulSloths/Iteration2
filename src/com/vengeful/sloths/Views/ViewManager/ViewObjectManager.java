package com.vengeful.sloths.Views.ViewManager;

import com.vengeful.sloths.Views.InventoryView.ItemViewObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by lenovo on 2/29/2016.
 */

    public abstract class ViewObjectManager {

        private ArrayList<ItemViewObject> itemList;

        public ViewObjectManager() {
            this.itemList = new ArrayList<ItemViewObject>();
        }

        public ViewObjectManager(ArrayList<ItemViewObject> itemList) {
            this.itemList = itemList;
        }

        public int getItemListSize() {
            return itemList.size();
        }

        public Iterator<ItemViewObject> iterator() {
            return itemList.iterator();
        }

        public ArrayList<ItemViewObject> getItemList() {
        return itemList;
    }

        public void addItem(ItemViewObject item) {
            itemList.add(item);
        }



    }
