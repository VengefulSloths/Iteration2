package com.vengeful.sloths.Models.Skills;

import javax.lang.model.element.NestingKind;

/**
 * Created by luluding on 3/6/16.
 */
public class Skill {
    private String name;
    private int level;

    public Skill(String name){
        this.name = name;
        this.level = 0;
    }

    public Skill(String name, int level){
        this.name = name;
        this.level = level;
    }

    public int getLevel(){
        return this.level;
    }

    public boolean addLevel(int level, int availableSkillPoints){
        if(availableSkillPoints >= level){
            this.level += level;
            return true;
        }

        return false;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
