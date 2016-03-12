package com.vengeful.sloths.Models.EntityMapInteractionCommands;


import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.Gold;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Models.Observers.EntityObserver;

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

    public EntityDropEntireInventoryCommand createDropEntireInventoryCommand(Entity entity) {
        EntityDropEntireInventoryCommand entityDropEntireInventoryCommand = new EntityDropEntireInventoryCommand(entity);
        return entityDropEntireInventoryCommand;
    }

    public EntityDieCommand createDeathCommand(Entity entity, Iterator<EntityObserver> entityObserverIterator){
        EntityDieCommand edc = new EntityDieCommand(entity, entityObserverIterator);
        return edc;
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
                                                       Iterator<EntityObserver> entityObserverIterator) {

        EntityAttackCommand eac = new EntityAttackCommand(src, dir, attackSpeed, attackDamage, map, entity, entityObserverIterator);


        return eac;
    }

    public EntityWaitCommand createEntityWaitCommand(Entity entity){
        EntityWaitCommand ewc = new EntityWaitCommand(entity);
        return ewc;
    }
    //TODO: Refactor the bellow commands
    public EntityDropCommand createDropCommand(InventoryItem itemToDrop, Coord dropLoc, Entity entity) { //Called in Avatar
        EntityDropCommand edc = new EntityDropCommand(itemToDrop, dropLoc, entity, this.map);
        return edc;
    }

    public PiggyDropTotemCommand createPiggyDropTotemCommand(TakeableItem itemToDrop, Coord dropLoc, Entity entity) { //Called in Piggy
        PiggyDropTotemCommand pdtc = new PiggyDropTotemCommand(itemToDrop, dropLoc, entity, this.map);
        return pdtc;
    }

    public EntityPickupCommand createPickUpCommand(Entity entity, Inventory inv, TakeableItem itemToPickup) {
        EntityPickupCommand epc = new EntityPickupCommand(entity, inv, itemToPickup);
        return epc;
    }

    public EntityPickUpGoldCommand createPickUpGoldCommand(Entity entity, Inventory inv, Gold gold){
        EntityPickUpGoldCommand epgc = new EntityPickUpGoldCommand(entity,inv,gold);
        return epgc;
    }

    public EntityRespawnCommand createRespawnCommand(Entity entity, Coord respawnTile, int timeToRespawn) {
        EntityRespawnCommand erc = new EntityRespawnCommand(entity, respawnTile, timeToRespawn);
        return erc;
    }

}
