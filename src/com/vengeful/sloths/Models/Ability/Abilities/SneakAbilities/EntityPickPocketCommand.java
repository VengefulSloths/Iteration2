package com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by harrison on 3/12/16.
 */
public class EntityPickPocketCommand {
    private int chanceToSucceed;
    private Entity target = null;
    private Inventory targInv = null;
    private InventoryItem item = null;

    public EntityPickPocketCommand(int chanceToSucceed, Entity target, Inventory targInv, InventoryItem item){
        this.chanceToSucceed = chanceToSucceed;
        this.target = target;
        this.targInv = targInv;
        this.item = item;
    }

    public EntityPickPocketCommand(Entity e, Inventory inv, InventoryItem item){
        this(100,e,inv,item);
    }

    public void execute(){
        int r = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        if(r <= chanceToSucceed){
            System.out.println("pick pocket will succeed");
            if(targInv.removeItem(item)){
                Avatar.getInstance().getInventory().addItem(item);
                System.out.println("pickpocket SUCEEDED");
            }
        }
        System.out.println("pick pocket FAILED");

    }
}
