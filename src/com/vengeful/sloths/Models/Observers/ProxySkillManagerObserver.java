package com.vengeful.sloths.Models.Observers;

import com.vengeful.sloths.AreaView.ViewTime;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.ViewObservable;

/**
 * Created by zach on 3/12/16.
 */
public class ProxySkillManagerObserver extends ProxyObserver implements SkillManagerObserver {
    private SkillManagerObserver target;

    public ProxySkillManagerObserver(SkillManagerObserver skillManagerObserver, ViewObservable subject) {
        this.subject = subject;
        System.out.println("Subject: " + subject);
        this.subject.registerObserver(this);
        this.target = skillManagerObserver;
    }

    @Override
    public ModelObserver getModelObserver() {
        return target;
    }

    @Override
    public void alertSkillAdded(Skill skill) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertSkillAdded(skill));
        }
    }

    @Override
    public void alertSkillLevelUpdated(Skill skill) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertSkillLevelUpdated(skill));
        }

    }

    @Override
    public void alertAvailableSkillPointsUpdated(int availableSkillPoints) {
        if (!deleteFlag) {
            ViewTime.getInstance().registerAlert(0, () -> target.alertAvailableSkillPointsUpdated(availableSkillPoints));
        }
    }
}
