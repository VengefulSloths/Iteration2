package com.vengeful.sloths.Controllers.ActionController;

import com.vengeful.sloths.Controllers.Target.*;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

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

    protected Direction getTargetDirection(Target target, int distance){

        Iterator<Coord> iter;
        int currRing = 0;
        while(currRing <= distance) {
            iter = HexMath.ring(entity.getLocation(), currRing);
            int dir = 0;
            while(iter.hasNext()){
                Coord current = iter.next();
                //System.out.println("iter: " + current + " == " + target.getCoord());
                if(current.equals(target.getCoord())) {
                    if (currRing <= 1) {
                        System.out.println("dir is: " + dir);
                        switch (dir) {
                            case 0:
                                return Direction.N;
                            case 1:
                                return Direction.S;
                            case 2:
                                return Direction.NE;
                            case 3:
                                return Direction.SW;
                            case 4:
                                return Direction.SE;
                            case 5:
                                return Direction.NW;
                            default:
                                System.out.println("not directioning rigt");
                        }
                    }else{
                        
                    }

                }
                ++dir;
            }
            ++currRing;
        }
        //find directions using sorted ring
        System.out.println("defaulting");
        return Direction.N;

    }

    protected boolean checkLocation(Target target, int distance){
        if(target != null) {
//            System.out.println("entity is: " + entity);
//            System.out.println("target is: " + target);
//            System.out.println((target.getCoord().getR()));
//            System.out.println(Math.abs(entity.getLocation().getR()));
//
//            if ((Math.abs(target.getCoord().getR()) - Math.abs(entity.getLocation().getR())) > distance  &&  (Math.abs(target.getCoord().getS()) - Math.abs(entity.getLocation().getS())) > distance) {
//                System.out.println("R dist is : " + (Math.abs(target.getCoord().getR()) - Math.abs(entity.getLocation().getR())));
//                return false;
//            }
//            if ((Math.abs(target.getCoord().getS()) - Math.abs(entity.getLocation().getS())) > distance) {
//                System.out.println("S dist is : " + (Math.abs(target.getCoord().getS()) - Math.abs(entity.getLocation().getS())));
//                return false;
//            }
            int currRing = 0;
            while(currRing <= distance) {
                Iterator<Coord> iter = HexMath.ring(entity.getLocation(), currRing);
                while(iter.hasNext()){
                    if(iter.next() == target.getCoord()){
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
