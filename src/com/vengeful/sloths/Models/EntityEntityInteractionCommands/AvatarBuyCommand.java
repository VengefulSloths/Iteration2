package com.vengeful.sloths.Models.EntityEntityInteractionCommands;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.MapItems.Gold;
import com.vengeful.sloths.Sound.SoundEffect;

/**
 * Created by harrison on 3/13/16.
 */
public class AvatarBuyCommand {

    private Inventory tradersInventory;
    private InventoryItem item;

    public AvatarBuyCommand(Inventory tradersInventory, InventoryItem item){
        this.tradersInventory = tradersInventory;
        this.item = item;
    }

    public void execute(){
        int itemValue = item.getValue();
        Inventory avatarInventory = Avatar.getInstance().getInventory();
        if(itemValue > avatarInventory.getGold()){
            SoundEffect fail = new SoundEffect("resources/audio/buzzerFail.wav");
            fail.play();
        }
        else{
            SoundEffect success = new SoundEffect("resources/audio/tradeSuccesfulchCaChing.wav");
            success.play();
            Gold g = new Gold();
            g.setValue(itemValue);
            avatarInventory.setGold(avatarInventory.getGold() - itemValue);
            tradersInventory.addGold(g);
            tradersInventory.removeItem(item);
            avatarInventory.addItem(item);
        }
    }
}
