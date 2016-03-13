package com.vengeful.sloths.Models.Inventory;

import com.vengeful.sloths.Models.Ability.Abilities.NullAbility;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.*;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.GenericStatsAddable;
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

    private Mount mount;

    //private EquippableItems mount;

    protected ArrayList<EquipmentObserver> equipmentObserver;

    public Hat getHat() {
        return hat;
    }
    public void setHat(Hat hat) {
        this.hat = hat;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public Mount getMount() {
        return mount;
    }
    public void setMount(Mount mount) {
        this.mount = mount;
    }

    public Equipped(Entity entity){
        this.hat = null;
        this.mount = null;
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


    public void addMount(Mount mount) {
        if (this.mount != null) {
            removeMount();
        }

        this.mount = mount;
        this.abilityManager.setMountAbility(mount.getMountAbility(entity));
        //TODO: alert observers
    }


    public void addHat(Hat hat){
        if(this.hat != null)
            removeHat(this.hat);

        this.hat = hat;
        this.entityStats.add(this.hat.getItemStats());

        for (EntityObserver observer: entity.getObservers()) {
            observer.alertEquipHat(hat.getItemName());
        }
        alertHatEquipped(hat);
    }

    public void addWeapon(Weapon weapon){
        this.entityStats.subtract(this.weapon.getItemStats());
        this.weapon = weapon;
        this.entityStats.add(this.weapon.getItemStats());
        this.entityStats.setOffensiveRating(getOffensiveRating());

        abilityManager.setWeaponAbility(this.weapon.getAttackAbility(entity));


        for (EntityObserver observer: entity.getObservers()) {
            observer.alertEquipWeapon(this.weapon.getItemName(), this.weapon.getWeaponClassification());
        }
        alertWeaponEquipped(weapon);
    }


    public void removeMount() {
        abilityManager.setMountAbility(new NullAbility());

        //TODO: alert equipped view that mount is equiped
    }

    public int getOffensiveRating() {
        return weapon.calculateOffsensiveRating(entityStats, skills);
    }


    public void removeHat(Hat hat){
        this.hat = null;
        this.entityStats.subtract(hat.getItemStats());

        for (EntityObserver observer: entity.getObservers()) {
            observer.alertUnequipHat();
        }
        alertHatEquipped(hat);
    }

    public void removeWeapon(Weapon weapon){
        this.weapon = fists;
        this.entityStats.subtract(weapon.getItemStats());
        this.entityStats.setOffensiveRating(getOffensiveRating());

        abilityManager.setWeaponAbility(this.weapon.getAttackAbility(entity));

        for (EntityObserver observer: entity.getObservers()) {
            observer.alertUnequipWeapon();
        }
        alertWeaponUnequipped(weapon);
    }

    public void alertAllEntityObserversEverything() {
        for (EntityObserver observer: entity.getObservers()) {
            if (this.hat != null) {
                observer.alertEquipHat(this.hat.getItemName());
            }
            if (this.weapon != null && this.weapon != fists) {
                observer.alertEquipWeapon(this.weapon.getItemName(), this.weapon.getWeaponClassification());
            }
        }
    }


    public void alertHatEquipped(Hat hat){
        Iterator<EquipmentObserver> iter = this.equipmentObserver.iterator();
        while (iter.hasNext()) {
            EquipmentObserver io = iter.next();
            io.alertHatEquipped(hat);
        }
    }

    public void alertHatUnequipped(Hat hat){
        Iterator<EquipmentObserver> iter = this.equipmentObserver.iterator();
        while (iter.hasNext()) {
            EquipmentObserver io = iter.next();
            io.alertHatUnequipped(hat);
        }
    }

    public void alertWeaponEquipped(Weapon weapon){
        Iterator<EquipmentObserver> iter = this.equipmentObserver.iterator();
        while (iter.hasNext()) {
            EquipmentObserver io = iter.next();
            io.alertWeaponEquipped(weapon);
        }
    }

    public void alertWeaponUnequipped(Weapon weapon){
        Iterator<EquipmentObserver> iter = this.equipmentObserver.iterator();
        while (iter.hasNext()) {
            EquipmentObserver io = iter.next();
            io.alertWeaponUnequipped(weapon);
        }
    }

    public void alertMountEquipped(Mount mount){
        Iterator<EquipmentObserver> iter = this.equipmentObserver.iterator();
        while (iter.hasNext()) {
            EquipmentObserver io = iter.next();
            io.alertMountEquipped(mount);
        }
    }

    public void alertMountUnequipped(Mount mount){
        Iterator<EquipmentObserver> iter = this.equipmentObserver.iterator();
        while (iter.hasNext()) {
            EquipmentObserver io = iter.next();
            io.alertMountUnequipped(mount);
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

    public GenericStatsAddable getAllEquippedStatEffects(){
        //@ToDO FINISH WRITING
        GenericStatsAddable gsa = new GenericStatsAddable();
        if(hat != null){
            gsa.add(hat.getItemStats());
        }
        if(weapon != null){
            gsa.add(weapon.getItemStats());
        }
        return gsa;
    }
}
