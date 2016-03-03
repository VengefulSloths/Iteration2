package com.vengeful.sloths.Models.Map.MapItems.InteractiveItem;

import com.vengeful.sloths.Models.Effects.EffectCommand;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by qianwen on 2/6/16.
 */
public class InventoryInteractiveItem extends InteractiveItem{

    private String requiredItemName;

    public InventoryInteractiveItem(EffectCommand command, String requiredItemName, Coord location){
        super(command, location);
        this.requiredItemName = requiredItemName;
    }

    @Override
    public void interact(Entity entity) {
        //System.out.Println("INTERACTIVEITEM OBSERVER" + this.observer);

        try{
            //Todo: i deleted some of this for compile
//        if(entity.getInventory().hasItem(requiredItemName) || entity.getEquipped().getSword().getItemName().equals(requiredItemName) || entity.getEquipped().getHat().getItemName().equals(requiredItemName)) {
//            command.execute();
//        }
        }catch (NullPointerException e){
            //dont have any objects, sont execute command
        }
    }

    // @TODO: Make visitor method for this
    @Override
    public void accept(ModelVisitor vistior) {
        vistior.visitMapItem(this);
    }

    public void setRequiredItem(String requiredItem) {
        this.requiredItemName = requiredItem;
    }
}
