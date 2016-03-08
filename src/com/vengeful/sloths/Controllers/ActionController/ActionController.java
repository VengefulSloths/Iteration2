package com.vengeful.sloths.Controllers.ActionController;

import com.vengeful.sloths.Controllers.Target.*;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.CanMoveVisitor;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.DefaultCanMoveVisitor;
import com.vengeful.sloths.Models.Map.*;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;

import java.util.*;

/**
 * Created by zach on 2/22/16.
 */
public abstract class ActionController implements TargetVisitor {

    private Entity entity;
    private CanMoveVisitor canMoveVisitor= new DefaultCanMoveVisitor();

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
    public abstract void visitAggressiveNPCTarget(AggressiveNPCTarget aNPC);

    @Override
    public abstract void visitNonAggressiveNPCTarget(NonAgressiveNPCTarget nonANPC);

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    protected boolean moveToTarget(Target target){
        Direction dir = getTargetDirection(target);
        if(entity.move(dir) > 0){
            return true;
        }else if(entity.move(getAlternativeDirection(dir)) > 0){
            return true;
        }else if( entity.move(getUnblockedDirection()) > 0){
            return true;
        }else{
            return false;
        }
    }

    protected Direction getUnblockedDirection(){
        Iterator<Coord> iter = HexMath.sortedRing(entity.getLocation(), 1);
        while(iter.hasNext()){
            try {
                Coord current = iter.next();
                Map.getInstance().getTile(current).accept(canMoveVisitor);
                if (canMoveVisitor.canMove()) {
                    return HexMath.getCoordDirection(entity.getLocation(), current);
                }
            }catch(Exception e){

            }
        }

        //default
        return null;
    }

    protected Direction getAlternativeDirection(Direction dir){
        switch(dir){
            case NE:
                return Direction.N;
            case SE:
                return Direction.NE;
            case S:
                return Direction.SE;
            case SW:
                return Direction.S;
            case NW:
                return Direction.SW;
            case N:
                return Direction.NW;
            default: return null;
        }
    }


    protected Direction getDirectionBFS(Target target){
        Queue<Coord> queue = new LinkedList<>();
        List<Coord> visited = new LinkedList<>();
        java.util.Map<Coord, Coord> parentMap = new HashMap<>();
        Coord targetCoord = target.getCoord();
        Iterator<Coord> iter;
        CanMoveVisitor canMoveVisitor = new DefaultCanMoveVisitor();
        Map map = Map.getInstance();

        queue.add(entity.getLocation());
        parentMap.put(entity.getLocation(), null);

        while(!queue.isEmpty()){
            Coord currCoord = queue.remove();
            visited.add(currCoord);
            //System.out.println("the queue's size is:" + queue.size());
            System.out.println("Comparing R's: " + currCoord.getR() + "=" + targetCoord.getR() + " and S's: " + currCoord.getS() + "=" + targetCoord.getS());
            if(currCoord.equals(targetCoord)){
                //we found the thing
                Coord prev = null;
                Coord curr = currCoord;

                while(!curr.equals(entity.getLocation())) {
                    //System.out.println("bloop");
                    prev = curr;
                    curr = parentMap.get(curr);
                    System.out.println(curr);
                }
                System.out.println("fjsdkfjdsfd");
                return HexMath.getCoordDirection(entity.getLocation(), prev);
            }
            iter = HexMath.sortedRing(currCoord,1);
            while(iter.hasNext()){
                Coord tmpCoord = iter.next();
                try {
                    map.getTile(tmpCoord).accept(canMoveVisitor);
                    if ((canMoveVisitor.canMove() && !visited.contains(tmpCoord)) || tmpCoord.equals(targetCoord)) {
                        if(!parentMap.containsKey(tmpCoord)) {
                            parentMap.put(tmpCoord, currCoord);
                        }
                        queue.add(tmpCoord);
                    }
                }catch(Exception e){
                    System.out.println("out of map bounds");
                }
            }
        }
        for(Coord c: visited){
            System.out.println(c);
        }
        return null;
    }

    protected Direction getTargetDirection(Target target){

        Iterator<Coord> iter;
        ArrayList<Coord> marked = new ArrayList<>();
        marked.add(target.getCoord());
        Queue<Coord> queue = new LinkedList<>();
        //Queue<Coord> path = new LinkedList<>();
        //int currRing = 0;
        //while(currRing <= distance) {

        int dir = 0;
        queue.add(entity.getLocation());

        while(!queue.isEmpty()){
            Coord tmp = queue.remove();
            //path.add(tmp);
            if (!marked.contains(tmp)) {
                iter = HexMath.sortedRing(tmp, 1);
                //this while loop checks for solution
                dir = 0;
                while (iter.hasNext()) {
                    Coord current = iter.next();

                    //System.out.println("iter: " + current + " == " + target.getCoord());
                    if (current.equals(target.getCoord())) {
                        //System.out.println("dir is: " + dir);
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
            //path.remove(tmp);
            marked.add(tmp);
        }
        System.out.println("defaulting");
        return null;

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
