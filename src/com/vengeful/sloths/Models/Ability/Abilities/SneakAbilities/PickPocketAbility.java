package com.vengeful.sloths.Models.Ability.Abilities.SneakAbilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.EntityEntityInteractionCommands.EntityInitiatePickPocketCommand;
import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Utility.ModelConfig;

/**
 * Created by harrison on 3/12/16.
 */
public class PickPocketAbility extends Ability implements ModelVisitable{

    //this ability initiates the command to start the pickpocketing window and controller
    public PickPocketAbility(){
        super("Pick Pocket", 1,1);

    }
    @Override
    public int execute() {
        new EntityInitiatePickPocketCommand().execute();
        return 0;
    }

    @Override
    public String getDescription() {
        return "Steal some items";
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitPickPocketAbility(this);
    }
}
