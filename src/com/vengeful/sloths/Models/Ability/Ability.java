package com.vengeful.sloths.Models.Ability;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by luluding on 2/21/16.
 */
public abstract class Ability implements ModelVisitable {

    public abstract int execute();


    //TODO: put this at a lower level for saving
    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitAbility(this);
    }
    //this is here for aid in loading, if you add more abilities have the toString just return the name of the class as a string
    public abstract String toString();
}
