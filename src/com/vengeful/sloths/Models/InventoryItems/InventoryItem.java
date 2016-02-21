package com.vengeful.sloths.Models.InventoryItems;

import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.SaveLoad.SaveManager;
import com.vengeful.sloths.Models.SaveLoad.Saveable;

/**
 * Created by qianwen on 1/30/16.
 */
public abstract class InventoryItem implements Saveable {
    private TakeableItem mapItemRep;
    protected String itemName;

    public InventoryItem(){

    }


    //Will be called in TakeableItem when takeable item is created
    public void setTakeableItem(TakeableItem item){
        this.mapItemRep = item;
    }

    public String getItemName(){
        return this.itemName;
    }

    public TakeableItem getMapItemRep(){
        return this.mapItemRep;
    }


    public void saveMe(SaveManager sv, int ws){
        sv.writeVariableLine(ws,"itemName", itemName, false);
        mapItemRep.saveMeFromInv(sv, ws);
    }

    public abstract void saveMeFromTakeable(SaveManager sm, int ws);

    public void setMapItemRep(TakeableItem mapItemRep) {
        this.mapItemRep = mapItemRep;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
