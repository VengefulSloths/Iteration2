package com.vengeful.sloths.Models.Stats;

import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;

/**
 * Created by John on 2/21/2016.
 */
public class Stats {

    private int strength;
    private int agility;
    private int intellect;
    private int hardiness;
    private int movement;

    private int currentExperience;
    private int maxExperience;
    private int level;

    private int currentHealth;
    private int maxHealth;

    private int currentMana;
    private int maxMana;

    private int offensiveRating;
    private int defensiveRating;
    private int armorRating;

    public Stats(){
        this.strength = 1;
        this.agility = 1;
        this.intellect = 1;
        this.hardiness = 1;
        this.movement = 1;//might need to modify this
        this.level = 1;
        calculateStats();

        this.currentHealth = this.maxHealth;
        this.currentMana = this.maxMana;
        this.currentExperience = 0;
    }
    public Stats(StatsAddable stats){
        this.strength = stats.getStrength();
        this.agility = stats.getAgility();
        this.intellect = stats.getIntellect();
        this.hardiness = stats.getHardiness();
        this.movement = stats.getMovement();
        this.level = 1;

        calculateStats();

        this.currentHealth = this.maxHealth;
        this.currentMana = this.maxMana;
        this.currentExperience = 0;
    }

    private void calculateStats(){
        calculateMaxMana();
        calculateMaxHealth();
        calculateMaxExperience();
        calculateOffensiveRating();
        calculateDefensiveRating();
        calculateArmorRating();
    }
    private void calculateMaxMana(){
        this.maxMana = (1 + this.level + (3*this.intellect));
    }
    private void calculateMaxHealth(){
        this.maxHealth = (1 + this.level + (2*this.hardiness));
    }
    private void calculateMaxExperience(){
        this.maxExperience = (int)Math.pow(((double)this.level), 2); //calculates experience to next level as level^2
    }
    private void calculateOffensiveRating(){
        this.offensiveRating = (1 + 2*this.level + (3*this.strength));
    }
    private void calculateDefensiveRating(){
        this.defensiveRating = (1 + 2*this.level + (3*this.agility));
    }
    private void calculateArmorRating(){
        this.armorRating = (1 + 2*this.level + (3*this.hardiness));
    }

    ////////////////////////////// public api //////////////////////////////


    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public int getHardiness() {
        return hardiness;
    }

    public void setHardiness(int hardiness) {
        this.hardiness = hardiness;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public void setCurrentExperience(int currentExperience) {
        this.currentExperience = currentExperience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
}
