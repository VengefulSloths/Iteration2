package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.EntityMapInteractionFactory;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Weapon;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.SaveLoad.SaveManager;
import com.vengeful.sloths.Models.SaveLoad.SaveVisitor;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Models.Observers.EntityObserver;

import java.util.Iterator;

/**
 * Created by Alex on 3/7/2016.
 */
public class MeleeAttackAbility extends Ability {
    private Entity entity;

    private Stats stats;
    private Skill relevantSkill;
    private int baseDamage;

    public MeleeAttackAbility(Entity entity, Skill relevantSkill, int baseDamage, int windTicks, int coolTicks) {
        super("Melee Attack", windTicks, coolTicks);
        this.entity = entity;
        this.stats = entity.getStats();
        this.relevantSkill = relevantSkill;
        this.baseDamage = baseDamage;
    }

    @Override
    public String getDescription() {
        return "Doing damage";
    }

    @Override
    public int execute() {
        if (entity.isActive()) return 0;

        entity.setActive(true);

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

        Iterator<EntityObserver> iter = entity.getObservers().iterator();
        while (iter.hasNext()) {
            iter.next().alertAttack(dst.getR(), dst.getS(), getWindTicks()* TimeController.MODEL_TICK, getCoolTicks()* TimeController.MODEL_TICK);
        }

        int damage = (entity.getStats().getStrength() + baseDamage)*(1+relevantSkill.getLevel());

        TimeModel.getInstance().registerAlertable(() -> {
            try {
                for (Entity entity : Map.getInstance().getTile(dst).getEntities()) {
                    entity.takeDamage(damage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, getWindTicks());

        TimeModel.getInstance().registerAlertable(() -> entity.setActive(false), getCoolTicks());

        return getCoolTicks();
    }


//    @Override
//    public void accept(ModelVisitor modelVisitor) {
//        super.accept(modelVisitor);
//    }
    public void accept(SaveVisitor sv){
        sv.visitMeleeAttackAbility(this);
    }


    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String toString() {
        return "Melee Attack";
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitMeleeAttackAbility(this);
    }

//    @Override
//    public void accept(ModelVisitor modelVisitor) {
//        super.accept(modelVisitor);
//    }
}
