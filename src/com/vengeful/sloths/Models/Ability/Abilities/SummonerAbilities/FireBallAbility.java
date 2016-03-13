package com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.RangedEffects.CanGenerateVisitor.DefaultCanGenerateVisitor;
import com.vengeful.sloths.Models.RangedEffects.EntityBlockLineEffectGenerator;
import com.vengeful.sloths.Models.RangedEffects.RangedEffectGenerator;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.HexMath;
import com.vengeful.sloths.Utility.ModelConfig;

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

    private int manaCost = ModelConfig.getManaCostLow();

    private DefaultCanGenerateVisitor canGenerateVisitor;
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
        this.canGenerateVisitor = new DefaultCanGenerateVisitor();
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
        int damage = (entity.getStats().getStrength() + entity.getEquipped().getWeapon().getBaseDamage()) * (2 + entity.getSkillManager().getBaneLevel());

        Coord firingLocation = HexMath.getNextFacingCoord(entity.getLocation(), entity.getFacingDirection());
        //If the attempt to fire the ability did not fail, then initial fireball hit target accuracy = 100
        RangedEffectGenerator reg = new EntityBlockLineEffectGenerator("fireball", firingLocation, entity.getFacingDirection(), this.travelDistance, this.travelTime, damage, 100, canGenerateVisitor);
        reg.createRangedEffect();
    }

    public void accept(ModelVisitor sv){
        sv.visitFireBallAbility(this);
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    public int getTravelDistance() {
        return travelDistance;
    }

    public void setTravelDistance(int travelDistance) {
        this.travelDistance = travelDistance;
    }

    public int getStartupTicks() {
        return super.getWindTicks();
    }

    public void setStartupTicks(int startupTicks) {
        super.setWindTicks(startupTicks);
    }

    public int getCoolDownTicks() {
        return super.getCoolTicks();
    }

    public void setCoolDownTicks(int coolDownTicks) {
        super.setCoolTicks(coolDownTicks);
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
}
