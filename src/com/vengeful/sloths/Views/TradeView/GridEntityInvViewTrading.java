package com.vengeful.sloths.Views.TradeView;

import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.ObserverManager;
import com.vengeful.sloths.Models.Observers.InventoryObserver;
import com.vengeful.sloths.Models.Observers.ProxyInventoryObserver;
import com.vengeful.sloths.Models.Observers.ProxyObserver;
import com.vengeful.sloths.Utility.CalculateBuySellPickPocket;
import com.vengeful.sloths.Views.InventoryView.GridInventoryView;
import com.vengeful.sloths.Views.InventoryView.ItemViewObject;

import java.util.ArrayList;

/**
 * Created by harrison on 3/13/16.
 */
public class GridEntityInvViewTrading extends GridInventoryView implements InventoryObserver{
    private int bargainSkill;
    private String entityName;

    public GridEntityInvViewTrading(Inventory inv, int bargainSkill){
        super(inv);
        this.bargainSkill = bargainSkill;
    }
    public GridEntityInvViewTrading(Inventory inv, int bargainSkill, String entityName){
        //this.itemList = new ArrayList<AbilityViewObject>();
        this.setItemList(new ArrayList<ItemViewObject>());
        this.setInventory(inv);
        //Create a proxy for the observer, regester the proxy w/ entity, add proxy to manager
        ProxyObserver pio = new ProxyInventoryObserver(this, inv);
        ObserverManager.getInstance().addProxyObserver(pio);

        initWithInventory(this.getInventory());
        this.entityName = entityName;
        initDefaultUI();
        this.setNumRows(5); //default numRows
        this.setNumCols(4); //default numCols
    }


    @Override
    public void initWithInventory(Inventory inventory){
        for (int i = 0; i < inventory.getCurrentSize(); ++i) {
            //InventoryItem item = inventory.getItem(i);
            //this.itemList.add(new AbilityViewObject(inventory.getItem(i)));
            //this.itemList.add(new AbilityViewObject(inventory.getItem(i)));
            System.out.println("THIS IS THE ITEM " + inventory.getItem(i).getItemName() );
            int value = inventory.getItem(i).getValue();
            int valuePostBargain = CalculateBuySellPickPocket.CalculateBuyPrice(value, bargainSkill);
            this.getItemList().add(new TradeItem(inventory.getItem(i), valuePostBargain));
            //this.getFromItemList(0).setSelected(true);
            //if(i==0) {
            //  this.getFromItemList(i).setSelected(true);
            //}
            //this.getItemList().add(new AbilityViewObject(inventory.getItem(i), this.getWidth(),this.getHeight()));
            //ivoFactory.generateItemViewObject(inventory.getItem(i), this.getWidth(), this.getHeight(), )
        }
    }

    @Override
    protected String getInvLabel(){
        return entityName;
    }
}
