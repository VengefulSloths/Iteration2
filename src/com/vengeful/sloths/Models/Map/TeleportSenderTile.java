package com.vengeful.sloths.Models.Map;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Models.Map.TeleportDestinationTile;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

/**
 * Created by alexs on 3/2/2016.
 * This guy is a mediator
 */
public class TeleportSenderTile extends Tile {

    private MapArea destinationMapArea;
    private TeleportDestinationTile destinationTile;

    public TeleportSenderTile(MapArea destinationMapArea, TeleportDestinationTile destinationTile) {
        this.destinationMapArea = destinationMapArea;
        this.destinationTile = destinationTile;
    }

    @Override
    public void interact(Entity entity) {

        System.out.println("Teleporitng");
        this.removeEntity(entity);
        System.out.println("Teleporitng1");

        destinationTile.addEntity(entity);
        System.out.println("Teleporitng2");

        entity.setLocation(destinationTile.getLocation());
        System.out.println("Teleporitng3");

        Map.getInstance().setActiveMapArea(destinationMapArea);
        System.out.println("Teleporitng4");

    }



    @Override
    public void accept(ModelVisitor v) {
        v.visitTeleportSenderTile(this);
    }
}
