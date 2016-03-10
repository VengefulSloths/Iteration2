package com.vengeful.sloths.Models.Inventory;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.*;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.StatAddables.StrengthAddable;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Models.Observers.*;
import com.vengeful.sloths.Models.Stats.*;

import java.util.Iterator;
import java.util.*;

/**
 * Created by qianwen on 1/30/16.
 */
public class Equipped implements ViewObservable, ModelVisitable{

    private Entity entity;
    private Stats entityStats;
    private SkillManager skills;
    private AbilityManager abilityManager;

    private Hat hat;
    private final Weapon fists = new Knuckle("hands", new StrengthAddable(0), 0);

    private Weapon weapon = fists;

    //private EquippableItems mount;

    protected ArrayList<EquipmentObserver> equipmentObserver;

    public Equipped(Entity entity){
        this.hat = null;
        this.entity = entity;
        this.entityStats = entity.getStats();
        this.skills = entity.getSkillManager();
        this.abilityManager = entity.getAbilityManager();
        this.equipmentObserver = new ArrayList<>();
        addWeapon(fists);

    }

    public Equipped(){}
    public void init(Entity e){
        this.entity = e;
        this.entityStats = entity.getStats();
        this.skills = entity.getSkillManager();
        this.abilityManager = entity.getAbilityManager();
        this.equipmentObserver = new ArrayList<>();
        addWeapon(fists);
    }


    public void addHat(EquippableItems hat){
        if(this.hat != null)
            removeHat(this.hat);

        this.hat = (Hat) hat;
        this.entityStats.add(this.hat.getItemStats());

        for (EntityObserver observer: entity.getObservers()) {
            observer.alertEquipHat(hat.getItemName());
        }
        alertEquipped(hat);
    }

    public void addWeapon(EquippableItems weapon){
        this.entityStats.subtract(weapon.getItemStats());
        this.weapon = (Weapon) weapon;
        this.entityStats.add(this.weapon.getItemStats());
        this.entityStats.setOffensiveRating(getOffensiveRating());

        abilityManager.setWeaponAbility(this.weapon.getAttackAbility(entity));


        for (EntityObserver observer: entity.getObservers()) {
            observer.alertEquipWeapon(this.weapon.getItemName(), this.weapon.getWeaponClassification());
        }
        alertEquipped(weapon);
    }

    public int getOffensiveRating() {
        return weapon.calculateOffsensiveRating(entityStats, skills);
    }


    public void removeHat(EquippableItems hat){
        this.hat = null;
        this.entityStats.subtract(hat.getItemStats());

        for (EntityObserver observer: entity.getObservers()) {
            observer.alertUnequipHat();
        }
        alertUnEquipped(hat);
    }

    public void removeWeapon(EquippableItems weapon){
        this.weapon = fists;
        this.entityStats.subtract(weapon.getItemStats());
        this.entityStats.setOffensiveRating(getOffensiveRating());

        abilityManager.setWeaponAbility(this.weapon.getAttackAbility(entity));

        for (EntityObserver observer: entity.getObservers()) {
            observer.alertUnequipWeapon();
        }
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

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitEquipped(this);
    }

    public SkillManager getSkills() {
        return skills;
    }

    public void setSkills(SkillManager skills) {
        this.skills = skills;
    }

    public AbilityManager getAbilityManager() {
        return abilityManager;
    }

    public void setAbilityManager(AbilityManager abilityManager) {
        this.abilityManager = abilityManager;
    }
}
