package com.vengeful.sloths.Models.InventoryItems.UsableItems;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Piggy;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.EntityMapInteractionFactory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.HexMath;
import com.vengeful.sloths.Utility.Location;

/**
 * Created by zach on 3/11/16.
 */
public class PiggyTotem extends UsableItems {

    Piggy pig;

    public PiggyTotem(String name, Piggy pig) {
        super(name);
        this.pig = pig;
    }

    @Override
    public void interact() {

        Coord closestMovableCoord = HexMath.getClosestMovableTile(new Location(Map.getInstance().getActiveMapArea(),Avatar.getInstance().getLocation()));

        this.pig.getControllerManager().setMapArea(Map.getInstance().getActiveMapArea());
        EntityMapInteractionFactory.getInstance().createRespawnCommand(this.pig, closestMovableCoord, 0);

        Avatar.getInstance().setPet(this.pig);
    }
}
