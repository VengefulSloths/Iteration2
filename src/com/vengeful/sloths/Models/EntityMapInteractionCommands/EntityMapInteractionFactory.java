package com.vengeful.sloths.Models.EntityMapInteractionCommands;


import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.View.Observers.EntityObserver;

import java.util.Iterator;

/**
 * Created by alexs on 2/28/2016.
 */
public class EntityMapInteractionFactory {
    private static EntityMapInteractionFactory ourInstance = new EntityMapInteractionFactory();

    public static EntityMapInteractionFactory getInstance() {
        return ourInstance;
    }

    private EntityMapInteractionFactory() {
    }

    private Map map;
    public void init(Map map) {
        this.map = map;
    }


    public EntityMovementCommand createMovementCommand(Coord src,
                                                       Direction dir,
                                                       int movementSpeed,
                                                       Entity entity,
                                                       CanMoveVisitor canMoveVisitor,
                                                       Iterator<EntityObserver> entityObserverIterator) {

        EntityMovementCommand emc = new EntityMovementCommand(src, dir, movementSpeed, map, entity, canMoveVisitor, entityObserverIterator);


        return emc;
    }

    public EntityAttackCommand createAttackCommand(Coord src,
                                                       Direction dir,
                                                       int attackSpeed,
                                                       int attackDamage,
                                                       Entity entity,
                                                       CanMoveVisitor canMoveVisitor,
                                                       Iterator<EntityObserver> entityObserverIterator) {

        EntityAttackCommand eac = new EntityAttackCommand(src, dir, attackSpeed, attackDamage, map, entity, canMoveVisitor, entityObserverIterator);


        return eac;
    }
    //TODO: Refactor the bellow commands
//    public DropCommand createDropCommand(InventoryItem itemToDrop, Coord dropLoc, Entity entity) { //Called in Avatar
//        return null;
//    }
//
//    public PickUpCommand createPickUpCommand(Coord dropLoc, Entity entity, TakeableItem item) {
//       return null;
//    }
//
//    public DieCommand createDieCommand(Coord location, Entity entity){
//        return null;
//    }
}
