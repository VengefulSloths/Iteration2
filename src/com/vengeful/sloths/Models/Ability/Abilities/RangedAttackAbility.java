package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.Observers.EntityObserver;
import com.vengeful.sloths.Models.RangedEffects.CanGenerateVisitor.DefaultCanGenerateVisitor;
import com.vengeful.sloths.Models.RangedEffects.EntityBlockLineEffectGenerator;
import com.vengeful.sloths.Models.RangedEffects.RangedEffectGenerator;
import com.vengeful.sloths.Models.SaveLoad.SaveVisitor;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.HexMath;
import com.vengeful.sloths.Utility.ModelConfig;

import java.util.Iterator;

/**
 * Created by Alex on 3/13/2016.
 */
public class RangedAttackAbility extends Ability{
    private Entity entity;

    private Skill relevantSkill;
    private int baseDamage;

    private DefaultCanGenerateVisitor canGenerateVisitor = new DefaultCanGenerateVisitor();

    public RangedAttackAbility(Entity entity, Skill relevantSkill, int baseDamage, int windTicks, int coolTicks ) {
        super("Ranged Attack", windTicks, coolTicks);
        this.entity = entity;
        this.relevantSkill = relevantSkill;
        this.baseDamage = baseDamage;
        this.name = "Ranged Attack";
    }

    @Override
    public int execute() {
        if (entity.isActive() ) return 0;

        entity.setActive(true);



        Iterator<EntityObserver> iter = entity.getObservers().iterator();
        Coord src = entity.getLocation();
        Coord dst = new Coord(src.getR(), src.getS());
        switch (entity.getFacingDirection()) {
            case N:
                dst.setS(dst.getS() - 1);
                break;
            case S:
                dst.setS(dst.getS() + 1);
                break;
            case NE:
                dst.setR(dst.getR() + 1);
                dst.setS(dst.getS() - 1);
                break;
            case NW:
                dst.setR(dst.getR() - 1);
                break;
            case SE:
                dst.setR(dst.getR() + 1);
                break;
            case SW:
                dst.setR(dst.getR() - 1);
                dst.setS(dst.getS() + 1);
                break;
            default:
                break;
        }

        while (iter.hasNext()) {
            iter.next().alertAttack(dst.getR(), dst.getS(), getWindTicks()* TimeController.MODEL_TICK, getCoolTicks()* TimeController.MODEL_TICK);
        }


        TimeModel.getInstance().registerAlertable(() ->{
            int damage = ModelConfig.calcuateDamage(baseDamage, entity.getStats().getAgility(), relevantSkill.getLevel());
            Coord firingLocation = HexMath.getNextFacingCoord(entity.getLocation(), entity.getFacingDirection());
            RangedEffectGenerator reg = new EntityBlockLineEffectGenerator("physical_projectile", firingLocation, entity.getFacingDirection(), 5, 4, damage, 100, canGenerateVisitor);
            reg.createRangedEffect();
        }, getWindTicks());


        TimeModel.getInstance().registerAlertable(() ->{
            this.entity.setActive(false);
        }, getCoolTicks());

        return getCoolTicks();
    }

    @Override
    public String getDescription() {
        return "A long range attack";
    }

    public void accept(SaveVisitor sv){

        sv.visitRangedAttact(this);
    }


    @Override
    public void accept(ModelVisitor v) {
        System.out.println("just tried to accept a ranged attack ability");
        (new Exception()).printStackTrace();
    }

    public String toString() { return name; }
}
