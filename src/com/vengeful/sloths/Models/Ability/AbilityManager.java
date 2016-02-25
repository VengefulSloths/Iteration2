package com.vengeful.sloths.Models.Ability;

import com.vengeful.sloths.Models.ModelVisitable;
import com.vengeful.sloths.Models.ModelVisitor;

import java.util.ArrayList;

/**
 * Created by luluding on 2/21/16.
 */
public class AbilityManager implements ModelVisitable{

    private ArrayList<Ability> abilities;

    public AbilityManager(){
        this.abilities = new ArrayList<Ability>();
    }


    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitAbilityManager(this);
    }
}
