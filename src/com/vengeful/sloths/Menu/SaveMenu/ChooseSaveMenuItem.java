package com.vengeful.sloths.Menu.SaveMenu;

import com.vengeful.sloths.Menu.MainMenu.MainMenuItem;
import com.vengeful.sloths.Menu.ScrollableMenuItemCommand;
import com.vengeful.sloths.Models.SaveLoad.SaveManager;

/**
 * Created by harrison on 3/12/16.
 */
public class ChooseSaveMenuItem extends MainMenuItem {
    private SaveManager sm;
    public ChooseSaveMenuItem(String name) {
        super(name);
    }

    @Override
    public void select(ScrollableMenuItemCommand command) {
        System.out.println("selected");
        doSave();
    }

    private void doSave(){
        SaveManager sm = new SaveManager(this.getName());
        sm.save();

    }
}
