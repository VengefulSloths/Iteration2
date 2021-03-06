package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by Alex on 3/10/2016.
 */
public class NullAbility extends Ability {

    public NullAbility() {
        super("Null", 1, 2);

    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public int execute() {
        return 0;
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        System.out.println("THis is inside of accept NullAbility");
        modelVisitor.visitNullAbility(this);
    }

    @Override
    public String toString() { return "Null Ability"; }
}
