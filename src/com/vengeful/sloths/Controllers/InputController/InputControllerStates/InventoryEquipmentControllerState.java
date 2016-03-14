package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Views.AbilitiesSkillsView.AbilitiesSkillView;
import com.vengeful.sloths.Views.CharacterView.CharacterView;

import java.util.ArrayList;

/**
 * Created by echristiansen on 3/12/2016.
 */
public class InventoryEquipmentControllerState extends InputControllerState {
    private int currStateIndex = 0;
    private ArrayList<InputControllerState> possibleStates;

    private CharacterView characterView;

    private InputControllerState state;

    private InventoryControllerState inventoryControllerState;
    private EquipmentControllerState equipmentControllerState;

    public InventoryEquipmentControllerState() {
        possibleStates = new ArrayList<>();

        this.inventoryControllerState = new InventoryControllerState();
        this.equipmentControllerState = new EquipmentControllerState();
        possibleStates.add(this.inventoryControllerState);
        possibleStates.add(this.equipmentControllerState);

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
        state.handleEnterKey();
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
    public void handleObservationKey() {

    }

    @Override
    public boolean handleSpaceKey() {
        currStateIndex = (currStateIndex + 1) % 2;
        this.state = this.possibleStates.get(currStateIndex);
        this.state.resetView(true);
        this.possibleStates.get((currStateIndex+1)%2).resetView(false);

        return true;
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

    @Override
    public void resetView(boolean isActive) {

    }
}
