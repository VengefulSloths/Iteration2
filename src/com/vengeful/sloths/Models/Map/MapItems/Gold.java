package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.DestroyableObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by harrison on 3/11/16.
 */
public class Gold extends MapItem implements ModelVisitable, ViewObservable {
    private int value;
    private DestroyableObserver observer;

    public Gold(int value, Coord c){
        this.setItemName("gold");
        this.value = value;
        this.setLocation(c);
    }
    public  Gold(){
        this.setItemName("gold");
        this.value = 0;
    }
    public Gold(int value){
        this.setItemName("gold");
        this.value = value;
    }
    @Override
    public void interact(Entity entity) {
        entity.getGold(this);
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitGold(this);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addGold(Gold gold){
        this.value += gold.getValue();
    }

    public void alertObserverOnDestroy(){
        if(observer != null)this.observer.alertDestroyed();
    }


    @Override
    public void registerObserver(ModelObserver modelObserver) {
        this.observer = (DestroyableObserver) modelObserver;
    }

    @Override
    public void deregisterObserver(ModelObserver modelObserver) {
        this.observer = null;
    }
}
