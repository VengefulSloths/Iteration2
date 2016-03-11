package com.vengeful.sloths.Models.Ability;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class Ability implements ModelVisitable {

    private int windTicks;
    private int coolTicks;

    public Ability(int windTicks, int coolTicks) {
        this.windTicks = windTicks;
        this.coolTicks = coolTicks;
    }
    public Ability(){}
    public abstract int execute();

    protected boolean shouldDoAbility(int skillLevel, int maxSkillLevel){

        int accuracy = (int)Math.round(((double)skillLevel / maxSkillLevel) * 100);
        int randomNum = 1 + (int)(Math.random() * 100); //[1-100]
        if(randomNum <= accuracy){
            System.out.println("ATTEMPT TO DO ABILITY SUCCESSFUL!" + " skillLevel: " + skillLevel + " accu: " + accuracy);
            return true;
        }

        System.out.println("ATTEMPT TO DO ABILITY FAILED! " + " skillLevel: " + skillLevel + " accu: " + accuracy);
        return false;
    }

    public int getWindTicks() {
        return  windTicks;
    }

    public int getCoolTicks() {
        return coolTicks;
    }


    //TODO: put this at a lower level for saving
    @Override
//    public void accept(ModelVisitor modelVisitor) {
//        modelVisitor.visitAbility(this);
//    }
    //this is here for aid in loading, if you add more abilities have the toString just return the name of the class as a string

    public String toString() {
        return "hacky hacky hacky";
    }

    public void setCoolTicks(int coolTicks) {
        this.coolTicks = coolTicks;
    }

    public void setWindTicks(int windTicks) {
        this.windTicks = windTicks;
    }

}
