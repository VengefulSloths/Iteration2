package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 1/30/2016.
 */
public abstract class OneShotItem extends MapItem {

    public void interact(Entity entity){
        //build effect command
    }

    public abstract void accept(ModelVisitor visitor);
}
