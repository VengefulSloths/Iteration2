package com.vengeful.sloths.Models.DialogueTrade;

import com.vengeful.sloths.Controllers.InputController.MainController;

/**
 * Created by John on 3/12/2016.
 */
public class TerminalDialogContainer extends DialogContainer {
    public TerminalDialogContainer(String name) {
        super(name);
    }

    @Override
    protected void terminalAction() {
        //will end and cut back to areaview stuff
        MainController.getInstance().setAvatarControllerState();
    }
}
