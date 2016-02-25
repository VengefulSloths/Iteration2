package com.vengeful.sloths.Models;

import com.vengeful.sloths.Models.Entity.*;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.MapItems.Obstacle;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Terrains.Water;
import com.vengeful.sloths.Models.Map.Tile;

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

    void visitMapArea(MapArea mapArea);

    void visitTile(Tile tile);

    void visitMapItem(MapItem mapItem);

    void visitTakeableItem(TakeableItem takeableItem);
    void visitObstacle(Obstacle obstacle);

    void visitGrass(Grass grass);
    void visitMountain(Mountain mountain);
    void visitWater(Water water);
}
