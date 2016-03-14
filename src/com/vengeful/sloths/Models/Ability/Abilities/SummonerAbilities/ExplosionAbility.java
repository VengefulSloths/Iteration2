package com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.RangedEffects.*;
import com.vengeful.sloths.Models.RangedEffects.CanGenerateVisitor.DefaultCanGenerateVisitor;
import com.vengeful.sloths.Models.RangedEffects.CanGenerateVisitor.OnTileCanGenerateVisitor;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.ModelConfig;

/**
 * Created by luluding on 3/10/16.
 */
public class ExplosionAbility extends Ability{
    /* Summoner only, a type of bane spell */

    private Entity entity;
    private int expandingTime;
    private int expandingDistance;


    private int manaCost = ModelConfig.getManaCostHigh();

    private DefaultCanGenerateVisitor canGenerateVisitor;


    public ExplosionAbility(Entity entity, int expandingTime, int expandingDistance, int startupTicks, int coolDownTicks){
        super("Explosion", startupTicks, coolDownTicks);
        this.entity = entity;
        this.expandingTime = expandingTime;
        this.expandingDistance = expandingDistance;
        this.canGenerateVisitor = new OnTileCanGenerateVisitor();
        this.name = "Explosion";
    }

    @Override
    public String getDescription() {
        return "3 .. 2 .. 1 .. BOOM!!!";
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
        }, this.getWindTicks());

        TimeModel.getInstance().registerAlertable(() ->{
            System.out.println("DONE DOING EXPLOSION");
            this.entity.setActive(false);
        }, this.getCoolTicks());

        return this.getCoolTicks();

    }


    private void doAbility(){
        int damage = (entity.getStats().getStrength() + entity.getEquipped().getWeapon().getBaseDamage()) * (2 + entity.getSkillManager().getBaneLevel());

        RangedEffectGenerator reg = new RadialEffectGenerator("explosion", entity.getLocation(), this.expandingDistance, this.expandingTime, damage, 100, canGenerateVisitor);
        reg.createRangedEffect();
    }

    @Override
    public String toString() {
        return "Explosion Ability";
    }

    @Override
    public void accept(ModelVisitor e) {
        e.visitExplosionAbility(this);
    }

    public int getExpandingTime() {
        return expandingTime;
    }

    public void setExpandingTime(int expandingTime) {
        this.expandingTime = expandingTime;
    }

    public int getExpandingDistance() {
        return expandingDistance;
    }

    public void setExpandingDistance(int expandingDistance) {
        this.expandingDistance = expandingDistance;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
}
