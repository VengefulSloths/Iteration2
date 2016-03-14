package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.Views.SkillsView.SkillsView;

/**
 * Created by zach on 3/12/16.
 */
public class SkillsControllerState extends  InputControllerState {
    private SkillsView skillsView;

    public SkillsControllerState() {

    }

    public void setSkillsView(SkillsView skillsView) {
        this.skillsView = skillsView;
    }

    public void resetView(boolean isActiveView) {
        this.skillsView.resetView(isActiveView);
    }

    @Override
    public void continuousFunction() {

    }

    @Override
    public void handleTalkKey() {

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
        return false;
    }

    @Override
    public boolean handleSouthKey() {
        this.skillsView.selectSouthItem();
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

        this.skillsView.selectNorthItem();
        return true;
    }

    @Override
    public boolean handleNorthEastKey() {

        this.skillsView.useSkillPoint();
        return true;
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
        this.skillsView.selectSouthItem();
        return true;
    }

    @Override
    public boolean handleUpKey() {
        this.skillsView.selectNorthItem();
        return true;
    }

    @Override
    public void handleSaveKey() {

    }

    @Override
    public void handleEnterKey() {

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
