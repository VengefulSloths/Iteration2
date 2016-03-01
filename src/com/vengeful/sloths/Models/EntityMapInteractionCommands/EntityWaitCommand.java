package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

/**
 * Created by John on 3/1/2016.
 */
public class EntityWaitCommand implements Alertable {

    private Entity entity;

    public EntityWaitCommand(Entity entity){
        this.entity = entity;
    }

    @Override
    public int execute() {
        entity.setActive(true);
        TimeModel.getInstance().registerAlertable(this, 4);
        return 1;
    }

    @Override
    public void mAlert() {
        //System.out.println("able to move again");
        entity.setActive(false);
    }


}
