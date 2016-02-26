package com.vengeful.sloths.Controllers.Target;

import com.vengeful.sloths.Models.Entity.AggressiveNPC;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.NonAggressiveNPC;
import com.vengeful.sloths.Models.Entity.Piggy;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.MapItems.Obstacle;
import com.vengeful.sloths.Models.Map.MapItems.TakeableItem;
import com.vengeful.sloths.Models.Map.Terrains.Grass;
import com.vengeful.sloths.Models.Map.Terrains.Mountain;
import com.vengeful.sloths.Models.Map.Terrains.Water;
import com.vengeful.sloths.Models.Map.Tile;

/**
 * Created by zach on 2/25/16.
 */
public interface TargetVisitor {
    void visitAvatarTarget(AvatarTarget avatar);
    void visitPiggyTarget(PiggyTarget piggy);
    void visitAggressiveNPCTarget(AgressiveNPCTarget aNPC);
    void visitNonAggressiveNPCTarget(NonAgressiveNPCTarget nonANPC);
}
