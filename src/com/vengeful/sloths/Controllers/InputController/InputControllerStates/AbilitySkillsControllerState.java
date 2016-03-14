package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Views.AbilitiesSkillsView.AbilitiesSkillView;

import java.util.ArrayList;

/**
 * Created by zach on 3/12/16.
 */
public class AbilitySkillsControllerState extends InputControllerState {
    private AbilitiesSkillView abilitiesSkillView;

    private int currStateIndex = 0;
    private InputControllerState state;
    private ArrayList<InputControllerState> possibleStates;

    private SkillsControllerState skillsControllerState;
    private AbilityControllerState abilityControllerState;

    public AbilitySkillsControllerState() {
        possibleStates = new ArrayList<>();
        this.abilityControllerState = new AbilityControllerState();
        this.skillsControllerState = new SkillsControllerState();
        possibleStates.add(this.abilityControllerState);
        possibleStates.add(this.skillsControllerState);

        this.state = this.abilityControllerState;
    }

    public void setAbilitiesSkillView(AbilitiesSkillView abilitiesSkillView) {
        this.abilitiesSkillView = abilitiesSkillView;

        this.skillsControllerState.setSkillsView(this.abilitiesSkillView.getSkillsView());
        this.abilityControllerState.setAbilitiesView(this.abilitiesSkillView.getAbilitiesView());
    }

    public void resetViews() {
        this.abilityControllerState.resetView(true);
        this.skillsControllerState.resetView(false);
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
    public boolean handleAbilitiesKey() {
        MainController.getInstance().setAvatarControllerState();
        return true;
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
        state.handleESCKey();
        return false;
    }

    @Override
    public boolean handleSouthWestKey() {
        return state.handleSouthWestKey();
    }

    @Override
    public boolean handleSouthKey() {
        return state.handleSouthKey();
    }

    @Override
    public boolean handleSouthEastKey() {
        return state.handleSouthEastKey();
    }

    @Override
    public boolean handleWestKey() {
        return state.handleWestKey();
    }

    @Override
    public boolean handleEastKey() {
        return state.handleEastKey();
    }

    @Override
    public boolean handleNorthWestKey() {
        return state.handleNorthWestKey();
    }

    @Override
    public boolean handleNorthKey() {
        return state.handleNorthKey();
    }

    @Override
    public boolean handleNorthEastKey() {
        return state.handleNorthEastKey();
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
        return state.handleLeftKey();
    }

    @Override
    public boolean handleRightKey() {
        return state.handleRightKey();
    }

    @Override
    public boolean handleDownKey() {
        return state.handleDownKey();
    }

    @Override
    public boolean handleUpKey() {
        return state.handleUpKey();
    }

    @Override
    public void handleSaveKey() {

    }

    @Override
    public void handleEnterKey() {

    }

    @Override
    public void handleAbility1Key() {
        state.handleAbility1Key();
    }

    @Override

    public void handleAbility2Key() {
        state.handleAbility2Key();
    }

    @Override
    public void handleAbility3Key() {
        state.handleAbility3Key();
    }

    @Override
    public void handleAbility4Key() {
        state.handleAbility4Key();
    }

    @Override
    public void resetView(boolean isActive) {

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
