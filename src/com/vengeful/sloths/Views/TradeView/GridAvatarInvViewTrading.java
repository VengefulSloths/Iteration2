package com.vengeful.sloths.Views.TradeView;

import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Views.InventoryView.GridInventoryView;

/**
 * Created by harrison on 3/13/16.
 */
public class GridAvatarInvViewTrading extends GridInventoryView{

    private int bargainSkill;
    public GridAvatarInvViewTrading(Inventory inv, int bargainSkill){
        super(inv);
        this.bargainSkill = bargainSkill;
    }

    @Override
    public void initWithInventory(Inventory inventory){
        for (int i = 0; i < inventory.getCurrentSize(); ++i) {
            //InventoryItem item = inventory.getItem(i);
            //this.itemList.add(new AbilityViewObject(inventory.getItem(i)));
            //this.itemList.add(new AbilityViewObject(inventory.getItem(i)));
            System.out.println("THIS IS THE ITEM " + inventory.getItem(i).getItemName() );
            int value = inventory.getItem(i).getValue();
            int valuePostBargain = value + bargainSkill;
            this.getItemList().add(new TradeItem(inventory.getItem(i), valuePostBargain));
            //this.getFromItemList(0).setSelected(true);
            //if(i==0) {
            //  this.getFromItemList(i).setSelected(true);
            //}
            //this.getItemList().add(new AbilityViewObject(inventory.getItem(i), this.getWidth(),this.getHeight()));
            //ivoFactory.generateItemViewObject(inventory.getItem(i), this.getWidth(), this.getHeight(), )
        }
    }
}
