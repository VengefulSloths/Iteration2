package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by Alex on 3/10/2016.
 */
public class NullAbility extends Ability {

    public NullAbility() {
        super(1, 2);
    }

    @Override
    public int execute() {
        return 0;
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        System.out.println("THis is inside of accept NullAbility");
        (new Exception()).printStackTrace();
    }
}
