package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.*;
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
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.*;
import com.vengeful.sloths.Models.ObserverManager;

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
import com.vengeful.sloths.Utility.Direction;


import com.vengeful.sloths.Models.Observers.ProxyDestoyableObserver;
import com.vengeful.sloths.Models.Observers.ProxyEntityObserver;
import com.vengeful.sloths.Models.Observers.ProxyStatsObserver;

 /**
 * Created by alexs on 2/23/2016.
 */
public class TemporaryVOCreationVisitor implements ModelVisitor {
    private static TemporaryVOCreationVisitor instance;
    public static TemporaryVOCreationVisitor getInstance() {
        if (instance == null) {
            instance = new TemporaryVOCreationVisitor();
        }
        return instance;
    }
    private TemporaryVOCreationVisitor() {

    }


    private ViewObjectFactory factory;
    private CameraView activeCameraView;

    public CameraView getActiveCameraView() {
        return activeCameraView;
    }

    public void setActiveCameraView(CameraView activeCameraView) {
        this.activeCameraView = activeCameraView;
        this.factory = activeCameraView.getFactory();
    }

     @Override
     public void visitShuriken(Shuriken shuriken) {

     }

     @Override
     public void visitAbilityItem(AbilityItem abilityItem) {

     }

     @Override
    public void visitMap(Map map) {

    }

    public DamageNumberViewObject createDamageNumber(int r, int s, int damage) {
        DamageNumberViewObject number = factory.createDamageNumberViewObject(r, s, damage);
        activeCameraView.addViewObject(number);
        return number;
    }

    public AttackViewObject createAttack(int r, int s, String resourcePath, long duration) {
        AttackViewObject attack =  factory.createAttackViewObject(r, s, resourcePath, duration);
        activeCameraView.addViewObject(attack);
        return attack;
    }
    public AttackViewObject createAttack(int r, int s, String resourcePath, long duration, boolean isInFront) {
        AttackViewObject attack =  factory.createAttackViewObject(r, s, resourcePath, duration, isInFront);
        activeCameraView.addViewObject(attack);
        return attack;
    }

    public HatViewObject createHat(int r, int s, String resourcePath, Direction direction) {
        return factory.createHatViewObject(r, s, resourcePath, direction);
    }



    private AvatarViewObject avo;
    private ProxyEntityObserver peo;
    private ProxyEntityObserver petPeo;


    private String avatarOccupation;
    @Override
    public void visitAvatar(Avatar avatar) {
        if (peo != null) {
            peo.deregister();
        }
        avatar.getOccupation().accept(this);

        this.avo = factory.createAvatarViewObject(avatar.getLocation().getR(),
                avatar.getLocation().getS(),
                "resources/entities/" + avatarOccupation + "/");


        //Let avo observe avatar through a proxy
        peo = new ProxyEntityObserver(avo, avatar);
        ObserverManager.getInstance().addProxyObserver(peo);
        //Let the AvatarViewFollower follow the avo
        AvatarViewFollower.getInstance().bindToViewObject(avo);

        //let the cameraView watch avatar for movement
        avo.registerObserver(activeCameraView);
        ObserverManager.getInstance().addProxyObserver(new ProxyStatsObserver(avo.getHealthBar(), avatar.getStats()));
        avatar.getStats().updateObservers();

        //Set the camera views avatar to this

        activeCameraView.addAvatar(avo);

        avatar.getEquipped().alertAllEntityObserversEverything();
        avatar.getBuffManager().alertObserversEverything();


    }

    @Override
    public void visitPiggy(Piggy piggy) {
        if (this.petPeo != null) {
            this.petPeo.deregister();
        }
        PiggyViewObject pvo = factory.createPiggyViewObject(piggy.getLocation().getR(), piggy.getLocation().getS(), "resources/entities/piggy/");
        this.petPeo = new ProxyEntityObserver(pvo, piggy);
        ObserverManager.getInstance().addProxyObserver(this.petPeo);
        pvo.registerObserver(activeCameraView);

        ObserverManager.getInstance().addProxyObserver(new ProxyStatsObserver(pvo.getHealthBar(), piggy.getStats()));

        piggy.getStats().updateObservers();
        activeCameraView.addViewObject(pvo);

        System.out.println("CREATING PET VO @" + pvo.getR() + ", " +  pvo.getS());
    }

    @Override
    public void visitAggressiveNPC(AggressiveNPC aNPC) {
        EvilBlobViewObject ebvo = factory.createEvilBlobViewObject(aNPC.getLocation().getR(), aNPC.getLocation().getS(), "resources/entities/cyclops/");
        ObserverManager.getInstance().addProxyObserver(new ProxyEntityObserver(ebvo, aNPC));
        //new ProxyEntityObserver(ebvo, aNPC);
        ebvo.registerObserver(activeCameraView);
        ObserverManager.getInstance().addProxyObserver(new ProxyStatsObserver(ebvo.getHealthBar(), aNPC.getStats()));
        aNPC.getStats().updateObservers();
        aNPC.getEquipped().alertAllEntityObserversEverything();

        if (!aNPC.getShirt().isEmpty()) {
            ebvo.wearShirt(aNPC.getShirt());
        }
        this.activeCameraView.addViewObject(ebvo);
    }



