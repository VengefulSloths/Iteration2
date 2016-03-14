package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.EntityEntityInteractionCommands.AvatarBuyCommand;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Views.InventoryView.InventoryView;
import com.vengeful.sloths.Views.TradeView.GridEntityInvViewTrading;

/**
 * Created by icavitt on 3/13/2016.
 */
public class TradeBuyState  extends InventoryControllerState{

    private GridEntityInvViewTrading inventoryView;
    public GridEntityInvViewTrading getInventoryView() {
        return inventoryView;
    }

    public void setInventoryView(GridEntityInvViewTrading inventoryView) {
        this.inventoryView = inventoryView;
    }

    public void setSelected(boolean isActive) {
        this.inventoryView.setSelected(isActive);
    }

    public TradeBuyState() {

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
    public boolean handleAbilitiesKey() {
        return false;
    }

    @Override
    public boolean handleInventoryKey() {
        MainController.getInstance().setAvatarControllerState();
        //MainController.getInstance().setInventoryControllerState();
        return true;
    }


    @Override
    public boolean handleESCKey() {
        return false;
    }

    @Override
    public boolean handleSouthWestKey() {
        return false;
    }


    public boolean handleSouthKey() {
        this.inventoryView.selectSouthItem();
        return true;
    }

    public void resetInventoryView() {
        this.inventoryView.resetInventoryView();
    }

    @Override
    public boolean handleSouthEastKey() {
        //Here due to InputStrategy

        //test code
        Inventory inventory = Avatar.getInstance().getInventory();
        if(inventory.getCurrentSize() > 0){
            //MainController.getInstance().getPlayer().drop(inventory.getItem(0)); //edit: think this was working before

            System.out.println("About to dorp item: " + this.inventoryView.getCurrentItem().getItemName());
            MainController.getInstance().getPlayer().drop(this.inventoryView.getCurrentItem());
            this.inventoryView.dropViewItem();
            return true;
        }else{
            System.out.println("Nothing in inventory");
            return false;
        }
    }


    @Override
    public void handleAbility1Key() {

    }

    @Override
    public void handleAbility2Key() {

    }

    @Override
    public void handleAbility3Key() {

    }

    @Override
    public void handleAbility4Key() {

    }

    public boolean handleWestKey() {
        this.inventoryView.selectWestItem();
        return true;
    }

    @Override
    public boolean handleEastKey() {
        this.inventoryView.selectEastItem();
        return true;
    }

    @Override
    public boolean handleNorthWestKey() {
        return false;
    }


    public boolean handleNorthKey() {
        // Move up an item
        this.inventoryView.selectNorthItem();
        return true;
    }

    @Override
    public boolean handleNorthEastKey() {
//        this.inventoryView.useCurrentlySelectedItem();
        if(this.inventoryView.getCurrentItem() != null) {
            InventoryItem item = this.inventoryView.getCurrentItem();
            new AvatarBuyCommand(this.inventoryView.getInventory(), item).execute();
            this.inventoryView.dropViewItem();
        }
        return true;

    }

    @Override
    public void resetView(boolean isActive) {
        this.inventoryView.setSelected(isActive);
    }

    @Override
    public boolean handleCenterKey() {
        return false;
    }

    @Override
    public boolean handleDropKey() {
        return false;
    }

    @Override
    public boolean handleLeftKey() {
        this.inventoryView.selectWestItem();
        return false;
    }

    @Override
    public boolean handleRightKey() {
        this.inventoryView.selectEastItem();
        return false;
    }

    @Override
    public boolean handleDownKey() {
        this.inventoryView.selectSouthItem();
        return true;
    }

    @Override
    public boolean handleUpKey() {
        this.inventoryView.selectNorthItem();
        return true;
    }

    @Override
    public boolean handleSpaceKey() {
        return false;
    }

    @Override
    public void handleReleaseSouthWestKey() {

    }

    @Override
    public void handleReleaseSouthKey() {

    }

    @Override
    public void handleReleaseSouthEastKey() {

    }

    @Override
    public void handleReleaseWestKey() {

    }

    @Override
    public void handleReleaseEastKey() {

    }

    @Override
    public void handleReleaseNorthWestKey() {

    }

    @Override
    public void handleReleaseNorthKey() {

    }

    @Override
    public void handleReleaseNorthEastKey() {

    }

    @Override
    public void handleReleaseCenterKey() {

    }

    public void handleSaveKey(){

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

    @Override
    public boolean handleEquipmentKey() {
        return false;
    }

    @Override
    public void handleEnterKey() {
        this.inventoryView.useCurrentlySelectedItem();
    }

    @Override
    public void handleTalkKey() {

    }
}
