package com.vengeful.sloths.Views.SkillsView;

import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zach on 3/12/16.
 */
public class SkillsViewObjectFactory {

    public ArrayList<SkillsViewObject> generateSkillsViewObjects(SkillManager skillManager) {
        ArrayList<SkillsViewObject> tmpList = new ArrayList<>();
        SkillsViewObject tmp;

        Iterator<Skill> iter = skillManager.getSkillsIter();

        // Get skills from skill manager
        while (iter.hasNext()) {
            Skill skill = iter.next();
            tmp = new SkillsViewObject(skill.getName(), skill.getLevel());
            tmpList.add(tmp);
        }

        // Get skill points
        tmp = new SkillsViewObject("Skill Points", skillManager.getAvailableSkillPoints());
        tmpList.add(tmp);

        return tmpList;
    }


}

