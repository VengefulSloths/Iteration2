package com.vengeful.sloths.AreaView.ViewObjects;

/**
 * Created by alexs on 2/23/2016.
 */
public class VOSorter implements VOVisitor {
    private int zLevel;

    public int getZLevel() {
        return zLevel;
    }
    @Override
    public void visitEntity(EntityViewObject e) {
        zLevel = 1000;
    }

    @Override
    public void visitGrass(GrassViewObject g) {
        zLevel = 200;
    }

    @Override
    public void visitMountain(MountainViewObject m) {
        zLevel = 2000;
    }
}
