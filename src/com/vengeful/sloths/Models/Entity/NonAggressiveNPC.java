package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/21/16.
 */
public class NonAggressiveNPC extends NPC{

    public NonAggressiveNPC(String name, Stats stats){
        super(name, stats);
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitNonAggressiveNPC(this);
    }

}
