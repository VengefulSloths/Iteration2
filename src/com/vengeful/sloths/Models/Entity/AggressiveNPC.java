package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.EntityMapInteractionFactory;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.Stats;

/**
 * Created by luluding on 2/21/16.
 */
public class AggressiveNPC extends NPC{

    public AggressiveNPC(String name, Stats stats){
        super(name, stats);
    }
    public AggressiveNPC(){}

    public void accept(ModelVisitor modelVisitor) {
       // System.out.println("fdskladskjfhsdjkfsdahfkjdshfkjsdhfjksdahfkjsdhfsdjkfhsdjkfhdsjkfhdskjfds");
        modelVisitor.visitAggressiveNPC(this);
    }

    @Override
    protected void doRespawn() {
        EntityMapInteractionFactory.getInstance().createRespawnCommand(this, this.getLocation(), timeToRespawn);
    }
}
