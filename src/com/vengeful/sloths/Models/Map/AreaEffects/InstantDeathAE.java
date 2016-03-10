package com.vengeful.sloths.Models.Map.AreaEffects;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Effects.EffectCommandFactory;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;

/**
 * Created by luluding on 2/5/16.
 */
public class InstantDeathAE extends AreaEffect {
    @Override
    public void doEffect(Entity entity) {
        entity.die();
    }
}
