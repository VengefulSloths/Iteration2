package com.vengeful.sloths.Models.RangedEffects.HitBox;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by luluding on 3/10/16.
 */
public class ImmovableHitBox extends HitBox implements ModelVisitable{

    public ImmovableHitBox(String name, Coord location, int dmg, int accuracy){
        super(name, location, dmg, accuracy);
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitImmovableHitBox(this);
    }
}
