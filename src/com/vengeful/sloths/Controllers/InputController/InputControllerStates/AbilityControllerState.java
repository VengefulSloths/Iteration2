package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Views.AbilitiesView.AbilitiesView;


/**
 * Created by zach on 3/11/16.
 */
public class AbilityControllerState extends InputControllerState{
    private AbilitiesView abilitiesView;

    public AbilitiesView getAbilitiesView() {
        return abilitiesView;
    }
    public void setAbilitiesView(AbilitiesView abilitiesView) {
        this.abilitiesView = abilitiesView;
    }

    @Override
    public boolean handleAbilitiesKey() {
        MainController.getInstance().setAvatarControllerState();
        return true;
    }

    public AbilityControllerState() {

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
    public void handleTalkKey() {

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
        this.abilitiesView.selectSouthItem();
        return true;
    }

    public void resetView(boolean isActiveView) {
        this.abilitiesView.resetView(isActiveView);
    }

    @Override
    public boolean handleSouthEastKey() {
        return false;
    }

    @Override
    public void handleAbility1Key() {
        this.abilitiesView.equipAbility(0);
    }

    @Override
    public void handleAbility2Key() {
        this.abilitiesView.equipAbility(1);
    }

    @Override
    public void handleAbility3Key() {
        this.abilitiesView.equipAbility(2);
    }

    @Override
    public void handleAbility4Key() {
        this.abilitiesView.equipAbility(3);

    }

    @Override
    public void handleObservationKey() {

    }

    public boolean handleWestKey() {
        this.abilitiesView.selectWestItem();
        return true;
    }

    @Override
    public boolean handleEastKey() {
        this.abilitiesView.selectEastItem();
        return true;
    }

    // Equip ability
    @Override
    public boolean handleNorthWestKey() {
        return false;
    }


    public boolean handleNorthKey() {
        // Move up an item
        this.abilitiesView.selectNorthItem();
        return true;
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
        this.abilitiesView.selectWestItem();
        return false;
    }

    @Override
    public boolean handleRightKey() {

        this.abilitiesView.selectEastItem();
        return false;
    }

    @Override
    public boolean handleDownKey() {

        this.abilitiesView.selectSouthItem();
        return false;
    }

    @Override
    public boolean handleUpKey() {
        this.abilitiesView.selectNorthItem();
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
