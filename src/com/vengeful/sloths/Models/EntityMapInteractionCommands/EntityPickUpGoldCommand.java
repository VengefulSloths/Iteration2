package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapItems.Gold;

import javax.swing.plaf.synth.SynthTextAreaUI;

/**
 * Created by harrison on 3/11/16.
 */
public class EntityPickUpGoldCommand {
    Entity entity;
    Inventory inventory;
    Gold gold;

    public EntityPickUpGoldCommand(Entity entity, Inventory inventory, Gold gold){
        this.entity = entity;
        this.inventory = inventory;
        this.gold = gold;
    }

    public void execute(){
        if(this.inventory.addGold(gold)){
            Map.getInstance().getActiveMapArea().getTile(this.gold.getLocation()).removeGold(this.gold);
            this.gold.alertObserverOnDestroy();
        }
        System.out.println("!!!!!!!Inventory Gold: " + inventory.getGold() + "!!!!!!!!");
    }
}
