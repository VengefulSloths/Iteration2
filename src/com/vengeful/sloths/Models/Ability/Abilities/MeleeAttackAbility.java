package com.vengeful.sloths.Models.Ability.Abilities;

import com.vengeful.sloths.Models.Ability.Ability;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.EntityMapInteractionFactory;
import com.vengeful.sloths.Models.InventoryItems.EquippableItems.Weapon;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.SaveLoad.SaveManager;
import com.vengeful.sloths.Models.SaveLoad.SaveVisitor;
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
    private int windTicks;
    private int coolTicks;


    public MeleeAttackAbility(Entity entity, int windTicks, int coolTicks) {
        this.entity = entity;
        this.stats = entity.getStats();
        this.windTicks = windTicks;
        this.coolTicks = coolTicks;
    }

    public MeleeAttackAbility(int windTicks, int coolTicks){
        this.windTicks = windTicks;
        this.coolTicks = coolTicks;
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
            iter.next().alertAttack(dst.getR(), dst.getS(), windTicks* TimeController.MODEL_TICK, coolTicks* TimeController.MODEL_TICK);
        }



            TimeModel.getInstance().registerAlertable(() -> {
                try {
                    for (Entity entity : Map.getInstance().getTile(dst).getEntities()) {
                        entity.takeDamage(stats.getOffensiveRating());
                    }
                } catch (Exception e) {
                        //do nothing its fine
                }
            }, windTicks);

        TimeModel.getInstance().registerAlertable(() -> entity.setActive(false), coolTicks);

        return coolTicks;
    }



//    @Override
//    public void accept(ModelVisitor modelVisitor) {
//        super.accept(modelVisitor);
//    }
    public void accept(SaveVisitor sv){
        sv.visitMeleeAttackAbility(this);
    }

    public int getCoolTicks() {
        return coolTicks;
    }

    public void setCoolTicks(int coolTicks) {
        this.coolTicks = coolTicks;
    }

    public int getWindTicks() {
        return windTicks;
    }

    public void setWindTicks(int windTicks) {
        this.windTicks = windTicks;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String toString(){
        return "MeleeAttackAbility";
    }
}
