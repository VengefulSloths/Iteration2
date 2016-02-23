package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by luluding on 2/21/16.
 */
public class Piggy extends Pet {

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitPiggy(this);
    }
}
