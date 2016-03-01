package com.vengeful.sloths.Controllers.InputController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Created by John on 2/29/2016.
 */
public class InputHandler implements KeyListener{

    private MainController mainController = MainController.getInstance();

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        mainController.dispatchPressedKey(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        mainController.dispatchReleasedKey(e.getKeyCode());
    }
}
