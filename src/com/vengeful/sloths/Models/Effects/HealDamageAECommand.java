package com.vengeful.sloths.Models.Effects;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by luluding on 2/5/16.
 */
public class HealDamageAECommand extends EffectCommand{
    private int health;

    public HealDamageAECommand(Entity entity, int health, Coord currentLoc){
        this.entity = entity;
        this.health = health;
        this.currentLoc = currentLoc;
        //TimeModel.getInstance().registerAlertable(this, 0);
        //When AE created 1st time, ticks = 0 (immediate effect). After that, tick at a fixed interval
    }

    public HealDamageAECommand(Entity entity, int health, Coord currentLoc, int ticks){
        this.entity = entity;
        this.health = health;
        this.currentLoc = currentLoc;
        TimeModel.getInstance().registerAlertable(this, ticks);
    }


    @Override
    public void execute() {
        //If Entity is still on the tile, take damage and respawn damage command
        if(this.entity.getLocation().getX() == this.currentLoc.getX() && this.entity.getLocation().getY() == this.currentLoc.getY()){

            //damage entity
            //This AE can only be applied to Avatar
            if(this.entity instanceof Avatar)
                ((Avatar)this.entity).gainHealth(this.health);


            /* Need to add functions in Entity to gainHealth is this later can be applied to Entity as well*/
            //this.entity.getEntityStats().setCurrentHealth(this.healLife);


            //System.out.Println("MY LIFE WAS: " + this.entity.getEntityStats().getCurrentHealth());
            //System.out.Println("HEALED!!");
            //System.out.Println("MY LIFE IS NOW: " + this.entity.getEntityStats().getCurrentHealth());

            //create new takeDamage command
            new HealDamageAECommand(this.entity, this.health, this.currentLoc, 30);
        }
    }

//    public void saveMe(SaveManager sm, int ws){
//        sm.writeClassLine(ws, "HealDamageAECommand");
//        sm.writeVariableLine(ws, "health", ""+health, false);
//        super.saveMe(sm, ws);
//    }
}
