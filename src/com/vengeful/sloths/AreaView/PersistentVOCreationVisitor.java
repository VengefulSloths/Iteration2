package com.vengeful.sloths.AreaView;



import com.vengeful.sloths.AreaView.ViewObjects.*;
import com.vengeful.sloths.AreaView.ViewObjects.InteractiveItemViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.OneShotViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.TakeableViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.TileViewObject;
import com.vengeful.sloths.Controllers.InputController.InputStrategies.AdaptableStrategy;
import com.vengeful.sloths.Models.Ability.Abilities.*;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.PickPocketAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.RemoveTrapAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities.StealthAbility;
import com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities.*;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.*;
import com.vengeful.sloths.Models.DialogueTrade.TerminalDialogContainer;
import com.vengeful.sloths.Models.DialogueTrade.TradeDialogContainer;
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
import com.vengeful.sloths.Models.Map.AreaEffects.*;
import com.vengeful.sloths.Models.Map.MapItems.*;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.InteractiveItem;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.BreakBoxQuest;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.DoDestroyObstacleQuest;
import com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest.HasItemQuest;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Terrains.Water;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.ProxyInteractiveItemObserver;
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
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Models.Observers.ProxyDestoyableObserver;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by alexs on 2/23/2016.
 */
public class PersistentVOCreationVisitor implements ModelVisitor{
    private ArrayList<TileViewObject> tiles = new ArrayList<>();
    private TileViewObject currentTile;

    private int r;
    private int s;

    private ViewObjectFactory factory;

    public PersistentVOCreationVisitor(ViewObjectFactory factory) {
        this.factory = factory;
    }

    public Iterator<TileViewObject> getTiles() {
        return tiles.iterator();
    }

    @Override
    public void visitMapArea(MapArea mapArea) {
        for (int i=0; i<mapArea.getMaxR(); i++) {
            for (int j=0; j<mapArea.getMaxS(); j++) {
                r = i;
                s = j;
                try {
                    mapArea.getTile(new Coord(r, s)).accept(this);
                } catch (Exception e) {
                    //do nothing its fine
                }
            }
        }
    }

    @Override
    public void visitTile(Tile tile) {
        currentTile = factory.createTileViewObject(r, s);

        //TODO: visit the rest of the tile
        tile.getTerrain().accept(this);
        Iterator<MapItem> itemIterator = tile.getMapItemIterator();
        while (itemIterator.hasNext()) {
            itemIterator.next().accept(this);
        }

        Iterator<AreaEffect> aeIterator = tile.getAreaEffectIterator();
        while (aeIterator.hasNext()) {
            aeIterator.next().accept(this);
        }


        tiles.add(currentTile);

    }

    @Override
    public void visitInteractiveItem(InteractiveItem item) {
        InteractiveItemViewObject interactiveItemViewObject = factory.createInteractiveItemViewObject(r, s, "resources/items/button/button.xml", "resources/items/button/button_activated.xml");
        new ProxyInteractiveItemObserver(interactiveItemViewObject, item);
        currentTile.addChild(interactiveItemViewObject);
    }

    @Override
    public void visitMapItem(MapItem mapItem) {

    }

    @Override
    public void visitTeleportSenderTile(TeleportSenderTile t) {
        visitTile(t);
        this.currentTile.addChild(factory.createDecalViewObject(r, s, "resources/effects/teleporting/teleport.xml"));
    }

    @Override
    public void visitTeleportDestinationTile(TeleportDestinationTile t) {
        visitTile(t);
    }

    @Override
    public void visitTakeableItem(TakeableItem takeableItem) {
        //used to populate map with takeable item when game starts
        String imagePath = "resources/items/"+takeableItem.getItemName()+"/"+takeableItem.getItemName()+".xml";
        System.out.println(imagePath);
        TakeableViewObject takeableViewObject = factory.createTakeableViewObject(r, s, imagePath);
        new ProxyDestoyableObserver(takeableViewObject, takeableItem);
        takeableViewObject.registerObserver(currentTile); //tileViewObject listen for takeable vo destroy
        currentTile.addChild(takeableViewObject);
    }

