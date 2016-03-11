package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.KeyBindingMenu.InputChangeMenu;

/**
 * Created by John on 3/10/2016.
 */
public class SetInputsControllerState extends InputControllerState {
    InputChangeMenu menu;

    public InputChangeMenu getMenu() {
        return menu;
    }

    public void setMenu(InputChangeMenu menu) {
        this.menu = menu;
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
    public boolean handleInventoryKey() {

        return false;
    }

    @Override
    public boolean handleEquipmentKey() {
        return false;
    }

    @Override
    public boolean handleESCKey() {

        //go back to area view or menu view or whatever
        return false;
    }

    @Override
    public boolean handleSouthWestKey() {
        return false;
    }

    @Override
    public boolean handleSouthKey() {
        return false;
    }

    @Override
    public boolean handleSouthEastKey() {
        return false;
    }

    @Override
    public boolean handleWestKey() {
        return false;
    }

    @Override
    public boolean handleEastKey() {
        return false;
    }

    @Override
    public boolean handleNorthWestKey() {
        return false;
    }

    @Override
    public boolean handleNorthKey() {
        return false;
    }

    @Override
    public boolean handleNorthEastKey() {
        return false;
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
        return false;
    }

    @Override
    public boolean handleRightKey() {
        return false;
    }

    @Override
    public boolean handleDownKey() {
        menu.down();
        return false;
    }

    @Override
    public boolean handleUpKey() {
        menu.up();
        return false;
    }

    @Override
    public void handleSaveKey() {

    }

    @Override
    public void handleEnterKey() {

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
