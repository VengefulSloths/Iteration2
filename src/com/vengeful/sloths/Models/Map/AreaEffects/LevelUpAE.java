package com.vengeful.sloths.Models.Map.AreaEffects;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Effects.EffectCommandFactory;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import sun.security.x509.AVA;

/**
 * Created by qianwen on 2/3/16.
 */
public class LevelUpAE extends AreaEffect{
    private int experience;

    public LevelUpAE(int experience) {
        this.experience = experience;
    }

    @Override
    public void doEffect(Entity entity) {
        if (entity == Avatar.getInstance()) {
            ((Avatar) entity).gainXP(experience);
        }
    }

}
