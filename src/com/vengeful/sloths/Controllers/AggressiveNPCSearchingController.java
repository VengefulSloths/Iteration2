package com.vengeful.sloths.Controllers;

import com.vengeful.sloths.Models.Entity.*;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.MapItems.Obstacle;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Map.Tile;

import java.util.Iterator;

/**
 * Created by zach on 2/22/16.
 */
public class AggressiveNPCSearchingController extends SearchingController {

    public AggressiveNPCSearchingController(Map map, Entity entity, boolean isSearching) {
        super(map, entity, isSearching);
    }

    @Override
    public void visit(Object o) {

    }

    @Override
    public void visitAvatar(Avatar avatar) {

    }

    @Override
    public void visitPiggy(Piggy piggy) {

    }

    @Override
    public void visitAggressiveNPC(AggressiveNPC aNPC) {

    }

    @Override
    public void visitNonAggressiveNPC(NonAggressiveNPC nonANPC) {

    }

    public void visitTile(Tile tile) {
        Iterator<Entity> iter = tile.getEntityIterator();
        while (iter.hasNext()) {
            iter.next().accept(this);
        }
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

}
