package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.EntityEntityInteractionCommands.EntityPickPocketCommand;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Views.TradeView.TradeView;

/**
 * Created by harrison on 3/13/16.
 */
public class TradeControllerState extends InputControllerState{
    private Inventory targInv = null;
    //    private int pickPocketSkill = 0;
    private TradeView tradeView = null;


    public void TradeView(TradeView tradeView, Inventory targInv) {
        this.tradeView = tradeView;
        this.targInv = targInv;
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
    public void handleAbility4Key() {

    }

    @Override
    public void handleTalkKey() {

    }

    @Override
    public void resetView(boolean isActive) {

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
        MainController.getInstance().setAvatarControllerState();
        return false;
    }

    @Override
    public boolean handleSouthWestKey() {
        return false;
    }


    public boolean handleSouthKey() {
//        this.tradeView.selectSouthItem();
        return true;
    }

    public void resetInventoryView() {
//        this.tradeView.resetInventoryView();
    }

    @Override
    public boolean handleSouthEastKey() {
        //test code for pickpocket
//        if(targInv.getCurrentSize() > 0){
////            MainController.getInstance().getPlayer().drop(inventory.getItem(0)); //edit: think this was working before
////            InventoryItem ii = targInv.removeItem(this.pickPocketView.getCurrentItem());
//            InventoryItem ii = targInv.getItem(0);
//            new EntityPickPocketCommand(target,targInv,ii);
//            return true;
//        }else{
//            System.out.println("Nothing in inventory");
//            return false;
//        }
        return false;
    }

    public void handleAbility0Key() {

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


    public boolean handleWestKey() {
//        this.tradeView.selectWestItem();
        return true;
    }

    @Override
    public boolean handleEastKey() {
//        this.pickPocketView.selectEastItem();
        return true;
    }

    @Override
    public boolean handleNorthWestKey() {
        return false;
    }


    public boolean handleNorthKey() {
        // Move up an item
//        this.tradeView.selectNorthItem();
        return true;
    }

    @Override
    public boolean handleNorthEastKey() {
//        this.pickPocketView.useCurrentlySelectedItem();
//        InventoryItem item = this.tradeView.getCurrentItem();
        //ENTITY TRADE COMMAND
        return true;

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
//        this.tradeView.selectWestItem();
        return false;
    }

    @Override
    public boolean handleRightKey() {

//        this.tradeView.selectEastItem();
        return false;
    }

    @Override
    public boolean handleDownKey() {

//        this.tradeView.selectSouthItem();
        return false;
    }

    @Override
    public boolean handleUpKey() {
//        this.tradeView.selectNorthItem();
        return false;
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

    }

}
