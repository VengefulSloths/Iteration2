package com.vengeful.sloths.Menu;

import com.vengeful.sloths.GameLaunching.LaunchGameTemplate;
import com.vengeful.sloths.GameLaunching.LaunchNewGame;
import com.vengeful.sloths.GameLaunching.LaunchSavedGame;

/**
 * Created by icavitt on 3/10/2016.
 */
public class LoadGameMenuItem extends MainMenuItem {

    public LoadGameMenuItem(String name) {
        super(name);
    }

    public void select(ScrollableMenuItemCommand command) {
        //super.select(command);
        this.startGame();
    }

    public void startGame(){
        LaunchGameTemplate launcher = new LaunchGameTemplate(new LaunchSavedGame());
        launcher.launchLoaded();
    }
}
