package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;

import java.util.ArrayList;

/**
 * Created by Alex on 3/10/2016.
 */
public class DemountAbility extends RemoveBuffAbility {
    private ArrayList<EntityObserver> observers;

    public DemountAbility(Buff buff, BuffManager buffManager, Entity entity, ArrayList<EntityObserver> observers) {
        super("Demount", buff, buffManager, entity);
        this.observers = observers;
    }

    @Override
    public int execute() {
        for (EntityObserver observer: observers) {
            observer.alertDemount();
        }
        return super.execute();
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitDemountAbility(this);
    }

    @Override
    public String toString() {
        return "Demount Ability";
    }
}
