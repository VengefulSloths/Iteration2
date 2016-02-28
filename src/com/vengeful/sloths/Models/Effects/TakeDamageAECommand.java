package com.vengeful.sloths.Models.Effects;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by luluding on 2/4/16.
 */
public class TakeDamageAECommand extends EffectCommand{

    private int damage;

    public TakeDamageAECommand(Entity entity, int damage, Coord currentLoc){
        this.entity = entity;
        this.damage = damage;
        this.currentLoc = currentLoc;
        //TimeModel.getInstance().registerAlertable(this, 0);
        //When AE created 1st time, ticks = 0 (immediate effect). After that, tick at a fixed interval
    }

    public TakeDamageAECommand(Entity entity, int damage, Coord currentLoc, int ticks){
        this.entity = entity;
        this.damage = damage;
        this.currentLoc = currentLoc;

        //TODO: get this working again
        //TimeModel.getInstance().registerAlertable(this, ticks);
    }


    @Override
    public void execute() {

        //If Entity is still on the tile, take damage and respawn damage command
        if(this.entity.getLocation().getR() == this.currentLoc.getS() && this.entity.getLocation().getS() == this.currentLoc.getS()){
            //damage avatar
            //This AE can only be applied to Avatar
            if(this.entity instanceof Avatar)
                ((Avatar)this.entity).takeDamage(this.damage);

            /* Need to add functions in Entity to takeDamage is this later can be applied to Entity as well*/
            //this.entity.getEntityStats().setCurrentHealth(-this.damage);
            //System.out.Println("MY LIFE WAS: " + this.entity.getEntityStats().getCurrentHealth());
            //System.out.Println("DAMAGE TAKEN!!");
            //System.out.Println("MY LIFE IS NOW: " + this.entity.getEntityStats().getCurrentHealth());

            //create new takeDamage command
            new TakeDamageAECommand(this.entity, this.damage, this.currentLoc, 30);
        }
    }

//    public void saveMe(SaveManager sm, int ws){
//        sm.writeClassLine(ws, "TakeDamageAECommand");
//        sm.writeVariableLine(ws, "damage", ""+damage, false);
//        super.saveMe(sm, ws);
//    }

}
