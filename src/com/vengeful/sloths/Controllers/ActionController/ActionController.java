package com.vengeful.sloths.Controllers.ActionController;

import com.vengeful.sloths.Controllers.Target.*;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;

import java.util.*;

/**
 * Created by zach on 2/22/16.
 */
public abstract class ActionController implements TargetVisitor {

    private Entity entity;

    public ActionController(){}
    public ActionController(Entity entity){
        this.setEntity(entity);
    }

    public abstract void action(Target target);


    @Override
    public abstract void visitAvatarTarget(AvatarTarget avatar);

    @Override
    public abstract void visitPiggyTarget(PiggyTarget piggy);

    @Override
    public abstract void visitAggressiveNPCTarget(AgressiveNPCTarget aNPC);

    @Override
    public abstract void visitNonAggressiveNPCTarget(NonAgressiveNPCTarget nonANPC);

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }


    protected Direction getTargetDirection(Target target){

        Iterator<Coord> iter;
        ArrayList<Coord> marked = new ArrayList<>();
        marked.add(target.getCoord());
        Queue<Coord> queue = new LinkedList<>();
        //int currRing = 0;
        //while(currRing <= distance) {

        int dir = 0;
        queue.add(entity.getLocation());

        while(!queue.isEmpty()){
            Coord tmp = queue.remove();
            if (!marked.contains(tmp)) {
                iter = HexMath.sortedRing(tmp, 1);
                //this while loop checks for solution
                dir = 0;
                while (iter.hasNext()) {
                    Coord current = iter.next();

                    //System.out.println("iter: " + current + " == " + target.getCoord());
                    if (current.equals(target.getCoord())) {
                        System.out.println("dir is: " + dir);
                        switch (dir) {
                            case 0:
                                return Direction.N;
                            case 1:
                                return Direction.NE;
                            case 2:
                                return Direction.SE;
                            case 3:
                                return Direction.S;
                            case 4:
                                return Direction.SW;
                            case 5:
                                return Direction.NW;
                            default:
                                System.out.println("not directioning rigt");
                        }

                    }
                    ++dir;
                    if (!marked.contains(current)) {
                        queue.add(current);
                    }
                }
            }
            marked.add(tmp);
        }
        System.out.println("defaulting");
        return Direction.N;

    }

    protected boolean checkLocation(Target target, int distance){
        if(target != null) {
            int currRing = 0;
            while(currRing <= distance) {
                Iterator<Coord> iter = HexMath.ring(entity.getLocation(), currRing);
                while(iter.hasNext()){
                    if(iter.next().equals(target.getCoord())){
                        return true;
                    }
                }
                ++currRing;
            }
            //will only get here to return true if the target is in desired location
            return false;
        }else{return false;}
    }
}
