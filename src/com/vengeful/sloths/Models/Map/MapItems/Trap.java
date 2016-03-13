package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
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
    private boolean detectable = true;
    //if avatar try to see a trap once and failed, let the trap be nondetectable for x seconds before avatar can try again

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
            affectedEntities.get(i).takeDamage(this.damageTaken);
        }

        if(this.affectedEntities.size() > 0){
            TimeModel.getInstance().registerAlertable(() -> {
                damageEntity();
            }, 20);
        }
    }

    public boolean destroy() {
        if(this.isVisible){
            for(DestroyableObserver observer: observers) {
                observer.alertDestroyed();
            }
            System.out.println("TRAP REMOVED");
            return true;
        }

        System.out.println("TRAP NOT VISIBLE");
        return false;
    }

    public boolean isVisible(){
        return this.isVisible;
    }

    public void makeVisible(){
        if(!this.isVisible){
            if(detectable){
                System.out.println("TRAP IS REVEALED");
                this.isVisible = true;

                //create trap visual
                TemporaryVOCreationVisitor creator = TemporaryVOCreationVisitor.getInstance();
                this.accept(creator);
            }else{
                System.out.println("SORRY TRAP IS UNDETECTABLE NOW");
            }
        }
    }

    public void makeUndetectable(){
        if(detectable){
            this.detectable = false;
            System.out.println("TRAP IS MADE UNDETECTABLE");
            TimeModel.getInstance().registerAlertable(() -> {
                this.detectable = true;
                System.out.println("TRAP IS DETECTABLE!!!!");
            }, 600); //make it undetectable for 10 seconds
        }
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
