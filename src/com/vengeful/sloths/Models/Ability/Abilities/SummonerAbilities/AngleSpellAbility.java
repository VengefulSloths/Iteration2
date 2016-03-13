package com.vengeful.sloths.Models.Ability.Abilities.SummonerAbilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.RangedEffects.AngularEffectGenerator;
import com.vengeful.sloths.Models.RangedEffects.CanGenerateVisitor.DefaultCanGenerateVisitor;
import com.vengeful.sloths.Models.RangedEffects.CanGenerateVisitor.OnTileCanGenerateVisitor;
import com.vengeful.sloths.Models.RangedEffects.RadialEffectGenerator;
import com.vengeful.sloths.Models.RangedEffects.RangedEffectGenerator;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.ModelConfig;

/**
 * Created by luluding on 3/11/16.
 */
//TODO: not sure what to call it now. Can refactor later to rename it
public class AngleSpellAbility extends Ability{
    /* Summoner only, a type of bane spell */
    private Entity entity;
    private int expandingTime;
    private int expandingDistance;

    private int manaCost = ModelConfig.getManaCostMid();

    private DefaultCanGenerateVisitor canGenerateVisitor;

    public AngleSpellAbility(Entity entity, int expandingTime, int expandingDistance, int startupTicks, int coolDownTicks){
        super("Cone of Death", startupTicks, coolDownTicks);
        this.entity = entity;
        this.expandingTime = expandingTime;
        this.expandingDistance = expandingDistance;
        this.canGenerateVisitor = new OnTileCanGenerateVisitor();
        this.name = "Angel Spell";
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
            int damage = (entity.getStats().getStrength() + entity.getEquipped().getWeapon().getBaseDamage()) * (2 + entity.getSkillManager().getBaneLevel());
            RangedEffectGenerator reg = new AngularEffectGenerator("explosion", entity.getLocation(), this.expandingDistance, this.expandingTime, damage, 100, canGenerateVisitor, entity.getFacingDirection());
            reg.createRangedEffect();
        }, this.getWindTicks());

        TimeModel.getInstance().registerAlertable(() ->{
            this.entity.setActive(false);
        }, this.getCoolTicks());

        return this.getCoolTicks();
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitAngleSpellAbility(this);
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

    @Override
    public String toString() {
        return "Angel Spell Ability";
    }

}
