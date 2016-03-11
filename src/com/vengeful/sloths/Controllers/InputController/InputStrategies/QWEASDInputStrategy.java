package com.vengeful.sloths.Controllers.InputController.InputStrategies;

import com.vengeful.sloths.Controllers.InputController.InputControllerStates.InputControllerState;

import java.awt.event.KeyEvent;

/**
 * Created by John on 2/29/2016.
 */
public class QWEASDInputStrategy extends InputStrategy {

    @Override
    public void interpretPressedKey(int key, InputControllerState state) {
        switch (key) {
            case KeyEvent.VK_6: //edit
                state.handleEastKey();
                break;
            case KeyEvent.VK_4:
                state.handleWestKey();
                break;
            case KeyEvent.VK_Q:
                state.handleNorthWestKey();
                break;
            case KeyEvent.VK_W:
                state.handleNorthKey();
                break;
            case KeyEvent.VK_E:
                state.handleNorthEastKey();
                break;
            case KeyEvent.VK_A:
                state.handleSouthWestKey();
                break;
            case KeyEvent.VK_S:
                state.handleSouthKey();
                break;
            case KeyEvent.VK_D:
                state.handleSouthEastKey();
                break;
            case KeyEvent.VK_I:
                state.handleInventoryKey();
                break;
            case KeyEvent.VK_R:
                state.handleEquipmentKey();
                break;
            case KeyEvent.VK_ESCAPE:
                state.handleESCKey();
                break;
            case KeyEvent.VK_UP:
                state.handleUpKey();
                break;
            case KeyEvent.VK_DOWN:
                state.handleDownKey();
                break;
            case KeyEvent.VK_RIGHT:
                state.handleRightKey();
                break;
            case KeyEvent.VK_LEFT:
                state.handleLeftKey();
                break;
            case KeyEvent.VK_SPACE:
                state.handleSpaceKey();
                break;
            case KeyEvent.VK_ENTER:
                state.handleCenterKey();
                break;
            case KeyEvent.VK_P:
                state.handleSaveKey();
        }
    }

    @Override
    public void interpretReleasedKey(int key, InputControllerState state) {
        switch (key) {
            case KeyEvent.VK_Q:
                state.handleReleaseNorthWestKey();
                break;
            case KeyEvent.VK_W:
                state.handleReleaseNorthKey();
                break;
            case KeyEvent.VK_E:
                state.handleReleaseNorthEastKey();
                break;
            case KeyEvent.VK_A:
                state.handleReleaseSouthWestKey();
                break;
            case KeyEvent.VK_S:
                state.handleReleaseSouthKey();
                break;
            case KeyEvent.VK_D:
                state.handleReleaseSouthEastKey();
                break;
            case KeyEvent.VK_UP:
                state.handleReleaseUpKey();
                break;
            case KeyEvent.VK_DOWN:
                state.handleReleaseDownKey();
                break;
            case KeyEvent.VK_RIGHT:
                state.handleReleaseRightKey();
                break;
            case KeyEvent.VK_LEFT:
                state.handleReleaseLeftKey();
                break;


        }
    }
}
