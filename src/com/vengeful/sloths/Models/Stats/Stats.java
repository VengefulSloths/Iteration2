package com.vengeful.sloths.Models.Stats;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;
import com.vengeful.sloths.Models.Observers.StatsObserver;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 2/21/2016.
 */
public class Stats implements ModelVisitable, ViewObservable {

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

    private int offensiveRating = 0;
    private int defensiveRating;
    private int armorRating;

    private int bonusHealth = 0;
    private int bonusMana = 0;

    private Entity entity;

    private ArrayList<StatsObserver> observers = new ArrayList<>();

    public Stats(){
        this.strength = 1;
        this.agility = 1;
        this.intellect = 1;
        this.hardiness = 1;
        this.movement = 45;//might need to modify this (done)
        this.level = 1;
        this.currentHealth = 1;
        calculateStats();

        this.currentHealth = this.maxHealth;
        this.currentMana = this.maxMana;
        this.currentExperience = 0;
    }
    public Stats(StatsAddable stats){
        setStrength(stats.getStrength());
        setAgility(stats.getAgility());
        setIntellect(stats.getIntellect());
        setHardiness(stats.getHardiness());
        setMovement(stats.getMovement());
        this.level = 1;

        calculateStats();

        setCurrentHealth(this.maxHealth);
        setCurrentMana(this.maxMana);
        setCurrentExperience(0);
    }

    public void calculateStats(){
        calculateMaxMana();
        calculateMaxHealth();
        calculateMaxExperience();
        calculateDefensiveRating();
        calculateArmorRating();
        checkIfDead();
    }
    private void calculateMaxMana(){
        this.maxMana = (1 + this.level + (3*this.intellect)) + bonusMana;
    }
    private void calculateMaxHealth(){
        this.maxHealth = (1 + this.level + (2*this.hardiness)) + bonusHealth;
    }
    private void calculateMaxExperience(){
        this.maxExperience = (int)Math.pow(((double)this.level), 2); //calculates experience to next level as level^2
    }
    public void setOffensiveRating(int offensiveRating){
        this.offensiveRating = offensiveRating;
    }
    private void calculateDefensiveRating(){
        this.defensiveRating = (1 + 2*this.level + (3*this.agility));
    }
    private void calculateArmorRating(){
        this.armorRating = (1 + 2*this.level + (3*this.hardiness));
    }

    private void checkIfDead(){
        if(this.entity != null) {
            if (currentHealth <= 0) {
                this.entity.die();
            }
        }
    }
    ////////////////////////////// public getters/setters //////////////////////////////


    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void resetStats() {
        setCurrentHealth(maxHealth);
        setCurrentMana(maxMana);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
        calculateStats();
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
        calculateStats();
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
        calculateStats();
    }

    public int getHardiness() {
        return hardiness;
    }

    public void setHardiness(int hardiness) {
        this.hardiness = hardiness;
        calculateStats();
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
        calculateStats();
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public void setCurrentExperience(int currentExperience) {
        if(currentExperience >= 0) {
            this.currentExperience = currentExperience;
        }else{
            this.currentExperience = 0;
        }
        if(currentExperience >= maxExperience){
            this.setLevel(this.level + 1);
            calculateStats();
            setCurrentExperience(currentExperience - maxExperience);
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(level > 0) {
            this.level = level;
        }
        calculateStats();
        this.currentHealth = maxHealth;
        this.currentMana = maxMana;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        if(currentMana >= 0 && currentMana <= maxMana) {
            this.currentMana = currentMana;
        }else if(currentMana >= this.maxMana){
            this.currentMana = this.maxMana;
        }else{
            this.currentMana = 0;
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {

        if(currentHealth >= 0 && currentHealth <= maxHealth) {
            this.currentHealth = currentHealth;
        }else if(currentHealth >= this.maxHealth){
            this.currentHealth = this.maxHealth;
        }else{
            this.currentHealth = 0;
        }
    }

    public int getMaxExperience(){
        return this.maxExperience;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getOffensiveRating() {
        return offensiveRating;
    }

    public int getDefensiveRating() {
        return defensiveRating;
    }

    public int getArmorRating() {
        return armorRating;
    }

    public int getBonusHealth() {
        return bonusHealth;
    }

    public int getBonusMana() {
        return bonusMana;
    }

    public ArrayList<StatsObserver> getObservers() {
        return observers;
    }

    /////////////////////////// public api ////////////////////////////////////
    public void add(StatsAddable stats){
        setStrength(this.strength + stats.getStrength());
        setAgility(this.agility + stats.getAgility());
        setIntellect(this.intellect + stats.getIntellect());
        setHardiness(this.hardiness + stats.getHardiness());
        setMovement(this.movement + stats.getMovement());

        this.bonusHealth += stats.getBonusHealth();
        this.bonusMana += stats.getBonusMana();

        setCurrentHealth(this.currentHealth + stats.getCurrentHealth());
        setCurrentMana(this.currentMana + stats.getCurrentMana());
        setCurrentExperience(this.currentExperience + stats.getCurrentExperience());

        calculateStats();
        updateObservers();
    }

    public void subtract(StatsAddable stats){
        setStrength(this.strength - stats.getStrength());
        setAgility(this.agility - stats.getAgility());
        setIntellect(this.intellect - stats.getIntellect());
        setHardiness(this.hardiness - stats.getHardiness());
        setMovement(this.movement - stats.getMovement());

        this.bonusHealth -= stats.getBonusHealth();
        this.bonusMana -= stats.getBonusMana();

        setCurrentHealth(this.currentHealth - stats.getCurrentHealth());
        setCurrentMana(this.currentMana - stats.getCurrentMana());
        setCurrentExperience(this.currentExperience - stats.getCurrentExperience());

        calculateStats();
        updateObservers();
    }

    @Override
    public void registerObserver(ModelObserver observer) {
            this.observers.add((StatsObserver) observer);
    }

    @Override
    public void deregisterObserver(ModelObserver observer) {
        this.observers.remove(observer);
    }
    public void updateObservers(){
        Iterator<StatsObserver> statsObserverIterator = observers.iterator();
        while (statsObserverIterator.hasNext()) {
            statsObserverIterator.next().alertStatChanged(this);
        }
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitStats(this);
    }
}
