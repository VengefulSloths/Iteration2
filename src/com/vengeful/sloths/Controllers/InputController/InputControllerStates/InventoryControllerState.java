package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Models.Inventory.Inventory;

/**
 * Created by luluding on 3/3/16.
 */
public class InventoryControllerState extends InputControllerState{

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
    public boolean handle2Key() {
        return false;
    }

    @Override
    public boolean handle3Key() {
        //Here due to InputStrategy

        //test code
        Inventory inventory = MainController.getInstance().getInventory();

        if(inventory.getCurrentSize() > 0){
            MainController.getInstance().getPlayer().drop(inventory.getItem(0));
            return true;
        }else{
            System.out.println("Nothing in inventory");
            return false;
        }
    }

    @Override
    public boolean handle4Key() {
        return false;
    }

    @Override
    public boolean handle6Key() {
        return false;
    }

    @Override
    public boolean handle7Key() {
        return false;
    }

    @Override
    public boolean handle8Key() {
        return false;
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
