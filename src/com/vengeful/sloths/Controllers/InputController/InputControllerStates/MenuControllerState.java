package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Menu.ScrollableMenu;

/**
 * Created by John on 3/7/2016.
 */
public class MenuControllerState extends InputControllerState {
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
    public boolean handleIKey() {
        return false;
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

        menu.down();

        return true;
    }

    @Override
    public boolean handle3Key() {
        return false;
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

        menu.up();

        return true;
    }

    @Override
    public boolean handle9Key() {
        return false;
    }

    @Override
    public boolean handle5Key() {
        menu.select(null);
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

    public void handlePKey(){

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
