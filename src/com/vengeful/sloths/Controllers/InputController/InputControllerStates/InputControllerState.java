package com.vengeful.sloths.Controllers.InputController.InputControllerStates;

/**
 * Created by John on 2/29/2016.
 */
public abstract class InputControllerState {

    public abstract void continuousFunction();
    public abstract void onDeregister();
    public abstract void onRegister();

    public abstract boolean handleIKey();
    public abstract boolean handleEKey();
    public abstract boolean handleESCKey();
    public abstract boolean handle1Key();
    public abstract boolean handle2Key();
    public abstract boolean handle3Key();
    public abstract boolean handle4Key();
    public abstract boolean handle6Key();
    public abstract boolean handle7Key();
    public abstract boolean handle8Key();
    public abstract boolean handle9Key();
    public abstract boolean handle5Key();
    public abstract boolean handleDKey();
    public abstract boolean handleLeftKey();
    public abstract boolean handleRightKey();
    public abstract boolean handleDownKey();
    public abstract boolean handleUpKey();


    public abstract void handleRelease1Key();
    public abstract void handleRelease2Key();
    public abstract void handleRelease3Key();
    public abstract void handleRelease4Key();
    public abstract void handleRelease6Key();
    public abstract void handleRelease7Key();
    public abstract void handleRelease8Key();
    public abstract void handleRelease9Key();
    public abstract void handleRelease5Key();
    public abstract boolean handleReleaseLeftKey();
    public abstract boolean handleReleaseRightKey();
    public abstract boolean handleReleaseDownKey();
    public abstract boolean handleReleaseUpKey();


}
