package com.vengeful.sloths.Menu.SaveMenu;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.GameLaunching.LaunchGameTemplate;
import com.vengeful.sloths.GameLaunching.LaunchSavedGame;
import com.vengeful.sloths.Menu.MainMenu.MainMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenuItemCommand;
import com.vengeful.sloths.Models.SaveLoad.SaveManager;

/**
 * Created by harrison on 3/12/16.
 */
public class SaveGameMenuItem extends MainMenuItem {

    public SaveGameMenuItem(String name) {
        super(name);
    }
    public void select(ScrollableMenuItemCommand command) {
        //super.select(command);
        this.chooseSaves();
    }

    public void chooseSaves(){
        MainController.getInstance().setChooseSaveControllerState();
    }
}
