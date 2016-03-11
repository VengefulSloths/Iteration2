package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by Alex on 3/10/2016.
 */
public class RemoveBuffAbility extends Ability {
    private BuffManager buffManager;
    private Buff buff;
    private Entity entity;
    public RemoveBuffAbility(Buff buff, BuffManager buffManager, Entity entity) {
        super(1, 1);
        this.buff = buff;
        this.buffManager = buffManager;
        this.entity = entity;
    }

    @Override
    public int execute() {
        //if (entity.isActive()) return 0;
        buffManager.removeBuff(buff);
        return getCoolTicks();
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        (new Exception()).printStackTrace();
    }
}
