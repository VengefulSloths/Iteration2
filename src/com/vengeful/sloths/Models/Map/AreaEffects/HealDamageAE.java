package com.vengeful.sloths.Models.Map.AreaEffects;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Effects.EffectCommandFactory;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;

/**
 * Created by luluding on 2/5/16.
 */
public class HealDamageAE extends AreaEffect{
    int health;

    public HealDamageAE(EffectCommandFactory commandFactory){
        super(commandFactory);
        this.health = 1;
    }

    public HealDamageAE(int heal, EffectCommandFactory commandFactory){
        super(commandFactory);
        this.health = heal;
    }

    //This AE can only take effect on Avatar
    @Override
    public EffectCommand createEffectCommand(Entity affectedEntity) {
        if(affectedEntity instanceof Avatar)
            //return new HealDamageAECommand(affectedEntity, this.health, affectedEntity.getLocation());
            return this.commandFactory.createHealDamageAECommand(affectedEntity, this.health, affectedEntity.getLocation());
        else
            return null;
    }

//    @Override
//    public void saveMe(SaveManager sm, int ws) {
//        sm.writeClassLine(ws, "HealingDamageAE");
//        String h = "" + health;
//        sm.writeVariableLine(ws, "health", h, false);
//    }
}
