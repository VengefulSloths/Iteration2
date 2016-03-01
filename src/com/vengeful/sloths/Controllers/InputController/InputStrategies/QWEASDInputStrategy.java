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
            case KeyEvent.VK_Q:
                state.handle7Key();
                break;
            case KeyEvent.VK_W:
                state.handle8Key();
                break;
            case KeyEvent.VK_E:
                state.handle9Key();
                break;
            case KeyEvent.VK_A:
                state.handle1Key();
                break;
            case KeyEvent.VK_S:
                state.handle2Key();
                break;
            case KeyEvent.VK_D:
                state.handle3Key();
                break;
            case KeyEvent.VK_I:
                state.handleIKey();
                break;
            case KeyEvent.VK_R:
                state.handleEKey();
                break;
            case KeyEvent.VK_ESCAPE:
                state.handleESCKey();
                break;

        }
    }

    @Override
    public void interpretReleasedKey(int key, InputControllerState state) {
        switch (key) {
            case KeyEvent.VK_Q:
                state.handleRelease7Key();
                break;
            case KeyEvent.VK_W:
                state.handleRelease8Key();
                break;
            case KeyEvent.VK_E:
                state.handleRelease9Key();
                break;
            case KeyEvent.VK_A:
                state.handleRelease1Key();
                break;
            case KeyEvent.VK_S:
                state.handleRelease2Key();
                break;
            case KeyEvent.VK_D:
                state.handleRelease3Key();
                break;


        }
    }
}
