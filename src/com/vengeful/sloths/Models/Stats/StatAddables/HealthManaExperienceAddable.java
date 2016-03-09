package com.vengeful.sloths.Models.Stats.StatAddables;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 2/21/2016.
 */
public class HealthManaExperienceAddable extends StatsAddable implements ModelVisitable {

    public HealthManaExperienceAddable(int currentHealth, int bonusHealth, int currentMana, int bonusMana, int currentExperience){
        this.setCurrentHealth(currentHealth);
        this.setBonusHealth(bonusHealth);
        this.setCurrentMana(currentMana);
        this.setBonusMana(bonusMana);
        this.setCurrentExperience(currentExperience);
    }

    public void accept(ModelVisitor mv){
        super.accept(mv);
    }
}
