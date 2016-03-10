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
    public GenericStatsAddable(){}
    public void accept(ModelVisitor mv){
        super.accept(mv);
    }
}
