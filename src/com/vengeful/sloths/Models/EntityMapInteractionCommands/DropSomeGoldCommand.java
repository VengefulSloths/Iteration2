package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.Gold;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by harrison on 3/12/16.
 */
public class DropSomeGoldCommand {
    private Entity entity;
    private Inventory inventory;
    private Coord dest;
    private int value;

    public DropSomeGoldCommand(Entity e, int value){
        this.entity = e;
        inventory = e.getInventory();
        dest = e.getLocation();
        this.value = value;
    }

    public void execute() {
        //inventory was null
        if(inventory.getGold() > 0){
            Gold gold = inventory.dropSomeGold(value);
            gold.setLocation(dest);
            Map.getInstance().getActiveMapArea().getTile(dest).addGold(gold);
        }
    }
}
