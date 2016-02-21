package com.vengeful.sloths.Models.ActionCommandFactory;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by zach on 1/30/16.
 */
public abstract class MovementCommand implements Alertable {

    Map map;
    Coord src;
    Coord dst;
    Entity entity;
    Direction direction;
    int movementSpeed;

    public MovementCommand(Map map, Coord src, Coord dst, Direction dir, Entity ent, int movementSpeed) {
        this.map = map;
        this.src = src;
        this.dst = dst;
        this.direction = dir;
        this.entity = ent;
        this.movementSpeed = movementSpeed;
        //this.execute();
        //replace this with a alertable command poosibly vary by speed
        int ticks = 30-movementSpeed;
        TimeModel.getInstance().registerAlertable(this, ticks);

    }

    public abstract void execute();
}
