package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.RangedEffects.EntityBlockLineEffectGenerator;
import com.vengeful.sloths.Models.RangedEffects.EntityPassThroughLineEffectGenerator;
import com.vengeful.sloths.Models.RangedEffects.LinearEffectGenerator;
import com.vengeful.sloths.Models.RangedEffects.RangedEffectGenerator;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.Coord;

import java.util.Iterator;


/**
 * Created by luluding on 3/7/16.
 */
public class FireBallAbility extends Ability{
    /* Summoner only, a type of bane spell */

    //NOTE: can't trust any entity internal attribute reference set in the constructor

    private Entity entity;
    private int travelTime;
    private int travelDistance;

    private int manaCost = 2;
    /*
        Linear Effect -> mana cost = Low
        Angular Effect -> mana cost = Medium
        Radial Effect -> mana cost = High
    */

    public FireBallAbility(Entity entity, int travelTime, int travelDistance, int startupTicks, int coolDownTicks){
        super(startupTicks, coolDownTicks);
        this.entity = entity;
        this.travelTime = travelTime;
        this.travelDistance = travelDistance;
    }

    @Override
    public int execute() {
        if(this.entity.isActive())
            return 0;

        if(!shouldDoAbility(entity.getSkillManager().getBaneLevel(), entity.getSkillManager().getMaxBaneLevel()))
            return 0;

        this.entity.setActive(true);
        this.entity.decMana(this.manaCost);

        Iterator<EntityObserver> observers = entity.getObservers().iterator();
        while (observers.hasNext()) {
            System.out.println("alerting cast spells");
            observers.next().alertCast(getWindTicks()* TimeController.MODEL_TICK, getCoolTicks()*TimeController.MODEL_TICK);
        }
        TimeModel.getInstance().registerAlertable(() ->{
            doAbility();
        }, getWindTicks());

        TimeModel.getInstance().registerAlertable(() ->{
            System.out.println("DONE FIRING THE FIRE BALL");
            this.entity.setActive(false);
        }, getCoolTicks());

        return getCoolTicks();
    }

    @Override
    public String toString() {
        return "FireBallAbility";
    }

    private void doAbility(){
        //TODO: better formula.
        int damage = entity.getStats().getOffensiveRating() * (1 + entity.getSkillManager().getBaneLevel());

        //If the attempt to fire the ability did not fail, then initial fireball hit target accuracy = 100
        RangedEffectGenerator reg = new EntityBlockLineEffectGenerator("fireball", entity.getLocation(), entity.getFacingDirection(), this.travelDistance, this.travelTime, damage, 100);
        reg.createRangedEffect();
    }
}
