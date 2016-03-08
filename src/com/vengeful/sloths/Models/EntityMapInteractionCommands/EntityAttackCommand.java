package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Models.Observers.EntityObserver;

import java.util.Iterator;

/**
 * Created by John on 2/29/2016.
 */
public class EntityAttackCommand implements Alertable {
    private Coord src;
    private Coord dst;
    private Entity subject;
    private Map map;
    private CanMoveVisitor canMoveVisitor;
    private Iterator<EntityObserver> entityObserverIterator;
    private int attackSpeed;
    private int attackDamage;

    public static final int MAX_ATTACKSPEED = 60;

    public EntityAttackCommand(Coord src, Direction dir, int attackSpeed, int attackDamage, Map map, Entity entity, Iterator<EntityObserver> entityObserverIterator) {
        this.src = src;
        this.subject = entity;
        this.map = map;
        //this.canMoveVisitor = canMoveVisitor; //yeah look at this again lol
        this.attackDamage = attackDamage;
        this.entityObserverIterator = entityObserverIterator;
        this.attackSpeed = attackSpeed;

        this.dst = new Coord(src.getR(), src.getS());
        switch (dir) {
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

        System.out.println("Model: Attacking from " + src.toString() + " to " + dst.toString());

    }

    public int execute() {
        if (subject.isActive()) return 0;

        int attackTicks = MAX_ATTACKSPEED - attackSpeed;
        TimeModel.getInstance().registerAlertable(this, attackTicks );

        //TODO: have take damage occur at end of startup time
        //do the attack... create an ae of dmg on dst tile?
        try {
            for (Entity entity : map.getTile(dst).getEntities()) {
                entity.takeDamage(attackDamage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("about to alert observers @" + System.currentTimeMillis());

        while (entityObserverIterator.hasNext()) {


           entityObserverIterator.next().alertAttack(dst.getR(), dst.getS(), attackTicks/2* TimeController.MODEL_TICK, attackTicks*TimeController.MODEL_TICK);
            //the commented line above can work if that method is added and the animations are put in place
        }
        subject.setActive(true);
        return attackTicks;

    }

    @Override
    public void mAlert() {
        System.out.println("okay attack done @" + System.currentTimeMillis());
        subject.setActive(false);
    }
}
