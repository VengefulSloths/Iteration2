package com.vengeful.sloths.Models.RangedEffects.HitBox;

import com.sun.javafx.geom.Path2D;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Models.Map.*;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Models.Entity.*;

import java.util.Iterator;

/**
 * Created by luluding on 3/8/16.
 */
public class HitBox {

    private String name;
    private Coord location;
    private boolean isActive = false;
    private int damage;
    private int accuracy;

    public HitBox(String name, Coord location, int dmg, int accuracy){
        this.name = name;
        this.location = location;
        this.damage = dmg;
        this.accuracy = accuracy;
    }

    public boolean takeDamage(){

        System.out.println("=========HIT BOX==========");
        System.out.println("My location: " + this.location.getR() + ", " + this.location.getS());
        System.out.println("My damage now: " + this.damage);
        System.out.println("My accuracy now: " + this.accuracy);

        System.out.println("==========================");

        if(!shouldTakeDmg())
            return false;


        boolean foundTarget = false;

        try {
            for (Entity entity : Map.getInstance().getActiveMapArea().getTile(this.location).getEntities()) {
                System.out.println("HITBOX FOUND AN ENTITY!!" + entity.getName());
                entity.takeDamage(this.damage);
                foundTarget = true;
            }
        } catch (Exception e) {
            //do nothing its fine
        }


        /* throws java.util.ConcurrentModificationException
        Iterator<Entity> iterator = Map.getInstance().getActiveMapArea().getTile(this.location).getEntityIterator();
        while(iterator.hasNext()){
                System.out.println("TAKING DAMAGE BY FIREBALL");
                iterator.next().takeDamage(this.damage);
                foundTarget = true;
        }*/

        return foundTarget;
    }

    //calculate based on accuracy
    //initial accuracy always 100
    private boolean shouldTakeDmg(){
        int randomNum = 1 + (int)(Math.random() * 101); //[1-100]
        if(randomNum <= this.accuracy){
            System.out.println("SHOULD TAKE DMG");
            return true;
        }
        System.out.println("SHOULD NOT TAKE DMG");
        return false;
    }


    //Alert view observer


    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Coord getLocation(){
        return this.location;
    }

    public void setLocation(Coord location){
        this.location = location;
    }

    public boolean isActive(){
        return this.isActive;
    }

    public void setActive(boolean active){
        this.isActive = active;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

}
