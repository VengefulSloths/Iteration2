package com.vengeful.sloths.Models.Skills;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Utility.ModelConfig;

import java.util.*;

/**
 * Created by luluding on 3/6/16.
 */
public class SkillManager implements ModelVisitable{

    //<skill name, skill>
    private Map<String, Skill> skills;
    private int availableSkillPoints;

    public SkillManager(){
        skills = new HashMap<>();
        availableSkillPoints = ModelConfig.getInitialSkillpoint();
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

    private int getMaxSkillLevel(String skillName){
        if(!this.skills.containsKey(skillName))
            return 0; //you dont have that skill
        else{
            return this.skills.get(skillName).getMaxSkillLevel();
        }
    }

    private Skill getSkill(String skillName) {
        return this.skills.get(skillName);
    }
    /*************************************/

    public Skill getOneHandSkill() {
        return getSkill("one-handed weapon");
    }

    public Skill getTwoHandSkill() {
        return getSkill("two-handed weapon");
    }

    public Skill getBrawlingSkill() {
        return getSkill("brawling");
    }

    public Skill getRangedWeaponSkill() {
        return getSkill("ranged weapon");
    }

    public Skill getStaffSkill() {
        return getSkill("staff");
    }

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


    //looks pretty bad... maybe reconstruct later.. But I don't want to pass skill name around
    public int getMaxBindWoundsLevel(){
        return this.getMaxSkillLevel("bind wounds");
    }

    public int getMaxBargainLevel(){
        return this.getMaxSkillLevel("bargain");
    }

    public int getMaxObservationLevel(){
        return this.getMaxSkillLevel("observation");
    }

    public int getMaxOneHandedLevel(){
        return this.getMaxSkillLevel("one-handed weapon");
    }

    public int getMaxTwoHandedLevel(){
        return this.getMaxSkillLevel("two-handed weapon");
    }

    public int getMaxBrawling(){
        return this.getMaxSkillLevel("brawling");
    }

    public int getMaxEnchantment(){
        return this.getMaxSkillLevel("enchantment");
    }

    public int getMaxBoonLevel(){
        return this.getMaxSkillLevel("boon");
    }

    public int getMaxBaneLevel(){
        return this.getMaxSkillLevel("bane");
    }

    public int getMaxStaffLevel(){
        return this.getMaxSkillLevel("staff");
    }

    public int getMaxPickPocketLevel(){
        return this.getMaxSkillLevel("pick pocket");
    }

    public int getMaxRemoveTrapLevel(){
        return this.getMaxSkillLevel("remove trap");
    }

    public int getMaxCreepLevel(){
        return this.getMaxSkillLevel("creep");
    }

    public int getMaxRangedWeaponLevel() {
        return this.getMaxSkillLevel("ranged weapon");
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitSkillManager(this);
    }
    /*************************************/

}
