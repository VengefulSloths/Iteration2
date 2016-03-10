package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.DestroyableObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Utility.Coord;
import sun.security.krb5.internal.crypto.Des;

import java.util.ArrayList;

/**
 * Created by John on 1/30/2016.
 */
public class Obstacle extends MapItem implements ViewObservable{

    private ArrayList<DestroyableObserver> observers = new ArrayList<>();

    public Obstacle(Coord location){
        this.setLocation(location);
    }

    public void destroy() {
        for(DestroyableObserver observer: observers) {
            observer.alertDestroyed();
        }
    }

    @Override
    public boolean canMove(){
        return false;
    }

    public void interact(Entity entity){
        //maybe alert user he cannot move here
    }

    public void accept(ModelVisitor visitor) {
        visitor.visitObstacle(this);
    }

    @Override
    public void registerObserver(ModelObserver observer) {
        this.observers.add((DestroyableObserver) observer);
    }

    @Override
    public void deregisterObserver(ModelObserver observer) {
        this.observers.remove(observer);
    }

//    public void saveMe(SaveManager sm, int ws) {
//        sm.writeClassLine(ws, "Obstacle");
//        super.saveMe(sm, ws);
//    }
}
