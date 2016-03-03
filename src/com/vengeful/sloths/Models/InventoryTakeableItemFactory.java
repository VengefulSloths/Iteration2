package com.vengeful.sloths.Models;

import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Utility.Coord;

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

    public TakeableItem createTakeableRep(InventoryItem item, Coord loc){
        TakeableItem takeableRep = new TakeableItem(item.getItemName(), item, loc);
        this.createTakeableVO(takeableRep);
        return takeableRep;
    }

    public void createTakeableVO(TakeableItem item){
        TemporaryVOCreationVisitor creator = TemporaryVOCreationVisitor.getInstance();
        item.accept(creator);

    }

    //add in logic to create corresponding graphical representations





}
