package com.vengeful.sloths.Models.EntityEntityInteractionCommands;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.Map.MapItems.Gold;
import com.vengeful.sloths.Sound.SoundEffect;
import com.vengeful.sloths.Utility.CalculateBuySellPickPocket;

/**
 * Created by harrison on 3/13/16.
 */
public class AvatarSellCommand {

    private Inventory tradersInventory;
    private InventoryItem item;

    public AvatarSellCommand(Inventory tradersInventory, InventoryItem item){
        this.tradersInventory = tradersInventory;
        this.item = item;
    }

    public void execute(){
        int itemValue = item.getValue();
        itemValue = CalculateBuySellPickPocket.CalculateSellPrice(itemValue, Avatar.getInstance().getSkillManager().getBargainLevel());
        Inventory avatarInventory = Avatar.getInstance().getInventory();
        if(itemValue > tradersInventory.getGold()){
            SoundEffect fail = new SoundEffect("resources/audio/buzzerFail.wav");
            fail.play();
        }
        else{
            SoundEffect success = new SoundEffect("resources/audio/tradeSuccesfulchCaChing.wav");
            success.play();
            Gold g = new Gold();
            g.setValue(itemValue);
            tradersInventory.setGold(tradersInventory.getGold() - itemValue);
            avatarInventory.addGold(g);
            avatarInventory.removeItem(item);
            tradersInventory.addItem(item);
        }
    }

}
