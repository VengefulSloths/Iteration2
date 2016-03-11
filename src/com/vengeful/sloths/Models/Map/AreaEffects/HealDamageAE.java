package com.vengeful.sloths.Models.Map.AreaEffects;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Effects.EffectCommandFactory;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by luluding on 2/5/16.
 */
public class HealDamageAE extends AreaEffect{
    int health;

    public HealDamageAE(int health) {
        this.health = health;
    }

    @Override
    public void doEffect(Entity entity) {
        entity.gainHealth(health);
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitHealDamageAE(this);
    }
}
