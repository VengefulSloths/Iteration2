package com.vengeful.sloths.Controllers.SearchingController;


import com.vengeful.sloths.Controllers.InputController.InputStrategies.AdaptableStrategy;
import com.vengeful.sloths.Models.Ability.Abilities.*;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.PickPocketAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.RemoveTrapAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.StealthAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities.*;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.*;
import com.vengeful.sloths.Controllers.Target.AvatarTarget;
import com.vengeful.sloths.Controllers.Target.Target;
import com.vengeful.sloths.Models.DialogueTrade.TerminalDialogContainer;
import com.vengeful.sloths.Models.DialogueTrade.TradeDialogContainer;
import com.vengeful.sloths.Models.Entity.*;
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
import com.vengeful.sloths.Models.Occupation.DummyOccupation;
import com.vengeful.sloths.Models.Occupation.Smasher;
import com.vengeful.sloths.Models.Occupation.Sneak;
import com.vengeful.sloths.Models.Occupation.Summoner;
import com.vengeful.sloths.Models.RangedEffects.HitBox.ImmovableHitBox;
import com.vengeful.sloths.Models.RangedEffects.HitBox.MovableHitBox;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.*;
import com.vengeful.sloths.Models.Stats.Stats;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by zach on 2/22/16.
 */
public class AggressiveNPCSearchingController extends SearchingController {


    public AggressiveNPCSearchingController(MapArea mapArea, Entity entity) {
        super(mapArea, entity);
    }
    private Random roller = new Random(420);
    @Override
    protected void doneSearching() {

    }

    @Override
    public void visitMap(Map map) {

    }

    @Override
    public void visitAbilityItem(AbilityItem abilityItem) {

    }

    @Override
    public void visitAvatar(Avatar avatar) {
        // set priority yadyadyayd
        //System.out.println("agressive npc sees the avatar :o");

        int roll = roller.nextInt(10);
//        System.out.println("I just rolled a " + roll);
//        System.out.println("I need > " + getCurrRing()*avatar.getStats().getConcealment());
        if (roll > getCurrRing()*avatar.getStats().getConcealment()) {
            Target currTarget = new AvatarTarget(0);
            currTarget.setCoord(this.getCurrentCoord());
            this.setHighestPriorityTarget(this.getMaxTarget(currTarget, this.getHighestPriorityTarget()));
        }

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
    public void visitBow(Bow bow) {

    }

    @Override
    public void visitStaff(Staff staff) {

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
    public void visitInteractiveItem(InteractiveItem item) {

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

    @Override
    public void visitSkillManager(SkillManager skillManager) {

    }

    @Override
    public void visitSkill(Skill skill) {

    }

    @Override
    public void visitMovableHitBox(MovableHitBox movableHitBox) {

    }

    @Override
    public void visitImmovableHitBox(ImmovableHitBox immovableHitBox) {

    }

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
    public void visitAngleSpellAbility(FlameThrowerAbility flameThrowerAbility) {

    }


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
    public void visitTrap(Trap trap) {
    }

    public void visitAdaptableStrategy(AdaptableStrategy adaptableStrategy) {

    }

    @Override
    public void visitRemoveTrapAbility(RemoveTrapAbility removeTrapAbility) {}

    @Override
    public void visitStealthAbility(StealthAbility stealthAbility) {

    }

    @Override
    public void visitNPCFallAsleepAbility(NPCFallAsleepAbility npcFallAsleepAbility) {

    }

    public void visitGold(Gold gold) {

    }

    @Override
    public void visitPiggyTotem(PiggyTotem piggyTotem) {

    }

    @Override
    public void visitBoonBuffAbility(BoonBuffAbility boonBuffAbility) {

    }

    @Override
    public void visitProtectFromEvilBuff(ProtectFromEvilBuff protectFromEvilBuff) {

    }

    @Override
    public void visitTimedBuff(TimedBuff timedBuff) {

    }

    @Override
    public void visitHealOverTimeBuff(HealOverTimeBuff healOverTimeBuff) {

    }

    @Override
    public void visitTradeDialogueContainer(TradeDialogContainer tradeDialogContainer) {

    }

    @Override
    public void visitTerminalDialogueContainer(TerminalDialogContainer terminalDialogContainer) {

    }

    @Override
    public void visitPickPocketAbility(PickPocketAbility pickPocketAbility) {

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
