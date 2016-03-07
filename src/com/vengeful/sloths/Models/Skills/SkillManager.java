package com.vengeful.sloths.Models.Skills;

import java.util.*;

/**
 * Created by luluding on 3/6/16.
 */
public class SkillManager {

    //<skill name, skill>
    private Map<String, Skill> skills;
    private int availableSkillPoints;

    public SkillManager(){
        skills = new HashMap<>();
        availableSkillPoints = 3; //give 3 at beginning of game
    }

    public SkillManager(int availableSkillPoints){
        skills = new HashMap<>();
        this.availableSkillPoints = availableSkillPoints;
    }

    public void addSkill(Skill skill){
        this.skills.put(skill.getName(), skill);
    }

    public boolean updateSkillLevel(Skill skill, int level){
        if(this.skills.containsValue(skill)){
            if(skill.addLevel(level, this.availableSkillPoints)){
                //TODO: alert view of updated skill level

                this.setAvailableSkillPoint(this.availableSkillPoints - level);
                System.out.println("Skill Level for " + skill.getName() + " increased");
                return true;
            }
        }

        return false;
    }

    public int getAvailableSkillPoints(){
        return this.availableSkillPoints;
    }

    public void setAvailableSkillPoint(int availableSkillPoints){
        this.availableSkillPoints = availableSkillPoints;
        //TODO: alert view
    }


    /********** non public ************/
    private int getSkillLevel(String skillName){
        if(!this.skills.containsKey(skillName))
            return 0; //you dont have that skill
        else{
            return this.skills.get(skillName).getLevel();
        }
    }
    /*************************************/



    public Iterator<Skill> getSkillsIter(){
        return this.skills.values().iterator();
    }

    /* Getters for getting skill levels */
    public int getBindWoundsLevel(){
        return this.getSkillLevel("bind wounds");
    }

    public int getBargainLevel(){
        return this.getSkillLevel("bargain");
    }

    public int getObservationLevel(){
        return this.getSkillLevel("observation");
    }

    public int getOneHandedLevel(){
        return this.getSkillLevel("one-handed weapon");
    }

    public int getTwoHandedLevel(){
        return this.getSkillLevel("two-handed weapon");
    }

    public int getBrawling(){
        return this.getSkillLevel("brawling");
    }

    public int getEnchantment(){
        return this.getSkillLevel("enchantment");
    }

    public int getBoonLevel(){
        return this.getSkillLevel("boon");
    }

    public int getBaneLevel(){
        return this.getSkillLevel("bane");
    }

    public int getStaffLevel(){
        return this.getSkillLevel("staff");
    }

    public int getPickPocketLevel(){
        return this.getSkillLevel("pick pocket");
    }

    public int getRemoveTrapLevel(){
        return this.getSkillLevel("remove trap");
    }

    public int getCreepLevel(){
        return this.getSkillLevel("creep");
    }

    public int getRangedWeaponLevel(){
        return this.getSkillLevel("ranged weapon");
    }
    /*************************************/

}
