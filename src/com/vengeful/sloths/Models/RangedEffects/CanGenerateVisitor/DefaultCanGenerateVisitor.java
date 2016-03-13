package com.vengeful.sloths.Models.RangedEffects.CanGenerateVisitor;

import com.vengeful.sloths.Controllers.InputController.InputStrategies.AdaptableStrategy;
import com.vengeful.sloths.Models.Ability.Abilities.*;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.RemoveTrapAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.StealthAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities.AngleSpellAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities.ExplosionAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities.FireBallAbility;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Buff.BuffOverTime;
import com.vengeful.sloths.Models.Entity.AggressiveNPC;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.NonAggressiveNPC;
import com.vengeful.sloths.Models.Entity.Piggy;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.Potion;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.*;
import com.vengeful.sloths.Models.InventoryItems.UsableItems.PiggyTotem;
import com.vengeful.sloths.Models.InventoryItems.UsableItems.UsableItems;
import com.vengeful.sloths.Models.Map.*;
import com.vengeful.sloths.Models.Map.AreaEffects.HealDamageAE;
import com.vengeful.sloths.Models.Map.AreaEffects.InstantDeathAE;
import com.vengeful.sloths.Models.Map.AreaEffects.LevelUpAE;
import com.vengeful.sloths.Models.Map.AreaEffects.TakeDamageAE;
import com.vengeful.sloths.Models.Map.MapItems.*;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.InteractiveItem;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.BreakBoxQuest;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.DoDestroyObstacleQuest;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.HasItemQuest;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Terrains.Water;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Occupation.DummyOccupation;
import com.vengeful.sloths.Models.Occupation.Smasher;
import com.vengeful.sloths.Models.Occupation.Sneak;
import com.vengeful.sloths.Models.Occupation.Summoner;
import com.vengeful.sloths.Models.RangedEffects.HitBox.ImmovableHitBox;
import com.vengeful.sloths.Models.RangedEffects.HitBox.MovableHitBox;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 3/10/16.
 */
public class DefaultCanGenerateVisitor implements ModelVisitor{
    //Default visitor used for over tile hitbox (e.g. fireball)

    public boolean canGenerate() {
        return canGenerate;
    }

    public void setCanGenerate(boolean canGenerate) {
        this.canGenerate = canGenerate;
    }

    private boolean canGenerate;

    @Override
    public void visitAbilityItem(AbilityItem abilityItem) {

    }

    @Override
    public void visitTile(Tile tile) {

        System.out.println("?????");
        canGenerate = true; //default
        tile.getTerrain().accept(this);
    }

    @Override
    public void visitMountain(Mountain mountain) {
        setCanGenerate(false);
    }

    @Override
    public void visitWater(Water water) {

    }

    @Override
    public void visitGrass(Grass grass) {

    }

    @Override
    public void visitAvatar(Avatar avatar) {

    }

    @Override
    public void visitPiggy(Piggy piggy) {

    }

    @Override
    public void visitMapItem(MapItem mapItem) {

    }

    @Override
    public void visitTeleportSenderTile(TeleportSenderTile t) {

    }

    @Override
    public void visitTeleportDestinationTile(TeleportDestinationTile t) {

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
    public void visitInteractiveItem(InteractiveItem item) {

    }

    @Override
    public void visitMovableHitBox(MovableHitBox movableHitBox) {

    }

    @Override
    public void visitImmovableHitBox(ImmovableHitBox immovableHitBox) {

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
    public void visitExplosionAbility(ExplosionAbility explosionAbility) {

    }

    @Override
    public void visitAngleSpellAbility(AngleSpellAbility angleSpellAbility) {

    }

    @Override
    public void visitRemoveTrapAbility(RemoveTrapAbility removeTrapAbility) {

    }

    @Override
    public void visitStealthAbility(StealthAbility stealthAbility) {

    }

    @Override
    public void visitBreakBoxQuest(BreakBoxQuest breakBoxQuest) {

    }

    @Override
    public void visitDoDestroyObstacleQuest(DoDestroyObstacleQuest doDestroyObstacleQuest) {

    }

    @Override
    public void visitHasItemQuest(HasItemQuest hasItemQuest) {

    }

    @Override
    public void visitSelfBuffAbility(SelfBuffAbility selfBuffAbility) {

    }

    @Override
    public void visitMountAbility(MountAbility mountAbility) {

    }

    @Override
    public void visitRemoveBuffAbility(RemoveBuffAbility removeBuffAbility) {

    }

    @Override
    public void visitNullAbility(NullAbility nullAbility) {

    }

    @Override
    public void visitDemountAbility(DemountAbility demountAbility) {

    }

    @Override
    public void visitMount(Mount mount) {

    }

    @Override

    public void visitTrap(Trap trap) {}


    public void visitAdaptableStrategy(AdaptableStrategy adaptableStrategy) {


    }

    @Override
    public void visitGold(Gold gold) {

    }

    @Override
    public void visitPiggyTotem(PiggyTotem piggyTotem) {

    }


    /********** Not gonna use down below *********/


    @Override
    public void visitMap(Map map) {

    }


    @Override
    public void visitAggressiveNPC(AggressiveNPC aNPC) {

    }

    @Override
    public void visitNonAggressiveNPC(NonAggressiveNPC nonANPC) {

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
    public void visitSkillManager(SkillManager skillManager) {

    }

    @Override
    public void visitSkill(Skill skill) {

    }

}
