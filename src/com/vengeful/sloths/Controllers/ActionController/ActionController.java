package com.vengeful.sloths.Controllers.ActionController;

import com.vengeful.sloths.Controllers.Target;

/**
 * Created by zach on 2/22/16.
 */
public abstract class ActionController {
    private ActionController currentActionController;


    public abstract void action(Target target);

    public void setNextActionController(ActionController actionController) {
        this.currentActionController = actionController;
    }
}
