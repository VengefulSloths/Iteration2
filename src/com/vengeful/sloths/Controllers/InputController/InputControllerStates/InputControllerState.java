package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

/**
 * Created by John on 2/29/2016.
 */
public abstract class InputControllerState {

    public abstract void continuousFunction();
    public abstract void onDeregister();
    public abstract void onRegister();

    public abstract boolean handleInventoryKey();
    public abstract boolean handleEquipmentKey();
    public abstract boolean handleESCKey();
    public abstract boolean handleSouthWestKey();
    public abstract boolean handleSouthKey();
    public abstract boolean handleSouthEastKey();
    public abstract boolean handleWestKey();
    public abstract boolean handleEastKey();
    public abstract boolean handleNorthWestKey();
    public abstract boolean handleNorthKey();
    public abstract boolean handleNorthEastKey();
    public abstract boolean handleCenterKey();
    public abstract boolean handleDropKey();
    public abstract boolean handleLeftKey();
    public abstract boolean handleRightKey();
    public abstract boolean handleDownKey();
    public abstract boolean handleUpKey();
    //Added for saving
    public abstract void handleSaveKey();
    public abstract void handleEnterKey();

    public abstract boolean handleSpaceKey();

    public abstract void handleReleaseSouthWestKey();
    public abstract void handleReleaseSouthKey();
    public abstract void handleReleaseSouthEastKey();
    public abstract void handleReleaseWestKey();
    public abstract void handleReleaseEastKey();
    public abstract void handleReleaseNorthWestKey();
    public abstract void handleReleaseNorthKey();
    public abstract void handleReleaseNorthEastKey();
    public abstract void handleReleaseCenterKey();
    public abstract boolean handleReleaseLeftKey();
    public abstract boolean handleReleaseRightKey();
    public abstract boolean handleReleaseDownKey();
    public abstract boolean handleReleaseUpKey();


}
