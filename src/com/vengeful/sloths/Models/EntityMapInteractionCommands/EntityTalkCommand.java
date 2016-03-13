package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Entity.NPC;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

import java.util.Iterator;

/**
 * Created by John on 3/12/2016.
 */
public class EntityTalkCommand implements Alertable {

    private Coord src;
    private Coord dst;

    public EntityTalkCommand(Direction dir, Coord src){

        this.src = src;

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
    }
    @Override
    public int execute() {

        Iterator<Entity> iter = Map.getInstance().getTile(dst).getEntityIterator();
        while(iter.hasNext()){
            Entity current = iter.next();
            try{
                ((NPC)current).talk();
            }catch(ClassCastException e){
                //not an NPC
            }
        }
        return 0;
    }

    @Override
    public void mAlert() {

    }
}
