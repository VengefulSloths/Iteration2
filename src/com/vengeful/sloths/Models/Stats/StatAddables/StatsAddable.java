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

    public void accept(ModelVisitor modelVisitor){
        modelVisitor.visitStatsAddable(this);
    }

}
