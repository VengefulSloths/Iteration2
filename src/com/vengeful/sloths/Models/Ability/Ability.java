package com.vengeful.sloths.Models.Ability;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class Ability implements ModelVisitable {

    public abstract void execute();


    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitAbility(this);
    }
}
