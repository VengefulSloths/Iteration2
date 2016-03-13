package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Models.EntityEntityInteractionCommands.EntityPickPocketCommand;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.InventoryItems.InventoryItem;
import com.vengeful.sloths.Views.PickPocketView.PickPocketView;

/**
 * Created by harrison on 3/12/16.
 */
public class PickPocketControllerState extends InputControllerState{
    private Entity target = null;
    private Inventory targInv = null;
//    private int pickPocketSkill = 0;
    private PickPocketView pickPocketView = null;


    public void initPickPocketValues(PickPocketView pickPocketView, Inventory targInv, Entity target) {
        this.pickPocketView = pickPocketView;
        this.targInv = targInv;
        this.target = target;
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
        target.setStunned(false);
        MainController.getInstance().setAvatarControllerState();
        //MainController.getInstance().setInventoryControllerState();
        return true;
    }


    @Override
    public boolean handleESCKey() {
        target.setStunned(false);
        MainController.getInstance().setAvatarControllerState();
        return false;
    }

    @Override
    public boolean handleSouthWestKey() {
        return false;
    }


    public boolean handleSouthKey() {
        this.pickPocketView.selectSouthItem();
        return true;
    }

    public void resetInventoryView() {
        this.pickPocketView.resetInventoryView();
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
        this.pickPocketView.selectWestItem();
        return true;
    }

    @Override
    public boolean handleEastKey() {
        this.pickPocketView.selectEastItem();
        return true;
    }

    @Override
    public boolean handleNorthWestKey() {
        return false;
    }


    public boolean handleNorthKey() {
        // Move up an item
        this.pickPocketView.selectNorthItem();
        return true;
    }

    @Override
    public boolean handleNorthEastKey() {
//        this.pickPocketView.useCurrentlySelectedItem();
        InventoryItem item = this.pickPocketView.getCurrentItem();
        new EntityPickPocketCommand(target,targInv,item).execute();
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
        this.pickPocketView.selectWestItem();
        return false;
    }

    @Override
    public boolean handleRightKey() {

        this.pickPocketView.selectEastItem();
        return false;
    }

    @Override
    public boolean handleDownKey() {

        this.pickPocketView.selectSouthItem();
        return false;
    }

    @Override
    public boolean handleUpKey() {
        this.pickPocketView.selectNorthItem();
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
