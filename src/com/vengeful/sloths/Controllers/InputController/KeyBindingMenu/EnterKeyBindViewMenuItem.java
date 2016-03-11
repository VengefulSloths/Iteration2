package com.vengeful.sloths.Controllers.InputController.KeyBindingMenu;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.GameLaunching.LaunchGameTemplate;
import com.vengeful.sloths.GameLaunching.LaunchNewGame;
import com.vengeful.sloths.Menu.MainMenu.MainMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenuItemCommand;

/**
 * Created by John on 3/11/2016.
 */
public class EnterKeyBindViewMenuItem extends MainMenuItem{
    public EnterKeyBindViewMenuItem(){
        super("Re-Bind Keys");
    }
    @Override
    public void select(ScrollableMenuItemCommand command) {
        //super.select(command);
        this.openBindKeys();
    }

    private void openBindKeys() {
        MainController.getInstance().setSetInputsControllerState();
    }
}
