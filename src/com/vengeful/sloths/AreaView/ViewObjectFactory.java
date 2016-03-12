package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.ViewObjects.*;
import com.vengeful.sloths.AreaView.ViewObjects.CoordinateStrategies.CoordinateStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.LocationStrategies.LocationStrategy;
import com.vengeful.sloths.AreaView.ViewObjects.NullTile;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Views.AbilitiesView.AbilityViewObject;

/**
 * Created by alexs on 2/22/2016.
 */

public abstract class ViewObjectFactory {
    private CoordinateStrategy cs;
    private LocationStrategy ls;

    public ViewObjectFactory(CoordinateStrategy cs, LocationStrategy ls) {
        this.cs = cs;
        this.ls = ls;
    }

    public TileViewObject createTileViewObject(int r, int s) {
        return new TileViewObject(r, s, cs, ls);
    }

    public TileViewObject createNullTileViewObject(int r, int s) { return new NullTile(r, s, cs, ls); }

    public EntityViewObject createEnitityViewObject(int r, int s, String resourcePath) {
        return new EntityViewObject(r, s, cs, ls, resourcePath);
    }

    public AvatarViewObject createAvatarViewObject(int r, int s, String resourcePath) {
        return new AvatarViewObject(r, s, cs, ls, resourcePath);
    }
    public EvilBlobViewObject createEvilBlobViewObject(int r, int s, String resourcePath){
        return new EvilBlobViewObject(r,s,cs,ls,resourcePath);
    }

    public PiggyViewObject createPiggyViewObject(int r, int s, String resourcePath) {
        return new PiggyViewObject(r, s, cs, ls, resourcePath);
    }

    public DecalViewObject createDecalViewObject(int r, int s, String resourcePath) {
        return new DecalViewObject(r, s, cs, ls, resourcePath);
    }

    public abstract GrassViewObject createGrassViewObject(int r, int s);
    public abstract GrassViewObject createRoadViewObject(int r, int s);
    public abstract MountainViewObject createMountainTerrainViewObject(int r, int s);
    public abstract WaterViewObject createWaterTerrainViewObject(int r, int s);
    public abstract OneShotViewObject createOneShotViewObject(int r, int s);
    public abstract OneShotViewObject createObstacleViewObject(int r, int s);

    public TakeableViewObject createTakeableViewObject(int r, int s, String resourcePath){
        return new TakeableViewObject(r, s, cs, ls, resourcePath);
    }

    public InteractiveItemViewObject createInteractiveItemViewObject(int r, int s, String inactivePath, String activePath) {
        return new InteractiveItemViewObject(r, s, cs, ls, inactivePath, activePath);
    }

    public AttackViewObject createAttackViewObject(int r, int s, String resourcePath, long duration) {
        return new AttackViewObject(r, s, cs, ls, resourcePath, duration);
    }
    public AttackViewObject createAttackViewObject(int r, int s, String resourcePath, long duration, boolean isInFront) {
        return new AttackViewObject(r, s, cs, ls, resourcePath, duration, isInFront);
    }

    public HatViewObject createHatViewObject(int r, int s, String resourcePath, Direction d) {
        return new HatViewObject(r, s, cs, ls, resourcePath, d);
    }

    public DamageNumberViewObject createDamageNumberViewObject(int r, int s, int damage) {
        return new DamageNumberViewObject(r, s, cs, ls, damage);
    }

    public LocationStrategy getLocationStrategy() {
        return ls;
    }

    public CoordinateStrategy getCoordinateStrategy() {
        return cs;
    }

    public MovableHitBoxViewObject createMovableHitBoxViewObject(int r, int s, String resourcePath, Direction d){
        return new MovableHitBoxViewObject(r, s, cs, ls, resourcePath, d);
    }

    public ImmovableHitBoxViewObject createImmovableHitBoxViewObject(int r, int s, String resourcePath){
        return new ImmovableHitBoxViewObject(r, s, cs, ls, resourcePath);
    }

    public AEViewObject createAEViewObject(int r, int s, String resourecPath) {
        return new AEViewObject(r, s, cs, ls, resourecPath);
    }


    public TrapViewObject createTrapViewObject(int r, int s, String resourcePath){
        return new TrapViewObject(r, s, cs, ls, resourcePath);
    }


    public GoldViewObject createGoldViewObject(int r, int s, String imagePath) {
        return new GoldViewObject(r,s,cs,ls,imagePath);
    }

}
