package com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by harrison on 3/12/16.
 */
public class PickPocketAbility extends Ability implements ModelVisitable{
    //this ability initiates the command to start the pickpocketing window and controller
    public PickPocketAbility(){
        super("Pick Pocket", 1, 2);

    }
    @Override
    public int execute() {
        new EntityInitiatePickPocketCommand().execute();
        return 0;
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitPickPocketAbility(this);
    }
}
