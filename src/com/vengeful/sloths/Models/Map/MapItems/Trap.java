package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.DestroyableObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Utility.Coord;

import java.util.ArrayList;

/**
 * Created by luluding on 3/11/16.
 */
public class Trap extends MapItem implements ModelVisitable, ViewObservable{

    private ArrayList<DestroyableObserver> observers = new ArrayList<>();
    private ArrayList<Entity> affectedEntities = new ArrayList<>();
    private int damageTaken;
    private boolean isVisible = false;

    public Trap(Coord location){
        this.setLocation(location);
        this.damageTaken = 1;
    }

    public Trap(Coord location, int damage){
        this.setLocation(location);
        this.damageTaken = damage;
    }

    @Override
    public void interact(Entity entity) {
        this.affectedEntities.add(entity);
        this.damageEntity();
    }


    private void damageEntity(){
        for(int i = 0; i < affectedEntities.size(); i++){
            affectedEntities.get(0).takeDamage(this.damageTaken);
        }

        if(this.affectedEntities.size() > 0){
            TimeModel.getInstance().registerAlertable(() -> {
                damageEntity();
            }, 20);
        }
    }

    public void destroy() {
        for(DestroyableObserver observer: observers) {
            observer.alertDestroyed();
        }
        System.out.println("TRAP REMOVED");

        //TODO: have a visibility variable. Remove only if its visible
    }

    public boolean isVisible(){
        return this.isVisible;
    }

    public void makeVisible(){
        this.isVisible = true;
        //alert view
    }


    public void removeEntity(Entity entity) {
        this.affectedEntities.remove(entity);
    }

    public ArrayList<Entity> getEntities() {
        return affectedEntities;
    }

    @Override
    public void registerObserver(ModelObserver observer) {
        this.observers.add((DestroyableObserver) observer);
    }

    @Override
    public void deregisterObserver(ModelObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitTrap(this);
    }

    public int getDamageTaken() {
        return damageTaken;
    }

    public void setDamageTaken(int damageTaken) {
        this.damageTaken = damageTaken;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
