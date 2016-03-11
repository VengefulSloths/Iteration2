package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

import java.util.Iterator;

/**
 * Created by John on 3/8/2016.
 */
public class EntityDieCommand implements Alertable {

    protected Entity entity;
    private Iterator<EntityObserver> entityObserverIterator;

    public EntityDieCommand(Entity entity, Iterator<EntityObserver> entityObserverIterator){
        this.entity = entity;
        this.entityObserverIterator = entityObserverIterator;
    }

    @Override
    public int execute() {
        entity.setDead(true);
        entity.setActive(true); //make it so that the entity cant do anything

        Map.getInstance().getTile(entity.getLocation()).removeEntity(entity); //unregister him from the tile

        while (entityObserverIterator.hasNext()) {
            entityObserverIterator.next().alertDeath();
        }

        return 0;
    }

    @Override
    public void mAlert() {
        //create a respawn command with ticks equal to the ammount passed in

    }
}
