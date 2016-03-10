package com.vengeful.sloths.Models.Map.AreaEffects;

import com.vengeful.sloths.Models.Effects.*;
import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

import java.util.ArrayList;

/**
 * Created by qianwen on 2/3/16.
 */
public class TakeDamageAE extends AreaEffect{

    private int damage;

    public TakeDamageAE(int damage) {
        this.damage = damage;
    }



    @Override
    public void doEffect(Entity entity) {
        entity.takeDamage(damage);
    }
}
