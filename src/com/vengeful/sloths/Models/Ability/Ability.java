package com.vengeful.sloths.Models.Ability;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by luluding on 2/21/16.
 */
public class Ability implements ModelVisitable {
    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitAbility(this);
    }
}
