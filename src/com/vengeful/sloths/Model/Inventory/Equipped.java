package com.vengeful.sloths.Model.Inventory;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by qianwen on 1/30/16.
 */
public class Equipped{

    /*
    private Hat hat;
    private Sword sword;

    protected ArrayList<EquipmentObserver> equipmentObserver;

    public Equipped(){
        this.hat = null;
        this.sword = null;
        this.equipmentObserver = new ArrayList<EquipmentObserver>();
    }


    public boolean addEquipped(EquippableItems item){

        if(item instanceof Hat){
            this.hat = (Hat)item;
        }else if(item instanceof Sword){
            this.sword = (Sword)item;
        }else{
            return false;
        }


        Iterator<EquipmentObserver> iter = this.equipmentObserver.iterator();
        while (iter.hasNext()) {
            EquipmentObserver io = iter.next();
            io.alertItemEquipped(item);
        }

        return true;

    }

    public boolean removeEquipped(EquippableItems item){
        if(item instanceof Hat)
            this.hat = null;
        else if(item instanceof Sword)
            this.sword = null;
        else{
            return false;
        }

        Iterator<EquipmentObserver> iter = this.equipmentObserver.iterator();
        while (iter.hasNext()) {
            EquipmentObserver io = iter.next();
            io.alertItemUnequipped(item);
        }

        return true;

    }

    //If equipment already equipped, then return the current equipped item
    //Else return null
    public InventoryItem alreadyEquipped(EquippableItems item){
        if(item instanceof Hat){
            if(this.hat != null)
                return this.hat;

        }else if(item instanceof Sword){
            if(this.sword != null)
                return this.sword;

        }else{
            return null;
        }
        return null;

    }


    public Hat getHat(){
        return this.hat;
    }


    public Sword getSword(){
        return this.sword;
    }


    @Override
    public void deregisterObserver(ModelObserver modelObserver) {
        this.equipmentObserver.remove(modelObserver);
    }

    @Override
    public void registerObserver(ModelObserver modelObserver) {
        this.equipmentObserver.add((EquipmentObserver) modelObserver);
    }*/

}
