package com.vengeful.sloths.Controllers.SearchingController;

import com.vengeful.sloths.Controllers.SearchingController.SearchingController;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Buff.BuffOverTime;
import com.vengeful.sloths.Controllers.Target.AvatarTarget;
import com.vengeful.sloths.Controllers.Target.Target;
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
import com.vengeful.sloths.Models.Stats.StatAddables.*;
import com.vengeful.sloths.Models.Stats.Stats;

import java.util.Iterator;

/**
 * Created by zach on 2/22/16.
 */
public class AggressiveNPCSearchingController extends SearchingController {

    public AggressiveNPCSearchingController(MapArea mapArea, Entity entity) {
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
        // set priority yadyadyayd
        //System.out.println("agressive npc sees the avatar :o");
        Target currTarget = new AvatarTarget(0);
        currTarget.setCoord(this.getCurrentCoord());
        this.setHighestPriorityTarget(this.getMaxTarget(currTarget, this.getHighestPriorityTarget()));
    }

    @Override
    public void visitPiggy(Piggy piggy) {
        Target currTarget = new AvatarTarget(1);
        currTarget.setCoord(this.getCurrentCoord());
        this.setHighestPriorityTarget(this.getMaxTarget(currTarget, this.getHighestPriorityTarget()));

    }

    @Override
    public void visitAggressiveNPC(AggressiveNPC aNPC) {
        //do nada

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
        }
    }

    @Override
    public void visitMapItem(MapItem mapItem) {
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
    }

    @Override
    public void visitTakeableItem(TakeableItem takeableItem) {
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

//    @Override
//    public void visitCurrentHealthAddable(CurrentHealthAddable currentHealthAddable) {
//
//    }
//
//    @Override
//    public void visitBonusHealthAddable(BonusHealthAddable bonusHealthAddable) {
//
//    }
//
//    @Override
//    public void visitGenericStatsAddable(GenericStatsAddable genericStatsAddable) {
//
//    }
//
//    @Override
//    public void visitHardinessAddable(HardinessAddable hardinessAddable) {
//
//    }
//
//    @Override
//    public void visitBaseStatsAddable(BaseStatsAddable baseStatsAddable) {
//
//    }
//
//    @Override
//    public void visitHealthManaExperienceAddable(HealthManaExperienceAddable healthManaExperienceAddable) {
//
//    }
//
//    @Override
//    public void visitIntellectAddable(IntellectAddable intellectAddable) {
//
//    }
//
//    @Override
//    public void visitMovementAddable(MovementAddable movementAddable) {
//
//    }
//
//    @Override
//    public void visitStrengthAddable(StrengthAddable strengthAddable) {
//
//    }
//
//    @Override
//    public void visitAgilityAddable(AgilityAddable agilityAddable) {
//
//    }

}
