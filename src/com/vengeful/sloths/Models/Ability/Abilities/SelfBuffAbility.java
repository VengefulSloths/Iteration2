package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

import java.util.Iterator;

/**
 * Created by Alex on 3/10/2016.
 */
public class SelfBuffAbility extends Ability {
    private Entity target;
    private Buff buff;
    private String description;
    public SelfBuffAbility(String name, String description, Entity entity, Buff buff, int windTicks, int coolTicks) {
        super(name, windTicks, coolTicks);
        this.buff = buff;
        this.target = entity;

        this.name = name;
        this.description = description;

    }


    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public int execute() {
        if (target.isActive()) return 0;

        target.setActive(true);
        Iterator<EntityObserver> entityObserverIterator = target.getObservers().iterator();
        while (entityObserverIterator.hasNext()) {
            entityObserverIterator.next().alertCast(getWindTicks() * TimeController.MODEL_TICK, getCoolTicks()*TimeController.MODEL_TICK);
        }

        TimeModel.getInstance().registerAlertable( () -> {
            target.getBuffManager().addBuff(buff);
        }, getWindTicks());

        TimeModel.getInstance().registerAlertable( () -> {
            target.setActive(false);
        }, getCoolTicks());

        return getCoolTicks();
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitSelfBuffAbility(this);
    }

    public Buff getBuff() {
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public String toString() {
        return "Self Buff";
    }
}
