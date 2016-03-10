package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Utility.Location;
import com.vengeful.sloths.Models.Observers.DestroyableObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by John on 1/30/2016.
 */
public class OneShotItem extends MapItem implements ModelVisitable, ViewObservable{

    private DestroyableObserver observer;

    public OneShotItem(Coord location){
        this.setLocation(location);
    }

    public void interact(Entity entity){
        observer.alertDestroyed();
        Map.getInstance().getActiveMapArea().getTile(getLocation()).removeOneShotItem();
    }

    @Override
    public void registerObserver(ModelObserver modelObserver) {
        this.observer = (DestroyableObserver) modelObserver;
        ////System.out.Println("MAP ITEM OBSERVER REGISTERED: " + ((MapItemObserver)modelObserver).getClass().getSimpleName());
    }

    @Override
    public void deregisterObserver(ModelObserver modelObserver) {
        this.observer = null;

    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visitOneShotItem(this);
    }

}
