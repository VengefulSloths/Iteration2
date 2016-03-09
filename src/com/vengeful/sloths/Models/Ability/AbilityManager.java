package com.vengeful.sloths.Models.Ability;

import com.vengeful.sloths.AreaView.vAlertable;
import com.vengeful.sloths.AreaView.vCommand;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Utility.Tuple;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by luluding on 2/21/16.
 */
public class AbilityManager implements ModelVisitable{

    private ArrayList<Ability> abilities;
    private Ability[] activeAbilities;
    private Ability weaponAbility; //set when weapon is equipped.
    //TODO: give it a default: punchAbility


    public AbilityManager(){
        this.abilities = new ArrayList<>();
        this.activeAbilities = new Ability[4]; //3 for occupation specific, 1 for common ability
    }

    public void addAbility(Ability ability){
        this.abilities.add(ability);
    }

    public boolean doAbility(int index){
        if(index < 0 || index >= activeAbilities.length)
            return false;

        if(this.activeAbilities[index] == null)
            return false;

        this.activeAbilities[index].execute();
        return true;
    }

    public boolean equipAbility(Ability ability, int index){
        if(index < 0 || index >= activeAbilities.length)
            return false;

        this.activeAbilities[index] = ability;
        return true;
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
}
