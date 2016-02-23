package com.vengeful.sloths.Models.Inventory;

import com.vengeful.sloths.Models.InventoryItems.EquippableItems.*;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.View.Observers.*;
import com.vengeful.sloths.Models.Stats.*;

import java.util.Iterator;
import java.util.*;

/**
 * Created by qianwen on 1/30/16.
 */
public class Equipped implements ViewObservable{

    private Stats entityStats;

    private EquippableItems hat;
    private EquippableItems weapon;
    //private EquippableItems mount;

    protected ArrayList<EquipmentObserver> equipmentObserver;

    public Equipped(Stats entityStats){
        this.hat = null;
        this.weapon = null;
        this.entityStats = entityStats;
        this.equipmentObserver = new ArrayList<EquipmentObserver>();
    }


    public void addHat(EquippableItems hat){
        if(this.hat != null)
            removeHat(this.hat);

        this.hat = hat;
        this.entityStats.add(this.hat.getItemStats());
        alertEquipped(hat);
    }

    public void addWeapon(EquippableItems weapon){
        if(this.weapon != null)
            removeWeapon(this.weapon);

        this.weapon = weapon;
        this.entityStats.add(this.weapon.getItemStats());
        alertEquipped(weapon);
    }

    public void removeHat(EquippableItems hat){
        this.hat = null;
        this.entityStats.subtract(hat.getItemStats());
        alertUnEquipped(hat);
    }

    public void removeWeapon(EquippableItems weapon){
        this.weapon = null;
        this.entityStats.subtract(weapon.getItemStats());
        alertUnEquipped(weapon);
    }


    //need getter and setter for mount


    public EquippableItems getHat(){
        return this.hat;
    }

    public EquippableItems getWeapon(){
        return this.weapon;
    }


    public void alertEquipped(EquippableItems item){
        Iterator<EquipmentObserver> iter = this.equipmentObserver.iterator();
        while (iter.hasNext()) {
            EquipmentObserver io = iter.next();
            io.alertItemEquipped(item);
        }
    }

    public void alertUnEquipped(EquippableItems item){
        Iterator<EquipmentObserver> iter = this.equipmentObserver.iterator();
        while (iter.hasNext()) {
            EquipmentObserver io = iter.next();
            io.alertItemUnequipped(item);
        }
    }

    @Override
    public void deregisterObserver(ModelObserver modelObserver) {
        this.equipmentObserver.remove(modelObserver);
    }

    @Override
    public void registerObserver(ModelObserver modelObserver) {
        this.equipmentObserver.add((EquipmentObserver) modelObserver);
    }

}
