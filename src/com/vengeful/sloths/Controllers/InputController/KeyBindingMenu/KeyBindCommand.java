package com.vengeful.sloths.Controllers.InputController.KeyBindingMenu;

import com.vengeful.sloths.Controllers.InputController.InputStrategies.AdaptableStrategy;
import com.vengeful.sloths.Controllers.InputController.KeyMapping;
import com.vengeful.sloths.Menu.ScrollableMenuItemCommand;

/**
 * Created by John on 3/10/2016.
 */
public class KeyBindCommand implements ScrollableMenuItemCommand {

    private KeyMapping keyMapping;
    private int keyCode;
    private AdaptableStrategy adaptableStrategy;
    private KeyBindMenuItem item;

    public KeyBindCommand(KeyMapping keyMapping, int keyCode, AdaptableStrategy adaptableStrategy, KeyBindMenuItem item){
        this.keyMapping = keyMapping;
        this.keyCode = keyCode;
        this.adaptableStrategy = adaptableStrategy;
        this.item = item;
    }
    @Override
    public void execute() {
        System.out.println("Rebinding Key");
        adaptableStrategy.setKeyMappings(this.keyCode, this.keyMapping);
        item.setCurrentMappedKey(keyCode);
    }
}
