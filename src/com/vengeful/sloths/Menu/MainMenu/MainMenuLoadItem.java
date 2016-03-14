package com.vengeful.sloths.Menu.MainMenu;

import com.vengeful.sloths.Controllers.InputController.InputControllerStates.MainMenuControllerState;
import com.vengeful.sloths.Controllers.InputController.MainController;
import com.vengeful.sloths.Menu.ScrollableMenuItemCommand;

/**
 * Created by harrison on 3/13/16.
 */
public class MainMenuLoadItem extends MainMenuItem{

    private MainScrollableMenu menu;

    public MainMenuLoadItem(String name, MainScrollableMenu menu) {
        super(name);
        this.menu = menu;
    }
    public void select(ScrollableMenuItemCommand command) {
        //super.select(command);
        this.chooseLoads();
    }

    public void chooseLoads(){
        menu.setLoadList();
    }
}
