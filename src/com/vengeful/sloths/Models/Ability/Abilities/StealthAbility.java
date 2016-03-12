package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.Hooks.RemoveBuffHook;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.PermanantBuff;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.MovementAddable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

import java.util.ArrayList;

/**
 * Created by Alex on 3/12/2016.
 */
public class StealthAbility extends SelfBuffAbility {
    private Entity entity;
    private Buff buff;

    public StealthAbility(Entity entity, Buff buff, int windTicks, int coolTicks) {
        super(entity, buff, windTicks, coolTicks);
        this.entity = entity;
        this.buff = buff;
    }

    @Override
    public int execute() {

        //This will set the creep to the correct level
        buff.getBuff().setConcealment(entity.getSkillManager().getCreepLevel());


        int output;
        if ((output = super.execute()) > 0) {
            TimeModel.getInstance().registerAlertable(() -> {
                entity.getAbilityManager().addAbilityHook(new RemoveBuffHook(buff, entity.getBuffManager()));
            }, getWindTicks());
        }
        return output;
    }

    @Override
    public void accept(ModelVisitor visitor){
        visitor.visitStealthAbility(this);
    }
}
