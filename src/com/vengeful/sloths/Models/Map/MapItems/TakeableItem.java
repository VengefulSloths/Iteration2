package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Models.ModelVisitor;


/**
 * Created by John on 1/30/2016.
 */
public class TakeableItem extends MapItem {

    private InventoryItem invItemRep;

    public TakeableItem() {}

    public TakeableItem(InventoryItem item){ //can be equippable item, consumable item ... etc
//        this.itemName = item.getItemName() + " Takeable";
//        this.invItemRep = item;
//
//        //Pairs Take-able with Inventory Item
//        item.setTakeableItem(this);
    }

    //Does nothing
    public void interact(Entity entity){
        //maybe alert user he cannot move here
        if(entity instanceof Avatar){
            //System.out.Println("Calling pick up!!!!");
            this.destroy = false;
            ((Avatar)entity).pickup();
        }
    }

    public InventoryItem getInvItemRep(){
        return this.invItemRep;
    }

    public void setInvItemRep(InventoryItem inv) {
        this.invItemRep = inv;
    }


    public void accept(ModelVisitor visitor) {
        visitor.visitTakeableItem(this);
    }

    //For testing purpose
    /*
    public String toString(){
        String ret = "My takeable is: " + this.itemName + "\n";
        if (this.invItemRep != null)
            ret +="My corresponding inv item is: " + this.invItemRep.getItemName();
        return ret;

    }*/

//    public void saveMe(SaveManager sm, int ws) {
//        sm.writeClassLine(ws, "TakeableItem");
//        invItemRep.saveMeFromTakeable(sm, ws+1);
//        super.saveMe(sm, ws);
//    }
//    public void saveMeFromInv(SaveManager sm, int ws)
//    {
//        sm.writeClassLine(ws, "TakeableItem");
//        super.saveMe(sm, ws);
//        sm.writeCloseBracket(ws);
//    }
}
