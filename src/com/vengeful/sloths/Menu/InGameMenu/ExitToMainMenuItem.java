package com.vengeful.sloths.Menu.InGameMenu;

import com.vengeful.sloths.Menu.MainMenu.MainMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenuItemCommand;
import com.vengeful.sloths.Utility.GameResetter;

/**
 * Created by John on 3/13/2016.
 */
public class ExitToMainMenuItem extends MainMenuItem {

    public ExitToMainMenuItem(){
        super("Exit to Main Menu");
    }

    @Override
    public void select(ScrollableMenuItemCommand command) {
        GameResetter.reset();
    }
}
