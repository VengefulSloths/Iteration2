package com.vengeful.sloths.Models.Stats.StatAddables;

/**
 * Created by John on 2/21/2016.
 */
public abstract class StatsAddable {

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
}
