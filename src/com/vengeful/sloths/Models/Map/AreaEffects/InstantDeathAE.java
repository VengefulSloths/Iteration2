package com.vengeful.sloths.Models.Map.AreaEffects;


import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.StatAddables.CurrentHealthAddable;

/**
 * Created by luluding on 2/5/16.
 */
public class InstantDeathAE extends AreaEffect {
    @Override
    public void doEffect(Entity entity) {
        entity.getStats().subtract(new CurrentHealthAddable(entity.getStats().getMaxHealth()));
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitInstantDeathAE(this);
    }
}
