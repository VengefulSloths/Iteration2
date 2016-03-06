package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.DecalViewObject;

/**
 * Created by alexs on 2/23/2016.
 */
public interface VOVisitor {
    void visitEntity(EntityViewObject e);
    void visitPiggy(PiggyViewObject p);
    void visitGrass(GrassViewObject g);
    void visitMountain(MountainViewObject m);
    void visitWater(WaterViewObject w);
    void visitTile(TileViewObject t);
    void visitOneShot(OneShotViewObject o);
    void visitTakeable(TakeableViewObject t);
    void visitDecal(DecalViewObject d);
    void visitAttack(AttackViewObject a);
}
