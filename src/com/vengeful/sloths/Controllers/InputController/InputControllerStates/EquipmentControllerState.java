package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Views.EquipmentView.EquipmentView;

/**
 * Created by echristiansen on 3/12/2016.
 */
public class EquipmentControllerState extends InputControllerState{
    private EquipmentView equipmentView;

    public EquipmentView getEquipmentView() {
        return equipmentView;
    }
    public void setEquipmentView(EquipmentView equipmentView) {
        this.equipmentView = equipmentView;
    }

    public EquipmentControllerState() {

    }

    public void setSelected(boolean isActive) {
        this.equipmentView.setSelected(isActive);
    }

    public void setEquipmentViewSelected() {
        this.getEquipmentView().setSelected(true);
    }


    public void resetView(boolean isActiveView) {

//        this.equipmentView.resetView(isActiveView);
        this.setSelected(isActiveView);
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
    public boolean handleEquipmentKey() {
        return false;
    }

    @Override
    public boolean handleESCKey() {
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
        this.equipmentView.selectSouthItem();
        System.out.println("TRYING EQUIPMENT VIEW DOWN KEY");
        return true;
    }

    @Override
    public boolean handleUpKey() {
        this.equipmentView.selectNorthItem();
        System.out.println("TRYING EQUIPMENT VIEW UP KEY");
        return true;
    }

    @Override
    public void handleSaveKey() {

    }

    @Override
    public void handleEnterKey() {
        this.equipmentView.unequipItem();
        System.out.println("REGISTERING THAT ENTER IS PRESSED FROM EQUIPMENTCONTROLLERSTATE, SHOULD BE UNEQUIPPING");

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
    public boolean handleSpaceKey() {
        return false;
    }

    @Override
    public void handleTalkKey() {

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
