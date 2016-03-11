package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.Ability.Abilities.BindWoundsAbility;
import com.vengeful.sloths.Models.Ability.Abilities.FireBallAbility;
import com.vengeful.sloths.Models.Ability.Abilities.MeleeAttackAbility;
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
import com.vengeful.sloths.Models.Map.AreaEffects.HealDamageAE;
import com.vengeful.sloths.Models.Map.AreaEffects.InstantDeathAE;
import com.vengeful.sloths.Models.Map.AreaEffects.LevelUpAE;
import com.vengeful.sloths.Models.Map.AreaEffects.TakeDamageAE;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.InteractiveItem;
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
import com.vengeful.sloths.Models.RangedEffects.HitBox.HitBox;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

import java.util.Iterator;

/**
 * Created by alexs on 2/28/2016.
 */
public class DefaultCanMoveVisitor extends CanMoveVisitor {

    @Override
    public void visitAvatar(Avatar avatar) {
        //System.out.println("cant move because Avatar");
        setCanMove(false);
    }
    @Override
    public void visitAggressiveNPC(AggressiveNPC aNPC) {
        //System.out.println("cant move because aNPC");
        setCanMove(false);
    }

    @Override
    public void visitNonAggressiveNPC(NonAggressiveNPC nonANPC) {
        //System.out.println("cant move because nonaNPC");
        setCanMove(false);
    }
    @Override
    public void visitPiggy(Piggy piggy) {
        setCanMove(false);
    }
    @Override
    public void visitTile(Tile tile) {
        //First assume we can move their ( AND logic )
        setCanMove(true);


        //Visit all the entities
        Iterator<Entity> entityIterator = tile.getEntityIterator();
        while (entityIterator.hasNext()) {
            //System.out.println("    visiting entity");
            entityIterator.next().accept(this);
        }

        Iterator<MapItem> mapItemIterator = tile.getMapItemIterator();
        while (mapItemIterator.hasNext()) {
            mapItemIterator.next().accept(this);
        }

        tile.getTerrain().accept(this);

    }
    @Override
    public void visitObstacle(Obstacle obstacle) {
        //System.out.println("cant move because Obstacle");

        setCanMove(false);

    }

    @Override
    public void visitGrass(Grass grass) {

    }

    @Override
    public void visitMountain(Mountain mountain) {
        //System.out.println("cant move because Mountain");
        setCanMove(false);

    }

    @Override
    public void visitWater(Water water) {
        //System.out.println("cant move because Water");

        setCanMove(false);

    }

    @Override
    public void visitSkillManager(SkillManager skillManager) {

    }

    @Override
    public void visitSkill(Skill skill) {

    }

    @Override
    public void visitHitBox(HitBox hitBox) {

    }

    @Override
    public void visitBindWounds(BindWoundsAbility bindWoundsAbility) {

    }

    @Override
    public void visitMeleeAttackAbility(MeleeAttackAbility meleeAttackAbility) {

    }

    @Override
    public void visitFireBallAbility(FireBallAbility fireBallAbility) {

    }

    @Override
    public void visitMap(Map map) {

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
    public void visitTakeDamageAE(TakeDamageAE t) {

    }

    @Override
    public void visitHealDamageAE(HealDamageAE h) {

    }

    @Override
    public void visitInstantDeathAE(InstantDeathAE i) {

    }

    @Override
    public void visitLevelUpAE(LevelUpAE ae) {

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



    @Override
    public void visitMapItem(MapItem mapItem) {

    }

    @Override
    public void visitTeleportSenderTile(TeleportSenderTile t) {
        visitTile(t);
    }

    @Override
    public void visitTeleportDestinationTile(TeleportDestinationTile t) {
        visitTile(t);
    }

    @Override
    public void visitTakeableItem(TakeableItem takeableItem) {

    }



    @Override
    public void visitOneShotItem(OneShotItem osi) {

    }

    @Override
    public void visitInteractiveItem(InteractiveItem item) {

    }


}
