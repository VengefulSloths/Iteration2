package com.vengeful.sloths.Controllers.MovementController;

import com.vengeful.sloths.Utility.Coord;

/**
 * Created by zach on 2/22/16.
 *
 * desc: Will be subclassed to handle controlling movement over various terrains
 */
public abstract class MovementController {

    public abstract void move(Coord src, Coord dest);
}
