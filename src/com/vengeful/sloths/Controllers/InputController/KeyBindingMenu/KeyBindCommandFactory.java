package com.vengeful.sloths.Controllers.InputController.KeyBindingMenu;

import com.vengeful.sloths.Controllers.InputController.InputStrategies.AdaptableStrategy;

/**
 * Created by John on 3/10/2016.
 */
public class KeyBindCommandFactory {
    private AdaptableStrategy adaptableStrategy;

    public KeyBindCommandFactory(AdaptableStrategy adaptableStrategy){
        this.adaptableStrategy = adaptableStrategy;
    }


}
