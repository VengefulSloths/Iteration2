package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.Gold;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by harrison on 3/12/16.
 */
public class DropAllGoldCommand {
    Entity entity;
    Inventory inventory;
    Coord dest;

    public DropAllGoldCommand(Entity entity, Coord dest){
        this.entity = entity;
        this.inventory = entity.getInventory();
        this.dest = dest;
    }

    public void execute() {
        //inventory was null
        if(inventory.getGold() > 0){
            Gold gold = entity.dropGold();
            gold.setLocation(dest);
            Map.getInstance().getActiveMapArea().getTile(dest).addGold(gold);
        }
    }
}
