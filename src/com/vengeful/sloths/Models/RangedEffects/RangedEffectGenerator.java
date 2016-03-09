package com.vengeful.sloths.Models.RangedEffects;

/**
 * Created by luluding on 3/8/16.
 */
public abstract class RangedEffectGenerator {

    //fog of war
    public static final int NUM_VISIBLE_TILES = 5; //used to calculate decreasing att and accuracy

    public abstract void createRangedEffect();

    public abstract void tickAlert();

    public int calculateAtt(int initialDmg, int currentRadius){
        int calculatedDmg = initialDmg - (int)(initialDmg * (currentRadius-1) * calculateDecreasingFactor());
        return calculatedDmg;
    }

    public int calculateAccuracy(int initialAcc, int currentRadius){
        int calculatedAcc = initialAcc - (int)(initialAcc * (currentRadius-1) * calculateDecreasingFactor());
        return calculatedAcc;
    }

    public double calculateDecreasingFactor(){
        return 1.0 / NUM_VISIBLE_TILES; //percent to decrease every tile travels
    }
}
