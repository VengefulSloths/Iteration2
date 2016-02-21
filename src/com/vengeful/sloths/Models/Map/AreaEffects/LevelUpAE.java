package com.vengeful.sloths.Models.Map.AreaEffects;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Effects.EffectCommandFactory;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;

/**
 * Created by qianwen on 2/3/16.
 */
public class LevelUpAE extends AreaEffect{

    public LevelUpAE(EffectCommandFactory commandFactory){
        super(commandFactory);
    }

    //This AE can only take effect on Avatar
    @Override
    public EffectCommand createEffectCommand(Entity affectedEntity) {
        if(affectedEntity instanceof Avatar) {
            //this.destory = true; //show be destroyed as soon as activated once
            //return new LevelUpAECommand(affectedEntity, this);
            //EffectCommand levelUpCMD = this.commandFactory.createLevelUpAECommand(affectedEntity, this);

            //levelUpCMD.execute();
            //return levelUpCMD;
            return this.commandFactory.createLevelUpAECommand(affectedEntity, this);
        }
        else
            return null;
    }

//    public void saveMe(SaveManager sm, int ws) {
//        sm.writeClassLine(ws, "LevelUpAE");
//    }

}
