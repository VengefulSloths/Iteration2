package com.vengeful.sloths.AreaView.ViewObjects;

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
    void visitDamageNumber(DamageNumberViewObject d);
    void visitMovableHitBoxViewObject(MovableHitBoxViewObject m);
    void visitImmovableHitBoxViewObject(ImmovableHitBoxViewObject i);
    void visitInteractiveItem(InteractiveItemViewObject i);
    void visitAE(AEViewObject a);

    void acceptGold(GoldViewObject goldViewObject);
}
