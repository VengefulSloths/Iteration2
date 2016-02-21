package com.vengeful.sloths.Models.Effects;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.AreaEffects.AreaEffect;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by qianwen on 2/6/16.
 */

public class EffectCommandFactory {

    Map map;
    //Entity entity;

    public EffectCommandFactory(Map map){
        this.map = map;
        //this.entity = entity;
    }


    public EffectCommand createHealDamageAECommand(Entity entity, int health, Coord currentLoc){
        return new HealDamageAECommand(entity, health, currentLoc);
    }

    public EffectCommand createInstantDeathAECommand(Entity entity){
        return new InstantDeathAECommand(entity);
    }

    public EffectCommand createLevelUpAECommand(Entity entity, AreaEffect ae){
        return new LevelUpAECommand(entity, ae);
    }

    public EffectCommand createTakeDamageAECommand(Entity entity, int damage, Coord currentLoc){
        return new TakeDamageAECommand(entity, damage, currentLoc);
    }


    public EffectCommand createDestroyObstacleCommand(MapItem obstacle, Tile tile){
        return new DestroyObstacleCommand(obstacle, tile);
    }

    public EffectCommand createAECreationCommand(AreaEffect ae, Coord coord){
        return new AECreationCommand(ae, coord, this.map);
    }

}
