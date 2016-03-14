package com.vengeful.sloths.Menu.MainMenu.LoadMenu;

import com.vengeful.sloths.GameLaunching.LaunchGameTemplate;
import com.vengeful.sloths.GameLaunching.LaunchSavedGame;
import com.vengeful.sloths.Menu.MainMenu.MainMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenuItemCommand;

/**
 * Created by harrison on 3/13/16.
 */
public class ChooseLoadMenuItem extends MainMenuItem {

    public ChooseLoadMenuItem(String name) {
        super(name);
    }

    @Override
    public void select(ScrollableMenuItemCommand command){
        this.doLoad();
    }

    private void doLoad(){
        LaunchGameTemplate launcher = new LaunchGameTemplate(new LaunchSavedGame(this.getName()));
        launcher.launchLoaded();
    }
}
