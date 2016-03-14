package com.vengeful.sloths.Menu.MainMenu.LoadMenu;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Menu.MainMenu.MainMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenu;
import com.vengeful.sloths.Menu.ScrollableMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenuItemCommand;

/**
 * Created by harrison on 3/13/16.
 */

public class LoadGameMenuItem extends MainMenuItem{

    public LoadGameMenuItem(String name) {
        super(name);
    }
    public void select(ScrollableMenuItemCommand command) {
        //super.select(command);
        this.chooseLoads();
    }

    public void chooseLoads(){
        MainController.getInstance().setChooseLoadControllerState();
    }
}
