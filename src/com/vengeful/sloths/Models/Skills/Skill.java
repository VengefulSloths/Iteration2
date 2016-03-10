package com.vengeful.sloths.Models.Skills;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

import javax.lang.model.element.NestingKind;

/**
 * Created by luluding on 3/6/16.
 */
public class Skill implements ModelVisitable{
    private String name;
    private int level;
    private int maxSkillLevel;

    public Skill(String name){
        this.name = name;
        this.level = 0;
        this.maxSkillLevel = 10; //default
    }
    public Skill(){

    }

    public Skill(String name, int level, int maxSkillLevel){
        this.name = name;
        this.level = level;
        this.maxSkillLevel = maxSkillLevel; //default;
    }

    public int getLevel(){
        return this.level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public boolean addLevel(int level, int availableSkillPoints){
        if(level+this.level > maxSkillLevel)
            return false;

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

    public int getMaxSkillLevel() {
        return maxSkillLevel;
    }

    public void setMaxSkillLevel(int maxSkillLevel) {
        this.maxSkillLevel = maxSkillLevel;
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitSkill(this);
    }
}
