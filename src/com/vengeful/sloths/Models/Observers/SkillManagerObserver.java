package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.Models.Skills.Skill;

/**
 * Created by zach on 3/12/16.
 */
public interface SkillManagerObserver extends ModelObserver {
    void alertSkillAdded(Skill skill);
    void alertSkillLevelUpdated(Skill skill);
    void alertAvailableSkillPointsUpdated(int availableSkillPoints);
}
