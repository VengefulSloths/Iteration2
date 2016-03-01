package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.OneShotViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.TileViewObject;
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
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.OneHandedWeapon;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.TwoHandedWeapon;
import com.vengeful.sloths.Models.InventoryItems.UsableItems.UsableItems;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.MapItems.Obstacle;
import com.vengeful.sloths.Models.Map.MapItems.OneShotItem;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Terrains.Water;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Occupation.DummyOccupation;
import com.vengeful.sloths.Models.Occupation.Smasher;
import com.vengeful.sloths.Models.Occupation.Sneak;
import com.vengeful.sloths.Models.Occupation.Summoner;
import com.vengeful.sloths.Models.Stats.StatAddables.StatsAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.View.Observers.ProxyDestoyableObserver;
import com.vengeful.sloths.View.Observers.ProxyObserver;

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
                mapArea.getTile(new Coord(r, s)).accept(this);
            }
        }
    }

    @Override
    public void visitTile(Tile tile) {
        currentTile = factory.createTileViewObject(r, s);

        //TODO: visit the rest of the tile
        tile.getTerrain().accept(this);
        Iterator<MapItem> iter = tile.getMapItemIterator();
        while (iter.hasNext()) {
            iter.next().accept(this);
        }

        tiles.add(currentTile);

    }
    @Override
    public void visitMapItem(MapItem mapItem) {

    }
    @Override
    public void visitTakeableItem(TakeableItem takeableItem) {

    }
    @Override
    public void visitObstacle(Obstacle obstacle) {

    }

    @Override
    public void visitOneShotItem(OneShotItem osi) {
        OneShotViewObject oneShotViewObject = factory.createOneShotViewObject(r, s);
        new ProxyDestoyableObserver(oneShotViewObject, osi);
        currentTile.addChild(oneShotViewObject);
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
        currentTile.addChild(factory.createWaterTerrainViewObject(r, s));
    }

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
    public void visitPiggy(Piggy piggy) {}

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
    public void visitOneHandedWeapon(OneHandedWeapon ohw) {

    }

    @Override
    public void visitTwoHandedWeapon(TwoHandedWeapon thw) {

    }

    @Override
    public void visitStatsAddable(StatsAddable sa) {

    }


}
