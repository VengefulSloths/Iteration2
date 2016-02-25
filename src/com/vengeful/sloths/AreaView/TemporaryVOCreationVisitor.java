package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.AvatarViewObject;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.SimpleHexCoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.CenterAvatarLocationStrategy;
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
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.View.Observers.ModelObserver;

/**
 * Created by alexs on 2/23/2016.
 */
public class TemporaryVOCreationVisitor implements ModelVisitor {
    private static TemporaryVOCreationVisitor instance;
    public static TemporaryVOCreationVisitor getInstance() {
        if (instance == null) {
            instance = new TemporaryVOCreationVisitor();
        }
        return instance;
    }
    private TemporaryVOCreationVisitor() {

    }


    private ViewObjectFactory factory;
    private CameraView activeCameraView;

    public void setActiveCameraView(CameraView activeCameraView) {
        this.activeCameraView = activeCameraView;
        this.factory = activeCameraView.getFactory();
    }




    @Override
    public void visitAvatar(Avatar avatar) {
        AvatarViewObject avo = factory.createAvatarViewObject(avatar.getLocation().getR(), avatar.getLocation().getS(), "entities/smasher/");

        //Let avo observe avatar
        avatar.registerObserver(avo);

        //let the cameraView watch avatar for movement
        avo.registerObserver(activeCameraView);

        //Add vo to the camera view
        this.activeCameraView.addViewObject(avo);
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


    //Not likely to need things below here
    @Override
    public void visitMapArea(MapArea mapArea) {

    }

    @Override
    public void visitTile(Tile tile) {

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

    }

    @Override
    public void visitMountain(Mountain mountain) {

    }

    @Override
    public void visitWater(Water water) {

    }
}
