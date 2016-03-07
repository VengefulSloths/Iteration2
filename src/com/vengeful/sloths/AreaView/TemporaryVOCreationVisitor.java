package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.*;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.SimpleHexCoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.CenterAvatarLocationStrategy;
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
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Occupation.DummyOccupation;
import com.vengeful.sloths.Models.Occupation.Smasher;
import com.vengeful.sloths.Models.Occupation.Sneak;
import com.vengeful.sloths.Models.Occupation.Summoner;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.View.Observers.ModelObserver;
import com.vengeful.sloths.View.Observers.ProxyDestoyableObserver;
import com.vengeful.sloths.View.Observers.ProxyEntityObserver;

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
    public void visitMap(Map map) {

    }



    public AttackViewObject createAttack(int r, int s, String resourcePath, long duration) {
        AttackViewObject attack =  factory.createAttackViewObject(r, s, resourcePath, duration);
        activeCameraView.addViewObject(attack);
        return attack;
    }

    public HatViewObject createHat(int r, int s, String resourcePath, Direction direction) {
        return factory.createHatViewObject(r, s, resourcePath, direction);
    }


    private boolean firstAvatarFlag = true;
    private AvatarViewObject avo;
    private ProxyEntityObserver peo;
    @Override
    public void visitAvatar(Avatar avatar) {
        if (peo != null) {
            peo.deregister();
        }
        this.avo = factory.createAvatarViewObject(avatar.getLocation().getR(),
                avatar.getLocation().getS(),
                "resources/entities/smasher/");

        //Let avo observe avatar through a proxy
        peo = new ProxyEntityObserver(avo, avatar);

        //Let the AvatarViewFollower follow the avo
        AvatarViewFollower.getInstance().bindToViewObject(avo);

        //let the cameraView watch avatar for movement
        avo.registerObserver(activeCameraView);
        avatar.getStats().registerObserver(avo.getHealthBar());
        avatar.getStats().updateObservers();

        //Set the camera views avatar to this
        activeCameraView.addAvatar(avo);

    }

    @Override
    public void visitPiggy(Piggy piggy) {
        PiggyViewObject pvo = factory.createPiggyViewObject(piggy.getLocation().getR(), piggy.getLocation().getS(), "resources/entities/piggy/");
        piggy.registerObserver(pvo);
        pvo.registerObserver(activeCameraView);
        this.activeCameraView.addViewObject(pvo);
    }

    @Override
    public void visitAggressiveNPC(AggressiveNPC aNPC) {
        EvilBlobViewObject ebvo = factory.createEvilBlobViewObject(aNPC.getLocation().getR(), aNPC.getLocation().getS(), "resources/entities/cyclops/");
        aNPC.registerObserver(ebvo);
        ebvo.registerObserver(activeCameraView);
        aNPC.getStats().registerObserver(ebvo.getHealthBar());
        aNPC.getStats().updateObservers();
        this.activeCameraView.addViewObject(ebvo);
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
        TakeableViewObject tvo = factory.createTakeableViewObject(takeableItem.getLocation().getR(), takeableItem.getLocation().getS(), imagePath);
        new ProxyDestoyableObserver(tvo, takeableItem);
        tvo.registerObserver(this.activeCameraView.getTileVO(tvo));
        this.activeCameraView.addViewObject(tvo);
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
