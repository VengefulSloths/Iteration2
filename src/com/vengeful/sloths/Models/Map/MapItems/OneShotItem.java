package com.vengeful.sloths.Models.Map.MapItems;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by John on 1/30/2016.
 */
public class OneShotItem extends MapItem implements ModelVisitable{

    public void interact(Entity entity){
        //build effect command
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visitOneShotItem(this);
    }

}
