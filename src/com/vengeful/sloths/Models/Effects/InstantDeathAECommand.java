package com.vengeful.sloths.Models.Effects;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;

/**
 * Created by luluding on 2/5/16.
 */
public class InstantDeathAECommand extends EffectCommand{

    public InstantDeathAECommand(Entity entity){
        this.entity = entity;
        //TimeModel.getInstance().registerAlertable(this, 0);
    }

    @Override
    public void execute() {

        //For now, make this AE only for Avatar

        if(this.entity instanceof Avatar){
//            this.entity.getEntityStats().setCurrentHealth(-this.entity.getEntityStats().getCurrentHealth());
//            this.entity.getEntityStats().updateLivesLeft(-this.entity.getEntityStats().getLivesLeft());
            ((Avatar)this.entity).die();
        }

    }

//    public void saveMe(SaveManager sm, int ws){
//        sm.writeClassLine(ws, "InstantDeathAECommand");
//        super.saveMe(sm, ws);
//    }
}
