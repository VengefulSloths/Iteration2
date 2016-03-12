package com.vengeful.sloths.AreaView.ViewObjects;

import com.vengeful.sloths.AreaView.CameraView;

import java.util.ArrayList;

/**
 * Created by alexs on 3/11/2016.
 */
public class VOCleaner implements VOVisitor {
    private CameraView cameraView;
    private TileViewObject currentTile;

    public void visitCameraView(CameraView c) {
        this.cameraView = c;
        for (int i=0; i<c.getMaxR(); i++) {
            for (int j = 0; j < c.getMaxS(); j++) {
                c.getTileVO(i, j).accept(this);
            }
        }

    }

    @Override
    public void visitTile(TileViewObject t) {
        currentTile = t;
        ArrayList<ViewObject> vos = t.getChildren();
        for (int i = vos.size() -1; i >=0; i--) {
            vos.get(i).accept(this);
        }
    }

    @Override
    public void visitEntity(EntityViewObject e) {
        e.deregisterObserver(cameraView);
        currentTile.reallyRemoveChild(e);
    }

    @Override
    public void visitPiggy(PiggyViewObject p) {
        p.deregisterObserver(cameraView);
        currentTile.reallyRemoveChild(p);
    }

    @Override
    public void visitGrass(GrassViewObject g) {

    }

    @Override
    public void visitMountain(MountainViewObject m) {

    }

    @Override
    public void visitWater(WaterViewObject w) {

    }



    @Override
    public void visitOneShot(OneShotViewObject o) {

    }

    @Override
    public void visitTakeable(TakeableViewObject t) {

    }

    @Override
    public void visitDecal(DecalViewObject d) {

    }

    @Override
    public void visitAttack(AttackViewObject a) {

    }

    @Override
    public void visitDamageNumber(DamageNumberViewObject d) {

    }

    @Override
    public void visitMovableHitBoxViewObject(MovableHitBoxViewObject m) {

    }

    @Override
    public void visitImmovableHitBoxViewObject(ImmovableHitBoxViewObject i) {

    }

    @Override
    public void visitInteractiveItem(InteractiveItemViewObject i) {

    }

    @Override
    public void visitAE(AEViewObject a) {

    }

    @Override
    public void visitTrapViewObject(TrapViewObject t) {

    }
}