    @Override
    public void visitNonAggressiveNPC(NonAggressiveNPC aNPC) {
        EvilBlobViewObject ebvo = factory.createEvilBlobViewObject(aNPC.getLocation().getR(), aNPC.getLocation().getS(), "resources/entities/smasher/");
        if(aNPC.getName().equalsIgnoreCase("Jana")) {
            ebvo = factory.createEvilBlobViewObject(aNPC.getLocation().getR(), aNPC.getLocation().getS(), "resources/entities/cyclops/");
        }
        ObserverManager.getInstance().addProxyObserver(new ProxyEntityObserver(ebvo, aNPC));
        //new ProxyEntityObserver(ebvo, aNPC);
        ebvo.registerObserver(activeCameraView);
        ObserverManager.getInstance().addProxyObserver(new ProxyStatsObserver(ebvo.getHealthBar(), aNPC.getStats()));
        aNPC.getStats().updateObservers();
        aNPC.getEquipped().alertAllEntityObserversEverything();
        if (!aNPC.getShirt().isEmpty()) {
            ebvo.wearShirt(aNPC.getShirt());
        }
        this.activeCameraView.addViewObject(ebvo);
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
        this.avatarOccupation = "summoner";
    }

    @Override
    public void visitSneak(Sneak s) {
        this.avatarOccupation = "sneak";
    }

    @Override
    public void visitSmasher(Smasher s) {
        this.avatarOccupation = "smasher";
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


    //Not likely to need things below here
    @Override
    public void visitMapArea(MapArea mapArea) {

    }

    @Override
    public void visitTile(Tile tile) {

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
        //used for dropping
        String imagePath = "resources/items/"+takeableItem.getItemName()+"/"+takeableItem.getItemName()+".xml";
        System.out.println("IMAGE PATH: ");
        System.out.println(imagePath);
        TakeableViewObject tvo = factory.createTakeableViewObject(takeableItem.getLocation().getR(), takeableItem.getLocation().getS(), imagePath);
        new ProxyDestoyableObserver(tvo, takeableItem);
        tvo.registerObserver(this.activeCameraView.getTileVO(tvo));
        this.activeCameraView.addViewObject(tvo);
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

     public void visitTrap(Trap trap) {
         String imagePath = "resources/trap/trap.xml";
         TrapViewObject trapViewObject = factory.createTrapViewObject(trap.getLocation().getR(), trap.getLocation().getS(), imagePath);
         new ProxyDestoyableObserver(trapViewObject, trap);
         this.activeCameraView.addViewObject(trapViewObject);
     }

     public void visitAdaptableStrategy(AdaptableStrategy adaptableStrategy) {

     }

     @Override
     public void visitGold(Gold gold) {
         String imagePath = "resources/items/"+gold.getItemName()+"/"+gold.getItemName()+".xml";
         System.out.println("IMAGE PATH: ");
         System.out.println(imagePath);
         GoldViewObject tvo = factory.createGoldViewObject(gold.getLocation().getR(), gold.getLocation().getS(), imagePath);
         new ProxyDestoyableObserver(tvo, gold);
         tvo.registerObserver(this.activeCameraView.getTileVO(tvo));
         this.activeCameraView.addViewObject(tvo);
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
        String imagePath = "resources/effects/"+movableHitBox.getName()+"/";
        MovableHitBoxViewObject hbvo = factory.createMovableHitBoxViewObject(movableHitBox.getLocation().getR(), movableHitBox.getLocation().getS(), imagePath, movableHitBox.getDirection());
        new ProxyHitBoxObserver(hbvo, movableHitBox);
        hbvo.registerObserver(this.activeCameraView); //register for Movement observer
        //hbvo.registerObserver(this.activeCameraView.getTileVO(hbvo)); //register for destroyableObserver
        this.activeCameraView.addViewObject(hbvo);
    }

    @Override
    public void visitImmovableHitBox(ImmovableHitBox immovableHitBox) {
        //String imagePath = "resources/effects/"+immovableHitBox.getName()+"/"+immovableHitBox.getName()+".xml";
        String imagePath = "resources/effects/"+immovableHitBox.getName()+"/"+immovableHitBox.getName();
        ImmovableHitBoxViewObject hbvo = factory.createImmovableHitBoxViewObject(immovableHitBox.getLocation().getR(), immovableHitBox.getLocation().getS(), imagePath);
        new ProxyHitBoxObserver(hbvo, immovableHitBox);
        this.activeCameraView.addViewObject(hbvo);
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
