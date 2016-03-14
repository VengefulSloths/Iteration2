package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Menu.MainMenu.MainScrollableMenu;
import com.vengeful.sloths.Menu.ScrollableMenu;

/**
 * Created by harrison on 3/13/16.
 */
public class MainMenuControllerState extends InputControllerState {
    ScrollableMenu menu;

    public void setScrollableMenu(ScrollableMenu menu) {
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
    public boolean handleAbilitiesKey() {
        return false;
    }

    @Override
    public void resetView(boolean isActive) {

    }

    @Override
    public void handleTalkKey() {

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

    @Override
    public boolean handleInventoryKey() {
        return false;
    }


    @Override
    public boolean handleESCKey() {
        ((MainScrollableMenu)menu).setMenuList();
        return false;
    }

    @Override
    public boolean handleSouthWestKey() {
        return false;
    }

    @Override
    public boolean handleSouthKey() {

        menu.down();

        return true;
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

        menu.up();

        return true;
    }

    @Override
    public boolean handleNorthEastKey() {
        return false;
    }

    @Override
    public void handleEnterKey() {
        menu.select(null);
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
    public boolean handleSpaceKey() {
        return false;
    }

    public void handleSaveKey(){

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

    @Override
    public boolean handleEquipmentKey() {
        return false;
    }



}
