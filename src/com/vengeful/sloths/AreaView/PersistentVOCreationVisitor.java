package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.TileViewObject;
import com.vengeful.sloths.Models.Entity.AggressiveNPC;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.NonAggressiveNPC;
import com.vengeful.sloths.Models.Entity.Piggy;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.MapItems.Obstacle;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Terrains.Water;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Utility.Coord;

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
    public void visitGrass(Grass grass) {
        currentTile.addChild(factory.createGrassViewObject(r,s));
    }

    @Override
    public void visitMountain(Mountain mountain) {
        currentTile.addChild(factory.createMountainTerrainViewObject(r, s));
    }

    @Override
    public void visitWater(Water water) {
        //TODO: create actual waterVO
        currentTile.addChild(factory.createRoadViewObject(r, s));
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








}
