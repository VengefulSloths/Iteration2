package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Views.AbilitiesSkillsView.AbilitiesSkillView;
import com.vengeful.sloths.Views.CharacterView.CharacterView;

/**
 * Created by echristiansen on 3/12/2016.
 */
public class InventoryEquipmentControllerState extends InputControllerState {

    private CharacterView characterView;

    private InputControllerState state;

    private InventoryControllerState inventoryControllerState;
    private EquipmentControllerState equipmentControllerState;

    public InventoryEquipmentControllerState() {
        this.inventoryControllerState = new InventoryControllerState();
        this.equipmentControllerState = new EquipmentControllerState();
        this.state = this.inventoryControllerState;
    }

    public void setCharacterView(CharacterView characterView) {
        this.characterView = characterView;
        this.inventoryControllerState.setInventoryView(this.characterView.getInventoryView());
        this.equipmentControllerState.setEquipmentView(this.characterView.getEquipmentView());
    }

    public void resetViews() {
        this.state = inventoryControllerState;
        this.inventoryControllerState.resetInventoryView();
        this.inventoryControllerState.getInventoryView().setSelected(true);
       //this.equipmentControllerState.resetView(false);
        this.equipmentControllerState.getEquipmentView().setSelected(false);
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
        MainController.getInstance().setAvatarControllerState();
        return true;
        //return false;
    }

    @Override
    public boolean handleInventoryKey() {
        state.handleInventoryKey();
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
        state.handleSouthKey();
        return true;
    }

    @Override
    public boolean handleSouthEastKey() {
        state.handleSouthEastKey();
        return true;
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
        state.handleLeftKey();
        return true;
    }

    @Override
    public boolean handleRightKey() {
        state.handleRightKey();
        return true;
    }

    @Override
    public boolean handleDownKey() {
        state.handleDownKey();
        return true;
    }

    @Override
    public boolean handleUpKey() {
        state.handleUpKey();
        return true;
    }

    @Override
    public void handleSaveKey() {

    }

    @Override
    public void handleEnterKey() {

    }

    @Override
    public void handleAbility0Key() {

    }

    @Override
    public void handleAbility1Key() {
        this.state = this.inventoryControllerState;
        this.inventoryControllerState.getInventoryView().setSelected(true);
        this.inventoryControllerState.getInventoryView().resetInventoryView();
        this.equipmentControllerState.getEquipmentView().setSelected(false);
        System.out.println("Trying to handleability 1 key");
    }

    @Override
    public void handleAbility2Key() {

    }

    @Override
    public void handleAbility3Key() {
        this.state = this.equipmentControllerState;
        this.inventoryControllerState.getInventoryView().setSelected(false);
        this.equipmentControllerState.getEquipmentView().setSelected(true);
        System.out.println("Trying to handleability 3 key");
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
