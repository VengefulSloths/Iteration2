package com.vengeful.sloths.Models.Stats.StatAddables;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 2/21/2016.
 */
public class GenericStatsAddable extends StatsAddable implements ModelVisitable {

    public GenericStatsAddable(int strength, int agility, int intellect, int hardiness, int movement, int currentHealth, int bonusHealth, int currentMana, int bonusMana, int currentExperience){
        this.setStrength(strength);
        this.setAgility(agility);
        this.setIntellect(intellect);
        this.setHardiness(hardiness);
        this.setMovement(movement);
        this.setCurrentHealth(currentHealth);
        this.setBonusHealth(bonusHealth);
        this.setCurrentMana(currentMana);
        this.setBonusMana(bonusMana);
        this.setCurrentExperience(currentExperience);
    }
    public GenericStatsAddable(){
        this.setStrength(0);
        this.setAgility(0);
        this.setIntellect(0);
        this.setHardiness(0);
        this.setMovement(0);
        this.setCurrentHealth(0);
        this.setBonusHealth(0);
        this.setCurrentMana(0);
        this.setBonusMana(0);
        this.setCurrentExperience(0);
    }
    public void accept(ModelVisitor mv){
        super.accept(mv);
    }
}