    @Override
    public void visitObstacle(Obstacle obstacle) {
        OneShotViewObject obstacleViewObject = factory.createObstacleViewObject(r, s);
        new ProxyDestoyableObserver(obstacleViewObject, obstacle);
        currentTile.addChild(obstacleViewObject);
    }

    @Override
    public void visitOneShotItem(OneShotItem osi) {
        OneShotViewObject oneShotViewObject = factory.createOneShotViewObject(r, s);
        new ProxyDestoyableObserver(oneShotViewObject, osi);
        currentTile.addChild(oneShotViewObject);
    }

    @Override
    public void visitTrap(Trap trap) {
        /* will not be shown at beginning of the game
        String imagePath = "resources/trap/trap.xml";
        TrapViewObject trapViewObject = factory.createTrapViewObject(r, s, imagePath);
        new ProxyDestoyableObserver(trapViewObject, trap);
        currentTile.addChild(trapViewObject);
        */
    }

    @Override
    public void visitTakeDamageAE(TakeDamageAE t) {
        currentTile.addChild(factory.createAEViewObject(r, s, "resources/aoe/aoe_damage.xml"));
    }

    @Override
    public void visitHealDamageAE(HealDamageAE h) {
        currentTile.addChild(factory.createAEViewObject(r, s, "resources/aoe/aoe_heal.xml"));
    }

    @Override
    public void visitShuriken(Shuriken shuriken) {

    }

    @Override
    public void visitInstantDeathAE(InstantDeathAE i) {
        currentTile.addChild(factory.createAEViewObject(r, s, "resources/aoe/aoe_death.xml"));
    }

    @Override
    public void visitLevelUpAE(LevelUpAE ae) {
        currentTile.addChild(factory.createAEViewObject(r, s, "resources/aoe/aoe_level.xml"));
    }

    @Override
    public void visitGrass(Grass grass) {
        currentTile.addChild(factory.createGrassViewObject(r,s));
    }

    @Override
    public void visitMountain(Mountain mountain) {
        currentTile.addChild(factory.createMountainTerrainViewObject(r, s));
    }

    @Override
    public void visitWater(Water water) {
        if(!water.isSky()){
            currentTile.addChild(factory.createWaterTerrainViewObject(r, s));
        }else{
        }

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

    @Override
    public void visitRemoveTrapAbility(RemoveTrapAbility removeTrapAbility) {

    }

    @Override
    public void visitStealthAbility(StealthAbility stealthAbility) {

    }

    @Override
    public void visitNPCFallAsleepAbility(NPCFallAsleepAbility npcFallAsleepAbility) {

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
    public void visitWeakenNPCAbility(WeakenNPCAbility weakenNPCAbility) {

    }

    @Override
    public void visitPoisonNPCAbility(PoisonNPCAbility poisonNPCAbility) {

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
    public void visitAdaptableStrategy(AdaptableStrategy adaptableStrategy) {

    }


    @Override
    public void visitGold(Gold gold) {
        String imagePath = "resources/items/"+gold.getItemName()+"/"+gold.getItemName()+".xml";
        GoldViewObject goldViewObject = factory.createGoldViewObject(r, s, imagePath);
        new ProxyDestoyableObserver(goldViewObject, gold);
        goldViewObject.registerObserver(currentTile); //tileViewObject listen for takeable vo destroy
        currentTile.addChild(goldViewObject);
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

    @Override
    public void visitMap(Map map) {

    }

    //**********************************************************************
    //*     Methods below will need to register the cameraview as an observer
    //**********************************************************************
    @Override
    public void visitAvatar(Avatar avatar) {
        //Avatar should be made dynmically
    }

    @Override
    public void visitPiggy(Piggy piggy) {

    }

    @Override
    public void visitAggressiveNPC(AggressiveNPC aNPC) {}

    @Override
    public void visitNonAggressiveNPC(NonAggressiveNPC nonANPC) {}

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
    public void visitAbilityItem(AbilityItem abilityItem) {

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


}
