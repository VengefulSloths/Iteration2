package com.vengeful.sloths.Controllers.InputController.InputStrategies;

import com.vengeful.sloths.Controllers.InputController.InputControllerStates.InputControllerState;

/**
 * Created by John on 2/29/2016.
 */
public abstract class InputStrategy {


    public abstract void interpretPressedKey(int key, InputControllerState state);
    public abstract void interpretReleasedKey(int key, InputControllerState state);

}
