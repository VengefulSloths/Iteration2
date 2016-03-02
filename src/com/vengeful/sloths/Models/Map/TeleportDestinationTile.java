package com.vengeful.sloths.Models.Map;

import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by alexs on 3/2/2016.
 */
public class TeleportDestinationTile extends Tile {

    private Coord location;

    public TeleportDestinationTile(Coord location) {
        super();
        this.location = location;
    }

    public Coord getLocation() {
        return location;
    }

    @Override
    public void accept(ModelVisitor m) {
        m.visitTeleportDestinationTile(this);
    }
}
