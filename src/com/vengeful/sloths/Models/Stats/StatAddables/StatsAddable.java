package com.vengeful.sloths.Models.Stats.StatAddables;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 2/21/2016.
 */
public abstract class StatsAddable implements ModelVisitable{

    private int strength;
    private int agility;
    private int intellect;
    private int hardiness;
    private int movement;
    private int currentHealth;
    private int bonusHealth;
    private int currentMana;
    private int bonusMana;
    private int currentExperience;

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntellect() {
        return intellect;
    }

    public int getHardiness() {
        return hardiness;
    }

    public int getMovement() {
        return movement;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getBonusHealth() {
        return bonusHealth;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public int getBonusMana() {
        return bonusMana;
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public void setHardiness(int hardiness) {
        this.hardiness = hardiness;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setBonusHealth(int bonusHealth) {
        this.bonusHealth = bonusHealth;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public void setBonusMana(int bonusMana) {
        this.bonusMana = bonusMana;
    }

    public void setCurrentExperience(int currentExperience) {
        this.currentExperience = currentExperience;
    }

    public void add(StatsAddable other){
        this.strength = this.strength + other.getStrength();
        this.agility = this.agility + other.getAgility();
        this.intellect = this.intellect + other.getIntellect();
        this.hardiness = this.hardiness + other.getHardiness();
        this.movement = this.movement + other.getMovement();
        this.currentHealth = this.currentHealth + other.getCurrentHealth();
        this.bonusHealth = this.bonusHealth +other.getBonusHealth();
        this.currentMana = this.currentMana + other.getCurrentMana();
        this.bonusMana = this.bonusMana + other.getBonusMana();
        this.currentExperience = this.currentExperience + other.getCurrentExperience();
    }

    public void invert(){
        if(this.strength != 0){
            this.strength = 0-this.strength;
        }
        if(this.agility != 0){
            this.agility = 0-this.agility;
        }
        if(this.intellect != 0){
            this.intellect = 0-this.intellect;
        }
        if(this.hardiness != 0){
            this.hardiness = 0-this.hardiness;
        }
        if(this.movement != 0){
            this.movement = 0-this.movement;
        }
        if(this.currentHealth != 0){
            this.currentHealth = 0-this.currentHealth;
        }
        if(this.bonusHealth !=0){
            this.bonusHealth = 0-this.bonusHealth;
        }
        if(this.currentMana != 0){
            this.currentMana = 0- this.currentMana;
        }
        if(this.bonusMana != 0){
            this.bonusMana = 0-this.bonusMana;
        }
        if(this.currentExperience != 0){
            this.currentExperience = 0-this.currentExperience;
        }
    }

    public void accept(ModelVisitor modelVisitor){
        modelVisitor.visitStatsAddable(this);
    }

}
