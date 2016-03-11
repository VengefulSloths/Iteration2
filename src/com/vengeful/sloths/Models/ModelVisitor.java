package com.vengeful.sloths.Models;

import com.vengeful.sloths.Models.Ability.Abilities.BindWoundsAbility;
import com.vengeful.sloths.Models.Ability.Abilities.ExplosionAbility;
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
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.OneHandedWeapon;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.TwoHandedWeapon;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Knuckle;
import com.vengeful.sloths.Models.InventoryItems.UsableItems.UsableItems;
import com.vengeful.sloths.Models.Map.AreaEffects.HealDamageAE;
import com.vengeful.sloths.Models.Map.AreaEffects.InstantDeathAE;
import com.vengeful.sloths.Models.Map.AreaEffects.LevelUpAE;
import com.vengeful.sloths.Models.Map.AreaEffects.TakeDamageAE;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.InteractiveItem;
import com.vengeful.sloths.Models.Map.TeleportSenderTile;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.MapItems.Obstacle;
import com.vengeful.sloths.Models.Map.MapItems.OneShotItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Map.TeleportDestinationTile;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Terrains.Water;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.Occupation.*;
import com.vengeful.sloths.Models.RangedEffects.HitBox.HitBox;
import com.vengeful.sloths.Models.RangedEffects.HitBox.ImmovableHitBox;
import com.vengeful.sloths.Models.RangedEffects.HitBox.MovableHitBox;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.*;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by zach on 2/22/16.
 */
public interface ModelVisitor {

    //void visit(Object o);
    void visitMap(Map map);
    void visitAvatar(Avatar avatar);
    void visitPiggy(Piggy piggy);
    void visitAggressiveNPC(AggressiveNPC aNPC);
    void visitNonAggressiveNPC(NonAggressiveNPC nonANPC);
    void visitStats(Stats s);

    void visitBuffManager(BuffManager bm);
    void visitBuff(Buff b);
    void visitBuffOverTime(BuffOverTime buffOverTime);
    void visitAbilityManager(AbilityManager am);
    void visitAbility(Ability ability);

    void visitSummoner(Summoner s);
    void visitSneak(Sneak s);
    void visitSmasher(Smasher s);
    void visitDummyOcc(DummyOccupation dummyO);

    void visitInventory(Inventory i);
    void visitPotion(Potion p);
    void vistUsableItem(UsableItems ui);

    void visitTakeDamageAE(TakeDamageAE t);
    void visitHealDamageAE(HealDamageAE h);
    void visitInstantDeathAE(InstantDeathAE i);
    void visitLevelUpAE(LevelUpAE ae);

    void visitEquipped(Equipped e);
    void visitHat(Hat h);
    void visitOneHandedWeapon(OneHandedWeapon ohw);
    void visitTwoHandedWeapon(TwoHandedWeapon thw);
    void visitKnuckle(Knuckle thw);
    void visitStatsAddable(StatsAddable sa);

    void visitMapArea(MapArea mapArea);
    void visitTile(Tile tile);

    @Deprecated
    void visitMapItem(MapItem mapItem);

    void visitTeleportSenderTile(TeleportSenderTile t);

    void visitTeleportDestinationTile(TeleportDestinationTile t);

    void visitTakeableItem(TakeableItem takeableItem);
    void visitObstacle(Obstacle obstacle);
    void visitOneShotItem(OneShotItem osi);
    void visitInteractiveItem(InteractiveItem item);

    void visitGrass(Grass grass);
    void visitMountain(Mountain mountain);
    void visitWater(Water water);

    void visitSkillManager(SkillManager skillManager);

    void visitSkill(Skill skill);


    void visitMovableHitBox(MovableHitBox movableHitBox);
    void visitImmovableHitBox(ImmovableHitBox immovableHitBox);

    void visitBindWounds(BindWoundsAbility bindWoundsAbility);

    void visitMeleeAttackAbility(MeleeAttackAbility meleeAttackAbility);

    void visitFireBallAbility(FireBallAbility fireBallAbility);
    void visitExplosionAbility(ExplosionAbility explosionAbility);
}
