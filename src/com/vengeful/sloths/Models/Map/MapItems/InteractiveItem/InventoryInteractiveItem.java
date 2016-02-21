package com.vengeful.sloths.Models.Map.MapItems.InteractiveItem;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Entity.Entity;

/**
 * Created by qianwen on 2/6/16.
 */
public class InventoryInteractiveItem extends InteractiveItem{

    private String requiredItemName;

    public InventoryInteractiveItem(EffectCommand command, String requiredItemName){
        super(command);
        this.requiredItemName = requiredItemName;
    }

    public InventoryInteractiveItem(){}

    @Override
    public void interact(Entity entity) {
        //System.out.Println("INTERACTIVEITEM OBSERVER" + this.observer);

        this.observer.alertActivated();
        try{
            //Todo: i deleted some of this for compile
//        if(entity.getInventory().hasItem(requiredItemName) || entity.getEquipped().getSword().getItemName().equals(requiredItemName) || entity.getEquipped().getHat().getItemName().equals(requiredItemName)) {
//            command.execute();
//        }
        }catch (NullPointerException e){
            //dont have any objects, sont execute command
        }
    }

    public void setRequiredItem(String requiredItem) {
        this.requiredItemName = requiredItem;
    }
}
