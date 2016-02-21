package com.vengeful.sloths.Models.Map.AreaEffects;

import com.vengeful.sloths.Models.Effects.*;
import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;

/**
 * Created by qianwen on 2/3/16.
 */
public class TakeDamageAE extends AreaEffect{
    private int damage;


    public TakeDamageAE(EffectCommandFactory commandFactory){
        super(commandFactory);
        this.damage = 1;
    }

    public TakeDamageAE(int damage, EffectCommandFactory commandFactory){
        super(commandFactory);
        this.damage = damage;
    }

    //This AE can only take effect on Avatar
    @Override
    public EffectCommand createEffectCommand(Entity affectedEntity) {
        if(affectedEntity instanceof Avatar)
            //return new TakeDamageAECommand(affectedEntity, this.damage, affectedEntity.getLocation());
            return this.commandFactory.createTakeDamageAECommand(affectedEntity, this.damage, affectedEntity.getLocation());
        else
            return null;
    }

//    public void saveMe(SaveManager sm, int ws) {
//        sm.writeClassLine(ws, "TakeDamageAE");
//        String d = "" + damage;
//        sm.writeVariableLine(ws, "damage", d, false);
//    }
}
