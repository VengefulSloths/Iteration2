package com.vengeful.sloths.Controllers.SearchingController;

import com.vengeful.sloths.Controllers.Target.AvatarTarget;
import com.vengeful.sloths.Controllers.Target.MapItemTarget;
import com.vengeful.sloths.Controllers.Target.AggressiveNPCTarget;
import com.vengeful.sloths.Controllers.Target.Target;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Buff.BuffOverTime;
import com.vengeful.sloths.Models.Entity.*;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.Potion;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Hat;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Knuckle;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.OneHandedWeapon;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.TwoHandedWeapon;
import com.vengeful.sloths.Models.InventoryItems.UsableItems.UsableItems;
import com.vengeful.sloths.Models.Map.*;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.MapItems.Obstacle;
import com.vengeful.sloths.Models.Map.MapItems.OneShotItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Terrains.Water;
import com.vengeful.sloths.Models.Occupation.DummyOccupation;
import com.vengeful.sloths.Models.Occupation.Smasher;
import com.vengeful.sloths.Models.Occupation.Sneak;
import com.vengeful.sloths.Models.Occupation.Summoner;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

import java.util.Iterator;

/**
 * Created by zach on 3/4/16.
 */
public class PiggySearchingController extends SearchingController {
    public PiggySearchingController(MapArea mapArea, Entity entity) {
        super(mapArea, entity);
    }

    @Override
    protected void doneSearching() {

    }

    @Override
    public void visitMap(Map map) {

    }

    @Override
    public void visitAvatar(Avatar avatar) {
        // Avatar is third-most important to Piggy
        Target currTarget = new AvatarTarget(2);
        currTarget.setCoord(this.getCurrentCoord());
        this.setHighestPriorityTarget(this.getMaxTarget(currTarget, this.getHighestPriorityTarget()));
    }

    @Override
    public void visitPiggy(Piggy piggy) {
        // Do nada!

    }

    @Override
    public void visitAggressiveNPC(AggressiveNPC aNPC) {
        // Attack?
        Target currTarget = new AggressiveNPCTarget(1);
        currTarget.setCoord(this.getCurrentCoord());
        this.setHighestPriorityTarget(this.getMaxTarget(currTarget, this.getHighestPriorityTarget()));

    }

    @Override
    public void visitNonAggressiveNPC(NonAggressiveNPC nonANPC) {
        //do nada
    }

    @Override
    public void visitStats(Stats s) {

    }

    @Override
    public void visitBuffManager(BuffManager bm) {

    }

    @Override
    public void visitBuff(Buff b) {

    }

    @Override
    public void visitBuffOverTime(BuffOverTime buffOverTime) {

    }

    @Override
    public void visitAbilityManager(AbilityManager am) {

    }

    @Override
    public void visitAbility(Ability ability) {

    }

    @Override
    public void visitSummoner(Summoner s) {

    }

    @Override
    public void visitSneak(Sneak s) {

    }

    @Override
    public void visitSmasher(Smasher s) {

    }

    @Override
    public void visitDummyOcc(DummyOccupation dummyO) {

    }

    @Override
    public void visitInventory(Inventory i) {

    }

    @Override
    public void visitPotion(Potion p) {

    }

    @Override
    public void vistUsableItem(UsableItems ui) {

    }

    @Override
    public void visitEquipped(Equipped e) {

    }

    @Override
    public void visitHat(Hat h) {

    }

    @Override
    public void visitOneHandedWeapon(OneHandedWeapon ohw) {

    }

    @Override
    public void visitTwoHandedWeapon(TwoHandedWeapon thw) {

    }

    @Override
    public void visitKnuckle(Knuckle thw) {

    }

    @Override
    public void visitStatsAddable(StatsAddable sa) {

    }

    @Override
    public void visitMapArea(MapArea mapArea) {

    }

    public void visitTile(Tile tile) {

        //System.out.println("okay checkinga  tile");
        if(tile != null) {
            Iterator<Entity> iter = tile.getEntityIterator();
            Entity currEntity;
            while (iter.hasNext()) {
                currEntity = iter.next();

                //System.out.println(currEntity);
                currEntity.accept(this);
            }
            Iterator<MapItem> iterator = tile.getMapItemIterator();
            MapItem item;
            while (iterator.hasNext()) {
                item = iterator.next();

                //System.out.println(currEntity);
                item.accept(this);
            }
        }
        //System.out.println("SEARCHING TILE");
    }

    @Override
    public void visitMapItem(MapItem mapItem) {
        // MapItems are priority 0 for piggy!

    }

    @Override
    public void visitTeleportSenderTile(TeleportSenderTile tile) {
        if(tile != null) {
            Iterator<Entity> iter = tile.getEntityIterator();
            Entity currEntity;
            while (iter.hasNext()) {
                currEntity = iter.next();

                //System.out.println(currEntity);
                currEntity.accept(this);
            }
        }
        //System.out.println("SEARCHING TILE");
    }

    @Override
    public void visitTeleportDestinationTile(TeleportDestinationTile tile) {
        if(tile != null) {
            Iterator<Entity> iter = tile.getEntityIterator();
            Entity currEntity;
            while (iter.hasNext()) {
                currEntity = iter.next();

                //System.out.println(currEntity);
                currEntity.accept(this);
            }
        }
        //System.out.println("SEARCHING TILE");
    }

    @Override
    public void visitTakeableItem(TakeableItem takeableItem) {

        Target currTarget = new MapItemTarget(0);
        currTarget.setCoord(this.getCurrentCoord());
        this.setHighestPriorityTarget(this.getMaxTarget(currTarget, this.getHighestPriorityTarget()));
    }

    @Override
    public void visitObstacle(Obstacle obstacle) {

    }

    @Override
    public void visitOneShotItem(OneShotItem osi) {

    }

    @Override
    public void visitGrass(Grass grass) {

    }

    @Override
    public void visitMountain(Mountain mountain) {

    }

    @Override
    public void visitWater(Water water) {

    }
}
