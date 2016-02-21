package com.vengeful.sloths.Models.Stats.StatAddables;

/**
 * Created by John on 2/21/2016.
 */
public class AgilityAddable extends StatsAddable {
    private int strength = 0;
    private int agility = 0;
    private int intellect = 0;
    private int hardiness = 0;
    private int movement = 0;
    private int currentHealth = 0;
    private int bonusHealth = 0;
    private int currentMana = 0;
    private int bonusMana = 0;
    private int currentExperience = 0;

    public AgilityAddable(int agility){
        this.agility = agility;
    }
}
