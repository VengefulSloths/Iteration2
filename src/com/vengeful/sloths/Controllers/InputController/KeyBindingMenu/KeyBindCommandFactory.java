package com.vengeful.sloths.Controllers.InputController.KeyBindingMenu;

import com.vengeful.sloths.Controllers.InputController.InputStrategies.AdaptableStrategy;
import com.vengeful.sloths.Controllers.InputController.KeyMapping;

/**
 * Created by John on 3/10/2016.
 */
public class KeyBindCommandFactory {
    private AdaptableStrategy adaptableStrategy;

    public KeyBindCommandFactory(AdaptableStrategy adaptableStrategy){
        this.adaptableStrategy = adaptableStrategy;
    }

    public KeyBindCommand createKeyBindCommand(int keyCode, KeyBindMenuItem item){
        KeyMapping keyMapping =  item.getKeyMapping();
        KeyBindCommand command = new KeyBindCommand(keyMapping, keyCode, adaptableStrategy);
        return command;
    }


}
