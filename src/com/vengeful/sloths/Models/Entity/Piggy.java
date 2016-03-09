package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/21/16.
 */
public class Piggy extends Pet {

    public Piggy(String name, Stats stats){

        super(name, stats);
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitPiggy(this);
    }

}
