package com.vengeful.sloths.Models.Map.AreaEffects;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Effects.EffectCommandFactory;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Models.ViewObservable;
import com.vengeful.sloths.Models.Observers.AreaEffectObserver;
import com.vengeful.sloths.Models.Observers.ModelObserver;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by John on 1/30/2016.
 */
public abstract class AreaEffect implements ModelVisitable{

    private ArrayList<Entity> affectedEntities = new ArrayList<>();


    public AreaEffect() {
        TimeModel.getInstance().registerAlertable(() -> applyEffect(), 20);
    }


    private void applyEffect() {
        ArrayList<Entity> entities = getEntities();
        for (int i = entities.size() - 1; i>=0; i--) {
            doEffect(entities.get(i));
        }

        TimeModel.getInstance().registerAlertable(() -> applyEffect(), 20);
    }

    public abstract void doEffect(Entity entity);

    public void addEntity(Entity entity) {
        this.affectedEntities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.affectedEntities.remove(entity);
    }

    public ArrayList<Entity> getEntities() {
        return affectedEntities;
    }
}
