package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

import com.vengeful.sloths.GameLaunching.LaunchGameTemplate;
import com.vengeful.sloths.GameLaunching.LaunchNewGame;
import com.vengeful.sloths.Menu.CharacterCreation.CharacterCreationView;
import com.vengeful.sloths.Models.ModelEngine;

/**
 * Created by John on 3/12/2016.
 */
public class CharacterCreationControllerState extends InputControllerState {

    private CharacterCreationView menu;

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
    public void resetView(boolean isActive) {

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
        menu.left();
        return false;
    }

    @Override
    public boolean handleRightKey() {
        menu.right();
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
        System.out.println("launch game");
        ModelEngine.getInstance().unpauseGame();
        LaunchGameTemplate launcher = new LaunchGameTemplate(new LaunchNewGame());
        launcher.launch(menu.getOccupation());
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
    public void handleTalkKey() {

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

    public CharacterCreationView getMenu() {
        return menu;
    }

    public void setMenu(CharacterCreationView menu) {
        this.menu = menu;
    }
}
