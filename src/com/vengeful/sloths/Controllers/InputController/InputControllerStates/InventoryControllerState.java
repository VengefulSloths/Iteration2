package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Views.InventoryView.InventoryView;
import com.vengeful.sloths.Views.InventoryView.ListInventoryView;
import com.vengeful.sloths.Views.ViewManager.ViewManager;
import sun.security.x509.AVA;

/**
 * Created by luluding on 3/3/16.
 */
public class InventoryControllerState extends InputControllerState{
    private InventoryView inventoryView;
    private int inventoryIndex;

    public InventoryView getInventoryView() {
        return inventoryView;
    }
    public void setInventoryView(InventoryView inventoryView) {
        this.inventoryView = inventoryView;
    }

    public InventoryControllerState() {
        this.inventoryIndex = 0;
    }


    @Override
    public void continuousFunction() {

    }

    @Override
    public void onDeregister() {

    }

    @Override
    public void onRegister() {

    }

    @Override
    public boolean handleIKey() {
        MainController.getInstance().setAvatarControllerState();
        //MainController.getInstance().setInventoryControllerState();
        return true;
    }

    @Override
    public boolean handleEKey() {
        return false;
    }

    @Override
    public boolean handleESCKey() {
        return false;
    }

    @Override
    public boolean handle1Key() {
        return false;
    }

    @Override
    public boolean handle2Key() { // select down in inventory
        int itemListSize = this.inventoryView.getItemListSize();
        int numCols = this.inventoryView.getNumCols();
        if(itemListSize == 0)
            return false;
        this.inventoryIndex+=numCols;
        if (this.inventoryIndex >= itemListSize) {
            this.inventoryIndex-=numCols;
            return false;
        }else{
            if(this.inventoryIndex > 0)
                this.inventoryView.setDeselected(this.inventoryView.getFromItemList(this.inventoryIndex-numCols)); //edit?
        }
        this.inventoryView.setSelected(this.inventoryView.getFromItemList(this.inventoryIndex));
        return true;
    }

    @Override
    public boolean handle3Key() {
        //Here due to InputStrategy

        //test code
        Inventory inventory = Avatar.getInstance().getInventory();
        System.out.println("inventory size: " + inventory.getCurrentSize());
        if(inventory.getCurrentSize() > 0){
            //MainController.getInstance().getPlayer().drop(inventory.getItem(0)); //edit: think this was working before
            MainController.getInstance().getPlayer().drop(inventory.getItem(inventoryIndex));
            return true;
        }else{
            System.out.println("Nothing in inventory");
            return false;
        }
    }

    @Override
        public boolean handle4Key() { //select left in inventory
        int itemListSize = this.inventoryView.getItemListSize();
        if(itemListSize == 0)
            return false;
        this.inventoryIndex--;
        if (this.inventoryIndex < 0) {
            this.inventoryIndex++;
            return false; //edit?
        } else {
            if(this.inventoryIndex < itemListSize)
                this.inventoryView.setDeselected(this.inventoryView.getFromItemList(this.inventoryIndex+1)); //edit?
        }
        this.inventoryView.setSelected(this.inventoryView.getFromItemList(this.inventoryIndex));
        return true;
    }

    @Override
    public boolean handle6Key() { //select right in inventory
        int itemListSize = this.inventoryView.getItemListSize();
        if(itemListSize == 0)
            return false;
        this.inventoryIndex++; //edit?
        if (this.inventoryIndex >= itemListSize) {
            this.inventoryIndex = itemListSize - 1;
            return false; //edit?
        } else {
            if(this.inventoryIndex < itemListSize)
                this.inventoryView.setDeselected(this.inventoryView.getFromItemList(this.inventoryIndex-1)); //edit?
        }
        this.inventoryView.setSelected(this.inventoryView.getFromItemList(this.inventoryIndex));
        return true;
    }

    @Override
    public boolean handle7Key() {
        return false;
    }

    @Override
    public boolean handle8Key() { //select up in inventory
        // Move up an item
        int itemListSize = this.inventoryView.getItemListSize();
        if(itemListSize == 0)
            return false;
        int numCols = this.inventoryView.getNumCols();
        this.inventoryIndex-=numCols; //edit?
        if (this.inventoryIndex < 0) {
            this.inventoryIndex+=numCols;
            return false; //edit?
        } else {
                this.inventoryView.setDeselected(this.inventoryView.getFromItemList(this.inventoryIndex+numCols)); //edit?
        }
        this.inventoryView.setSelected(this.inventoryView.getFromItemList(this.inventoryIndex));
        return true;
    }

    @Override
    public boolean handle9Key() {
        return false;
    }

    @Override
    public boolean handle5Key() {
        return false;
    }

    @Override
    public boolean handleDKey() {
        return false;
    }

    @Override
    public boolean handleLeftKey() {
        return false;
    }

    @Override
    public boolean handleRightKey() {
        return false;
    }

    @Override
    public boolean handleDownKey() {
        return false;
    }

    @Override
    public boolean handleUpKey() {
        return false;
    }

    @Override
    public boolean handleSpaceKey() {
        return false;
    }

    @Override
    public void handleRelease1Key() {

    }

    @Override
    public void handleRelease2Key() {

    }

    @Override
    public void handleRelease3Key() {

    }

    @Override
    public void handleRelease4Key() {

    }

    @Override
    public void handleRelease6Key() {

    }

    @Override
    public void handleRelease7Key() {

    }

    @Override
    public void handleRelease8Key() {

    }

    @Override
    public void handleRelease9Key() {

    }

    @Override
    public void handleRelease5Key() {

    }

    @Override
    public boolean handleReleaseLeftKey() {
        return false;
    }

    @Override
    public boolean handleReleaseRightKey() {
        return false;
    }

    @Override
    public boolean handleReleaseDownKey() {
        return false;
    }

    @Override
    public boolean handleReleaseUpKey() {
        return false;
    }
}
