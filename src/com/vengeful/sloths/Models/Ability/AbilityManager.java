package com.vengeful.sloths.Models.Ability;

import com.vengeful.sloths.Models.Ability.Abilities.NullAbility;
import com.vengeful.sloths.Models.Ability.Hooks.Hook;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.AbilityManagerObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;
import com.vengeful.sloths.Models.ViewObservable;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by luluding on 2/21/16.
 */
public class AbilityManager implements ModelVisitable, ViewObservable {

    private ArrayList<Ability> abilities;
    private Ability[] activeAbilities;
    private Ability weaponAbility; //set when weapon is equipped.
    private Ability mountAbility; //set when mount is equipped


    private ArrayList<Hook> castAbilityHooks = new ArrayList<>();

    private ArrayList<AbilityManagerObserver> abilityManagerObservers;


    //TODO: give it a default: punchAbility
    private Entity entity;


    public AbilityManager(Entity entity){
        this.abilities = new ArrayList<>();
        this.activeAbilities = new Ability[4]; //3 for occupation specific, 1 for common ability
        this.abilityManagerObservers = new ArrayList<>();
        for(int i = 0; i != activeAbilities.length; ++i){
            activeAbilities[i] = new NullAbility();
        }
        this.entity = entity;
    }

    public int getCurrentSize() {
        return this.abilities.size();
    }

    public void addAbility(Ability ability){
        this.abilities.add(ability);

        Iterator<AbilityManagerObserver> iter = this.abilityManagerObservers.iterator();
        while (iter.hasNext()) {
            AbilityManagerObserver abo = iter.next();
            abo.alertAbilityAdded(ability);
        }
    }

    public boolean doAbility(int index){
        doAbilityHooks();

        if(index < 0 || index >= activeAbilities.length)
            return false;

        if(this.activeAbilities[index] == null)
            return false;

        this.activeAbilities[index].execute();
        return true;
    }

    public int doMountAbility() {
        doAbilityHooks();
        return mountAbility.execute();
    }

    public int doWeaponAbility(){
        doAbilityHooks();
        return this.weaponAbility.execute();
    }

    public boolean equipAbility(Ability ability, int index){
        if(index < 0 || index >= activeAbilities.length)
            return false;

        this.activeAbilities[index] = ability;

        Iterator<AbilityManagerObserver> iter = this.abilityManagerObservers.iterator();
        while (iter.hasNext()) {
            AbilityManagerObserver abo = iter.next();
            abo.alertAbilityEquipped(ability);
        }

        return true;
    }

    public void setMountAbility(Ability mountAbility) {
        this.mountAbility = mountAbility;
    }

    public Ability getMountAbility() {
        return mountAbility;
    }


    public Ability getWeaponAbility(){
        return this.weaponAbility;
    }

    public void setWeaponAbility(Ability weaponAbility){
        this.weaponAbility = weaponAbility;
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitAbilityManager(this);
    }

    public Ability[] getAbilities(){
        Ability[] abArray = new Ability[abilities.size()];
        int i = 0;
        for(Ability ab : abilities){
            abArray[i] = ab;
            ++i;
        }
        return abArray;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Ability getAbilityByIndex(int index) {
        if (index < 0 || index >= this.abilities.size())
            return null;

        return this.abilities.get(index);
    }

    public Ability getAbility(String abilityName){
        for(Ability ab : abilities){
            if(ab.toString().equals(abilityName)){
                return ab;
            }
        }
        return null;
    }
    public Ability[] getActiveAbilities(){
        return activeAbilities;
    }


    public void addAbilityHook(Hook hook) {
        this.castAbilityHooks.add(hook);
    }

    public void removeAbilityHook(Hook hook) {
        if (castAbilityHooks.contains(hook)) {
            castAbilityHooks.remove(hook);
        }
    }

    private void doAbilityHooks() {
        for (Hook hook : castAbilityHooks) {
            hook.execute(this);
        }
    }
    @Override
    public void registerObserver(ModelObserver modelObserver) {
        this.abilityManagerObservers.add((AbilityManagerObserver) modelObserver);
    }

    @Override
    public void deregisterObserver(ModelObserver modelObserver) {
        this.abilityManagerObservers.remove(modelObserver);
    }
}
