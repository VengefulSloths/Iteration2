package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Models.Observers.DestroyableObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;
import com.vengeful.sloths.Utility.Coord;


/**
 * Created by John on 1/30/2016.
 */
public class TakeableItem extends MapItem implements ModelVisitable, ViewObservable{

    InventoryItem inventorpRep;
    private DestroyableObserver observer;

    public TakeableItem(String itemName, InventoryItem item, Coord location){
        this.setItemName(itemName);
        this.inventorpRep = item;
        this.setLocation(location);
    }

    //Does nothing
    public void interact(Entity entity){
        //maybe alert user he cannot move here
        //this.destroy = false;

        entity.pickup(this);
    }


    public InventoryItem getInventorpRep(){
        return this.inventorpRep;
    }

    public void alertObserverOnDestroy(){
        this.observer.alertDestroyed();
    }


    @Override
    public void registerObserver(ModelObserver modelObserver) {
        this.observer = (DestroyableObserver) modelObserver;
    }

    @Override
    public void deregisterObserver(ModelObserver modelObserver) {
        this.observer = null;
    }

    public void accept(ModelVisitor visitor) {
        visitor.visitTakeableItem(this);
    }

}
