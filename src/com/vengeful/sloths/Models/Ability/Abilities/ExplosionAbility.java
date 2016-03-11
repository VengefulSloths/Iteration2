package com.vengeful.sloths.Models.Ability.Abilities;

import com.sun.corba.se.impl.encoding.EncapsInputStream;
import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.RangedEffects.*;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

/**
 * Created by luluding on 3/10/16.
 */
public class ExplosionAbility extends Ability{
    /* Summoner only, a type of bane spell */

    private Entity entity;
    private int expandingTime;
    private int expandingDistance;
    private int startupTicks;
    private int coolDownTicks;

    private int manaCost = 6;

    private DefaultCanGenerateVisitor canGenerateVisitor;


    public ExplosionAbility(Entity entity, int expandingTime, int expandingDistance, int startupTicks, int coolDownTicks){
        this.entity = entity;
        this.expandingTime = expandingTime;
        this.expandingDistance = expandingDistance;
        this.startupTicks = startupTicks;
        this.coolDownTicks = coolDownTicks;
        this.canGenerateVisitor = new OnTileCanGenerateVisitor();
    }





    @Override
    public int execute() {
        if(this.entity.isActive())
            return 0;

        if(!shouldDoAbility(entity.getSkillManager().getBaneLevel(), entity.getSkillManager().getMaxBaneLevel()))
            return 0;


        this.entity.setActive(true);
        this.entity.decMana(this.manaCost);

        TimeModel.getInstance().registerAlertable(() ->{
            doAbility();
        }, startupTicks);

        TimeModel.getInstance().registerAlertable(() ->{
            System.out.println("DONE DOING EXPLOSION");
            this.entity.setActive(false);
        }, coolDownTicks);

        return coolDownTicks;

    }


    private void doAbility(){
        int damage = entity.getStats().getOffensiveRating() * (1 + entity.getSkillManager().getBaneLevel());

        //If the attempt to fire the ability did not fail, then initial fireball hit target accuracy = 100
        //RangedEffectGenerator reg = new EntityBlockLineEffectGenerator("fireball", entity.getLocation(), entity.getFacingDirection(), this.travelDistance, this.travelTime, damage, 100);
        //reg.createRangedEffect();
        RangedEffectGenerator reg = new RadialEffectGenerator("explosion", entity.getLocation(), this.expandingDistance, this.expandingTime, damage, 100, canGenerateVisitor);
        reg.createRangedEffect();
    }




    @Override
    public String toString() {
        return "ExplosionAbility";
    }
}
