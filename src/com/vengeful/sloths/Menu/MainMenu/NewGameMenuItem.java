package com.vengeful.sloths.Menu.MainMenu;

import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.GameLaunching.LaunchGameTemplate;
import com.vengeful.sloths.GameLaunching.LaunchNewGame;
import com.vengeful.sloths.Menu.ScrollableMenuItemCommand;

/**
 * Created by zach on 3/7/16.
 */
public class NewGameMenuItem extends MainMenuItem {

    public NewGameMenuItem(){
        super("New Game");
    }
    @Override
    public void select(ScrollableMenuItemCommand command) {
        //super.select(command);
        this.startGame();
    }

    private void startGame() {
       // System.out.println("launch new game !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        LaunchGameTemplate launcher = new LaunchGameTemplate(new LaunchNewGame());
//        launcher.launch();
        MainController.getInstance().setCharacterCreationControllerState();
    }
}
