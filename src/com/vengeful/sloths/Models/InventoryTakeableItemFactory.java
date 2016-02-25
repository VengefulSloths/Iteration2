package com.vengeful.sloths.Models;

import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;

import java.util.*;

/**
 * Created by luluding on 2/22/16.
 */
public class InventoryTakeableItemFactory {


    private static InventoryTakeableItemFactory factory = null;

    private InventoryTakeableItemFactory(){
    }

    public static InventoryTakeableItemFactory getInstance(){
        if(factory == null)
            factory = new InventoryTakeableItemFactory();

        return factory;
    }

    public TakeableItem createTakeableRep(InventoryItem item){
        return new TakeableItem(item.getItemName(), item);
    }

    //add in logic to create corresponding graphical representative





}
