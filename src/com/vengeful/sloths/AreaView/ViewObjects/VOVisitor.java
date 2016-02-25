package com.vengeful.sloths.AreaView.ViewObjects;

/**
 * Created by alexs on 2/23/2016.
 */
public interface VOVisitor {
    void visitEntity(EntityViewObject e);
    void visitGrass(GrassViewObject g);
    void visitMountain(MountainViewObject m);
    void visitTile(TileViewObject t);
}
