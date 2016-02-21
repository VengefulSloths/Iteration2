package com.vengeful.sloths.Models.Effects;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.AreaEffects.AreaEffect;

/**
 * Created by qianwen on 2/5/16.
 */
public class LevelUpAECommand extends EffectCommand {

    AreaEffect ae;

    public LevelUpAECommand(Entity entity, AreaEffect ae){
        this.entity = entity;
        this.ae = ae;
        //TimeModel.getInstance().registerAlertable(this, 0);
    }


    @Override
    public void execute() {

        //System.out.Println("MY LEVEL BEFORE: " + entity.getEntityStats().getLevel());

        if(entity instanceof Avatar){
            ((Avatar)entity).levelUp();
            //System.out.Println("LEVELING UP!!");
        }

        //System.out.Println("MY LEVEL AFTER: " + entity.getEntityStats().getLevel());
    }

//    public void saveMe(SaveManager sm, int ws){
//        sm.writeClassLine(ws, "LevelUpAECommand");
//        super.saveMe(sm, ws);
//    }
}
