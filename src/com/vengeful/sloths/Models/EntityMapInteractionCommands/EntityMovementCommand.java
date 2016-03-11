package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Models.Observers.EntityObserver;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by alexs on 2/28/2016.
 */
public class EntityMovementCommand implements Alertable{
    private Coord src;
    private Coord dst;
    private Entity subject;
    private Map map;
    private CanMoveVisitor canMoveVisitor;
    private Iterator<EntityObserver> entityObserverIterator;
    private int movementSpeed;

    public static final int MAX_MOVESPEED = 60;

    public EntityMovementCommand(Coord src, Direction dir, int movementSpeed, Map map, Entity entity, CanMoveVisitor canMoveVisitor, Iterator<EntityObserver> entityObserverIterator) {
        this.src = src;
        this.subject = entity;
        this.map = map;
        this.canMoveVisitor = canMoveVisitor;
        this.entityObserverIterator = entityObserverIterator;
        this.movementSpeed = movementSpeed;

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
       // subject.setFacingDirection(dir);

        System.out.println("Model: Going to move from " + src.toString() + " to " + dst.toString());

    }

    public int execute() {
        if (subject.isActive()) return 0;
        try {
            map.getActiveMapArea().getTile(dst).accept(canMoveVisitor);
            if (canMoveVisitor.canMove()) {

                //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+canMoveVisitor.canMove());

                //this will throw if no tile exists
                map.getActiveMapArea().getTile(dst);

                map.getActiveMapArea().getTile(src).removeEntity(subject);


                subject.setLocation(dst);
                subject.setActive(true);


                int moveTicks = MAX_MOVESPEED - movementSpeed;
                TimeModel.getInstance().registerAlertable(this, moveTicks);


                while (entityObserverIterator.hasNext()) {
                    entityObserverIterator.next().alertMove(dst.getR(), dst.getS(), moveTicks * TimeController.MODEL_TICK);
                }

                //This needs to be last for teleporting, bad connasence
                map.getActiveMapArea().getTile(dst).addEntity(subject);
                return moveTicks;

            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public void mAlert() {
        subject.setActive(false);
    }
}
