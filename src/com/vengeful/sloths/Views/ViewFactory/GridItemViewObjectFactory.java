package com.vengeful.sloths.Views.ViewFactory;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Views.InventoryView.GridItemViewObject;

/**
 * Created by lenovo on 3/8/2016.
 */
public class GridItemViewObjectFactory extends ItemViewObjectFactory {

    private ItemImageFactory imageFactory = new ItemImageFactory();

    public GridItemViewObjectFactory() {

    }

    public GridItemViewObjectFactory(InventoryItem item, int containerWidth, int containerHeight, int numRows, int numCols) {


    }

}
