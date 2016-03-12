package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.PermanantBuff;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.Stats.StatAddables.MovementAddable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

import java.util.ArrayList;

/**
 * Created by Alex on 3/12/2016.
 */
public class StealthAbility extends SelfBuffAbility {
    private ArrayList<EntityObserver> observers;


    public StealthAbility(Entity entity) {
        super(entity, new PermanantBuff(new MovementAddable(-10)), 20, 25);
        this.observers = entity.getObservers();
    }

    @Override
    public int execute() {
        System.out.println("about to stealth bby");
        int output;
        if ((output = super.execute()) > 0) {
            TimeModel.getInstance().registerAlertable(() -> {
                for (EntityObserver observer : observers) {
                    observer.alertStealth();
                }
            }, getWindTicks());
        }
        return output;
    }
}
