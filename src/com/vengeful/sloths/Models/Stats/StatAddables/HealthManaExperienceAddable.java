package com.vengeful.sloths.Models.Stats.StatAddables;

/**
 * Created by John on 2/21/2016.
 */
public class HealthManaExperienceAddable extends StatsAddable {

    public HealthManaExperienceAddable(int currentHealth, int bonusHealth, int currentMana, int bonusMana, int currentExperience){
        this.setCurrentHealth(currentHealth);
        this.setBonusHealth(bonusHealth);
        this.setBonusMana(currentMana);
        this.setBonusMana(bonusMana);
        this.setCurrentExperience(currentExperience);
    }
}
