package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Views.AbilitiesSkillsView.AbilitiesSkillView;

/**
 * Created by zach on 3/12/16.
 */
public class AbilitySkillsControllerState extends InputControllerState {
    private AbilitiesSkillView abilitiesSkillView;

    private InputControllerState state;

    private SkillsControllerState skillsControllerState;
    private AbilityControllerState abilityControllerState;

    public AbilitySkillsControllerState() {
        this.abilityControllerState = new AbilityControllerState();
        this.skillsControllerState = new SkillsControllerState();

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
    public void handleSaveKey() {

    }

    @Override
    public void handleEnterKey() {

    }

    @Override
    public void handleAbility0Key() {

    }

    // Set state to AbilityControllerState
    @Override
    public void handleAbility1Key() {
        // @TODO: Set state selected!

        this.state = this.abilityControllerState;
        this.skillsControllerState.resetView(false);
        this.abilityControllerState.resetView(true);

    }

    @Override
    public void handleAbility2Key() {

    }

    @Override
    public void handleAbility3Key() {
        // @TODO: Set state selected!

        this.state = this.skillsControllerState;
        this.skillsControllerState.resetView(true);
        this.abilityControllerState.resetView(false);
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
